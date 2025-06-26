package com.sj07.sj07hardwarestore.dto;
import lombok.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String telephone;
    private String title;
    private String imageUrl;
    private String roleName;
    private String permissions;
    private Integer loginAttempts;
    private boolean isUsingMfa;
    private boolean isEnabled;
    private boolean isNotLocked;
    private boolean isExpired;
    private LocalDateTime lastLogin;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
