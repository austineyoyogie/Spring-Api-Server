package com.sj07.sj07hardwarestore.resource.inventory;

import com.sj07.sj07hardwarestore.entities.inventory.Category;
import com.sj07.sj07hardwarestore.response.HttpResponse;
import com.sj07.sj07hardwarestore.service.inventory.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/add")
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid Category category) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        Category addCategory = categoryService.createCategory(category);

        return ResponseEntity.created(getUri()).body(
            HttpResponse.builder()
                .timeStamp(now().toString())
                .data(of("category", addCategory))
                .message(String.format("Category added to field %s", category.getName()))
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    private URI getUri() {
        return URI.create(fromCurrentContextPath().path("/add/get/<categoryId>").toUriString());
    }
}
