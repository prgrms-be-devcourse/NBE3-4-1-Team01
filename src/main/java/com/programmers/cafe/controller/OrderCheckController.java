package com.programmers.cafe.controller;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCheckController {
    private final OrderService orderService;

    @GetMapping
    public String check(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "order_check";
    }

    @GetMapping("/search-by-email")
    public String searchByEmail(@RequestParam String email, Model model) {
        List<Order> orders = orderService.findByEmail(email);
        model.addAttribute("orders", orders);
        return "order_check";
    }
}
