package com.sj07.sj07hardwarestore.utils;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.UserPrincipal;

import org.springframework.security.core.Authentication;


public class UserUtils {
    public static UserDTO getAuthenticatedUser(Authentication authentication) {
        return ((UserDTO) authentication.getPrincipal());
    }

    public static UserDTO getLoggedInUser(Authentication authentication) {
        return ((UserPrincipal) authentication.getPrincipal()).getUser();
    }
}
