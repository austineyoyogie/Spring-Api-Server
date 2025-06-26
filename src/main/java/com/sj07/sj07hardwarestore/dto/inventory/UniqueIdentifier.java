package com.sj07.sj07hardwarestore.dto.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class UniqueIdentifier {
    private Long id;
    private Long userId;
    private String identifier;
    private LocalDateTime createdAt;
    private java.time.LocalDateTime updatedAt;
}
