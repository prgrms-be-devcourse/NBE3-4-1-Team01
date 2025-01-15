package com.programmers.cafe.controller;

import com.programmers.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCheckController {
    private final OrderService orderService;

    @GetMapping
    @ResponseBody
    public String check() {
        return "orderCheck";
    }
}
