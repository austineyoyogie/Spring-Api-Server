package com.sj07.sj07hardwarestore.entities.inventory;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Product {
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Description is required")
    private String descri;
    @NotEmpty(message = "Slug is required")
    private String slug;
   // @NotEmpty(message = "SKU is required")
    @Column(unique = true)
    private String sku;
    //@NotEmpty(message = "Image uri is required")
    private String image;

    @Min(1)
    private Integer quantity;
   // @Positive(message = "Product price must be a positive value")
    private BigDecimal price;

    @Min(1)
    private Integer stock;
    private Boolean isActive;
    private Boolean outdated;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
