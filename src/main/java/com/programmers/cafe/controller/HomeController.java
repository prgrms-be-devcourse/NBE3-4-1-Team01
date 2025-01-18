package com.programmers.cafe.controller;

import com.programmers.cafe.entity.Product;
import com.programmers.cafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/mainorder")
    public String home() {
        return "order_create";
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Product> productList = productService.getList(page);
        model.addAttribute("productList", productList);
        return "product_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id")Long id, Model model){
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "product_detail";
    }
}
