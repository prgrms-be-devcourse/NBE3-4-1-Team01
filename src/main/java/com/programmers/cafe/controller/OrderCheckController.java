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
        model.addAttribute("searchEmail", email); // 검색한 이메일 주소를 화면에 표시
        return "order_check";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam int deliveryStatus, @RequestParam String email, Model model) { // 주문 필터
        List<Order> orders;

        if (deliveryStatus == 2 && (email == null || email.isEmpty())) {
            // "모두" 선택 및 이메일 비어있는 경우: 최초 화면 반환
            return "redirect:/order";
        } else if (deliveryStatus == 2) {
            // "모두" 선택 및 이메일 필터만 적용
            orders = orderService.findByEmail(email);
        } else if (email == null || email.isEmpty()) {
            // 배송 상태 필터만 적용
            orders = orderService.findByStatus(deliveryStatus);
        } else {
            // 배송 상태와 이메일 모두 필터 적용
            orders = orderService.findByStatusAndEmail(deliveryStatus, email);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("selectedStatus", deliveryStatus); // 선택한 상태를 화면에 표시
        model.addAttribute("searchEmail", email); // 검색한 이메일 주소를 화면에 표시

        return "order_check";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable long id) { // 주문 삭제
        orderService.deleteById(id);
        return "redirect:/order";
    }
}
