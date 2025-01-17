package com.programmers.cafe.controller;

import com.programmers.cafe.dto.ProductRequestDto;
import com.programmers.cafe.dto.ProductResponseDto;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Product> productList = productService.getList(page);
        model.addAttribute("productList", productList);
        return "admin_product";
    }

    @GetMapping("/create")
    public String createProduct() {
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
