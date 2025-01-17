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

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String adminHome(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        List<Product> productList = this.productService.getList();
        model.addAttribute("productList", productList);
        return "admin_product";
    }

    @GetMapping("/create")
    public String createProduct(Model model) {
        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setFilePath("/images/columbia.jpeg");  // 파일 경로 기본값 설정
        model.addAttribute("product", requestDto);
        return "product_create";
    }

    @PostMapping("/create")
    public String createProduct(@Valid ProductRequestDto productRequestDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_create";
        }

        productService.create(productRequestDto);
        return "redirect:/admin/product"; // admin 페이지로 이동
    }

    @GetMapping("/modify/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("productId", product.getId());

        ProductRequestDto requestDto = new ProductRequestDto();
        requestDto.setName(product.getName());
        requestDto.setPrice(product.getPrice());
        requestDto.setFilePath(product.getFilePath());
        model.addAttribute("product", requestDto);
        return "product_modify";
    }

    @PostMapping("/modify/{id}")
    public String updateProduct(
        @PathVariable("id") Long id,
        @Valid ProductRequestDto requestDto
    ) {
        productService.updateProduct(id, requestDto);
        return "redirect:/admin/product";  // admin 페이지로 redirect
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";  // admin 페이지로 redirect
    }
}
