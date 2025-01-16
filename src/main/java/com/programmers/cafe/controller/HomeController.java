package com.programmers.cafe.controller;

import com.programmers.cafe.entity.Product;
import com.programmers.cafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model){
        List<Product> productList = productService.getList();
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
