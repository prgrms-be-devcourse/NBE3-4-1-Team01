package com.programmers.cafe.controller;

import com.programmers.cafe.dto.OrderRequest;
import com.programmers.cafe.entity.Product;
import com.programmers.cafe.service.CreateOrderService;
import com.programmers.cafe.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final CreateOrderService createOrderService;

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
    @PostMapping("/create/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        createOrderService.createOrder(orderRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(UriComponentsBuilder.fromPath("/").build().toUri());

        return new ResponseEntity<>("Order created successfully", headers, HttpStatus.FOUND);
    }
}
