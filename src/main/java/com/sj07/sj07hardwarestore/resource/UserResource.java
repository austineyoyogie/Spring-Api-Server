package com.sj07.sj07hardwarestore.resource;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.User;
import com.sj07.sj07hardwarestore.entities.authentic.UserPrincipal;
import com.sj07.sj07hardwarestore.exception.ApiException;
import com.sj07.sj07hardwarestore.form.LoginForm;
import com.sj07.sj07hardwarestore.provider.TokenProvider;
import com.sj07.sj07hardwarestore.response.HttpResponse;
import com.sj07.sj07hardwarestore.service.RoleService;
import com.sj07.sj07hardwarestore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static com.sj07.sj07hardwarestore.constant.Constants.TOKEN_PREFIX;
import static com.sj07.sj07hardwarestore.dtomapper.UserDTOMapper.toUser;
import static com.sj07.sj07hardwarestore.utils.ExceptionUtils.processError;
import static com.sj07.sj07hardwarestore.utils.UserUtils.getAuthenticatedUser;
import static com.sj07.sj07hardwarestore.utils.UserUtils.getLoggedInUser;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class UserResource {
    private final UserService userService;
    private final RoleService roleService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final  HttpServletRequest request;
    private final HttpServletResponse response;

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        Authentication authentication = authenticate(loginForm.getEmail(), loginForm.getPassword());
        UserDTO user = getLoggedInUser(authentication);
        return user.isUsingMfa() ? sendVerificationCode(user) : sendResponse(user);
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody @Valid User user) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.created(getUri()).body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("user", userDTO))
                .message(String.format("User account created for user %s", user.getFirstName()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    @GetMapping("/verify/code/{email}/{code}")
    public ResponseEntity<HttpResponse> verifyCode(@PathVariable("email") String email, @PathVariable("code") String code) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        UserDTO user = userService.verifyCode(email, code);
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("user", user,
                        "access_token", tokenProvider.createAccessToken(getUserPrincipal(user))
                        ,"refresh_token", tokenProvider.createRefreshToken(getUserPrincipal(user))))
                .message("You have login successfully")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    // "Account already verified" : "Account verification"
    @GetMapping("/refresh/token")
    public ResponseEntity<HttpResponse> refreshToken(HttpServletRequest request) throws InterruptedException {
        if(isHeaderAndTokenValid(request)) {
            String token = request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length());
            UserDTO user = userService.getUserById(tokenProvider.getSubject(token, request));
            //UserDTO user = userService.getUserByEmail(tokenProvider.getSubject(token, request));
            return ResponseEntity.ok().body(
                HttpResponse.builder()
                    .timeStamp(now().toString())
                    .data(of("user", user,
                            "access_token", tokenProvider.createAccessToken(getUserPrincipal(user)),
                            "refresh_token", token))
                    .message("Token refreshed")
                    .status(OK)
                    .statusCode(OK.value())
                    .build());
        } else {
            return ResponseEntity.badRequest().body(
                HttpResponse.builder()
                    .timeStamp(now().toString())
                    .reason("Refresh Token missing or invalid")
                    .developerMessage("Refresh Token missing or invalid")
                    .status(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .build());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<HttpResponse> profile(Authentication authentication) {
        UserDTO user = userService.getUserByEmail(getAuthenticatedUser(authentication).getEmail());
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("user", user)) //, "roles", roleService.getRoles()))
                .message("Profile Retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @RequestMapping("/error")
    public ResponseEntity<HttpResponse> handleError(HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .reason("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                .status(BAD_REQUEST)
                .statusCode(BAD_REQUEST.value())
                .build());
    }

    private URI getUri() {
        return URI.create(fromCurrentContextPath().path("/auth/get/<userId>").toUriString());
    }

    private ResponseEntity<HttpResponse> sendResponse(UserDTO user) {
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("user", user,
                        "access_token", tokenProvider.createAccessToken(getUserPrincipal(user)),
                        "refresh_token", tokenProvider.createRefreshToken(getUserPrincipal(user))))
                .message("Login success")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    private Authentication authenticate(String email, String password) {
        try {
            return authenticationManager.authenticate(unauthenticated(email, password));
        } catch (Exception exception) {
            processError(request, response, exception);
            throw new ApiException(exception.getMessage());
        }
    }

    private UserPrincipal getUserPrincipal(UserDTO user) {
        return new UserPrincipal(toUser(userService.getUserByEmail(user.getEmail())), roleService.getRoleByUserId(user.getId()));
    }

    private boolean isHeaderAndTokenValid(HttpServletRequest request) {
        return  request.getHeader(AUTHORIZATION) != null
            &&  request.getHeader(AUTHORIZATION).startsWith(TOKEN_PREFIX)
            && tokenProvider.isTokenValid(
            tokenProvider.getSubject(request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length()), request),
            request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length())
        );
    }

    // If Using Multi Factory Authentication Mfa
    private ResponseEntity<HttpResponse> sendVerificationCode(UserDTO user) {
        userService.sendVerificationCode(user);
        return ResponseEntity.ok().body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("user", user))
                .message("Verification Code Send")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }
}