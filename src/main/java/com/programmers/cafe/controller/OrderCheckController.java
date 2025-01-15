package com.programmers.cafe.controller;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCheckController {
    private final OrderService orderService;

    @GetMapping
    public String check(Model model) { // 주문 목록 최초 화면
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "order_check";
    }

    @GetMapping("/search-by-email")
    public String searchByEmail(@RequestParam String email, Model model) {
        // email이 비어있으면 최초 화면으로 리다이렉트
        if (email == null || email.trim().isEmpty()) {
            return "redirect:/order";
        }

        List<Order> orders = orderService.findByEmail(email);
        model.addAttribute("orders", orders);
        return "order_check";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) { // 주문 삭제
        orderService.deleteById(id);
        return "redirect:/order";
    }
}
