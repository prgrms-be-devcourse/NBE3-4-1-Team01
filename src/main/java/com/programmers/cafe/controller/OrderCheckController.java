package com.programmers.cafe.controller;

import com.programmers.cafe.dto.OrderDto;
import com.programmers.cafe.dto.OrderFilterDto;
import com.programmers.cafe.entity.Order;
import com.programmers.cafe.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderCheckController {
    private final OrderService orderService;
    private final OrderFilterDto filter;

    @GetMapping
    public String check(@RequestParam(defaultValue = "0") int page, Model model) { // 주문 조회
        Page<OrderDto> paging = orderService.getOrderByFilters(filter, page);
        model.addAttribute("paging", paging);
        model.addAttribute("filter", filter);

        return "order_check";
    }

    @PostMapping
    public String filter(@ModelAttribute OrderFilterDto filter,
                         @RequestParam(defaultValue = "0") int page, Model model) { // 주문 필터
        this.filter.setEmail(filter.getEmail());
        this.filter.setDeliveryStatus(filter.getDeliveryStatus()); // 필터 설정

        Page<OrderDto> paging = orderService.getOrderByFilters(this.filter, page);

        model.addAttribute("paging", paging);
        model.addAttribute("filter", this.filter);

        return "order_check";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) { // 주문 삭제
        orderService.deleteById(id);
        return "redirect:/order";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("order", order);

        return "order_modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(@ModelAttribute OrderDto order) {
        orderService.modifyOrder(order);

        return "redirect:/order";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("order", order);

        return "order_info";
    }
}
