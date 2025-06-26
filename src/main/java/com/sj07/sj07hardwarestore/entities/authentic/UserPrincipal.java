package com.sj07.sj07hardwarestore.entities.authentic;

import com.sj07.sj07hardwarestore.dto.UserDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import static com.sj07.sj07hardwarestore.dtomapper.UserDTOMapper.fromUser;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;


@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    private final User user;
    private final Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(role.getPermissions().split(",".trim())).map(SimpleGrantedAuthority::new).collect(toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }


   //@Override
    public boolean isExpired() {
        return user.isExpired();
    }

    public UserDTO getUser() {
        return fromUser(user, role);
    }
}
