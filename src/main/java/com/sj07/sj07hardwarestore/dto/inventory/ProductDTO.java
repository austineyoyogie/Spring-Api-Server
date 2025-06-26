package com.sj07.sj07hardwarestore.dto.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_DEFAULT)
public class ProductDTO {
    private Long id;
    private String name;
    private String descri;
    private String slug;
    private String sku;
    private String image;
    private Integer quantity;
    private BigDecimal price;
    private String categoryName;
    private String descriptions;
    private Integer stock;
    private Boolean isActive;
    private Boolean outdated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
