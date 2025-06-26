package com.sj07.sj07hardwarestore.service.implementation;

import com.sj07.sj07hardwarestore.dto.UserDTO;
import com.sj07.sj07hardwarestore.entities.authentic.Role;
import com.sj07.sj07hardwarestore.entities.authentic.User;
import com.sj07.sj07hardwarestore.repository.RoleRepository;
import com.sj07.sj07hardwarestore.repository.UserRepository;
import com.sj07.sj07hardwarestore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.sj07.sj07hardwarestore.dtomapper.UserDTOMapper.fromUser;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {
    private final UserRepository<User> userRepository;
    private final RoleRepository<Role> roleRoleRepository;

    @Override
    public UserDTO createUser(User user) {
        return mapToUserDTO(userRepository.create(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return mapToUserDTO(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendVerificationCode(user);
    }

    @Override
    public UserDTO verifyCode(String email, String code) {
        return mapToUserDTO(userRepository.verifyCode(email, code));
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return mapToUserDTO(userRepository.get(userId));
    }

    private UserDTO mapToUserDTO(User user) {
        return fromUser(user, roleRoleRepository.getRoleByUserId(user.getId()));
    }
}
