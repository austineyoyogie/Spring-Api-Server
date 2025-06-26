package com.sj07.sj07hardwarestore.dtomapper;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.Role;
import com.sj07.sj07hardwarestore.entities.authentic.User;

import org.springframework.beans.BeanUtils;


public class UserDTOMapper {
    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static UserDTO fromUser(User user, Role role) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoleName(role.getName());
        userDTO.setPermissions(role.getPermissions());
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
