package com.sj07.sj07hardwarestore.repository;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.User;

import java.util.Collection;


public interface UserRepository<T extends User> {

    T create(T data);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    Boolean delete(Long id);
  
    User getUserByEmail(String email);
    void sendVerificationCode(UserDTO user);
    T verifyCode(String email, String code);
}
