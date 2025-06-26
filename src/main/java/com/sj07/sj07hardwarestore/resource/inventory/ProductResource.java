package com.sj07.sj07hardwarestore.resource.inventory;


import com.sj07.sj07hardwarestore.dto.inventory.ProductDTO;
import com.sj07.sj07hardwarestore.entities.inventory.Product;
import com.sj07.sj07hardwarestore.response.HttpResponse;
import com.sj07.sj07hardwarestore.service.inventory.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/add")
public class ProductResource {
    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<HttpResponse> addProduct(@RequestBody @Valid Product product) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        ProductDTO addProductDTO = productService.createProduct(product);
        return ResponseEntity.created(getUri()).body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("product", addProductDTO))
                .message(String.format("Product created for field %s", product.getName()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    private URI getUri() {
        return URI.create(fromCurrentContextPath().path("/add/get/<productId>").toUriString());
    }

}
