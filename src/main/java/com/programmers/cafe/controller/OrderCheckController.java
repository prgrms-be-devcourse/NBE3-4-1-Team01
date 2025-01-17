package com.programmers.cafe.controller;

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

    @GetMapping
    public String check(Model model, @RequestParam(defaultValue = "0") int page) { // 주문 목록 최초 화면
        Page<Order> paging = orderService.findAllByPage(page);
        model.addAttribute("paging", paging);

        return "order_check";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(defaultValue = "2") int deliveryStatus,
                         @RequestParam(defaultValue = "") String email,
                         @RequestParam(defaultValue = "0") int page, Model model) { // 주문 필터
        Page<Order> paging = orderService.getOrderByFilters(deliveryStatus, email, page);

        if (paging == null) {  // "모두" 선택 및 이메일 비어있는 경우: 최초 화면 반환
            return "redirect:/order";
        }

        model.addAttribute("paging", paging);
        model.addAttribute("selectedStatus", deliveryStatus); // 선택한 상태를 화면에 표시
        model.addAttribute("searchEmail", email); // 검색한 이메일 주소를 화면에 표시

        return "order_check";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) { // 주문 삭제
        orderService.deleteById(id);
        return "redirect:/order";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable long id, Model model) {
        Order order = orderService.findById(id);
        model.addAttribute("order", order);

        return "order_modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(@ModelAttribute Order order) {
        orderService.modifyOrders(order);

        return "redirect:/order";
    }
}
