package com.programmers.cafe.service;

import com.programmers.cafe.entity.Order;
import com.programmers.cafe.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderStatusScheduler {

    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 0 14 * * ?") // 매일 오후 14:00 실행
    public void updateOrderStatuses() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime cutoffDateTime = now.with(LocalTime.of(14, 0));
        
        // 업데이트 할 status 조회
        List<Order> ordersToUpdate = orderRepository.findAll().stream()
                .filter(order -> order.getStatus() == 0 && order.getCreatedAt().isBefore(cutoffDateTime))
                .toList(); // 상태가 0(주문완료)이고, 현재 시각이 14:00 이후인 주문을 조회

        // 상태 업데이트
        ordersToUpdate.forEach(order -> {
            order.setStatus(1); // 배송중으로 변경
            orderRepository.save(order);
        });

        // 스케줄러 실행 확인 로그
        System.out.println("배송상태 업데이트 : " + now);
    }
}