package com.sj07.sj07hardwarestore.service;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.User;


public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);
    UserDTO verifyCode(String email, String code);
    UserDTO getUserById(Long userId);
}
