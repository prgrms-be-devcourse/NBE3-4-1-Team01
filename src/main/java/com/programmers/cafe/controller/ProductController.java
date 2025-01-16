package com.programmers.cafe.controller;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/create")
    public String createProduct(Model model) {
        model.addAttribute("productRequestDto", new ProductRequestDto());
        return "product_create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("productRequestDto") @Valid ProductRequestDto productRequestDto,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "product_create";
        }

        productService.create(productRequestDto);
        return "redirect:/product"; // admin 페이지로 이동
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long id,
        @RequestBody @Valid ProductRequestDto requestDto
    ) {
        ProductResponseDto responseDto = productService.updateProduct(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable Long id) {
        ProductResponseDto responseDto = productService.deleteProduct(id);
        return ResponseEntity.ok(responseDto);
    }
}
