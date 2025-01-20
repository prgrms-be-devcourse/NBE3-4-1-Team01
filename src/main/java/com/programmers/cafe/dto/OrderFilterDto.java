package com.programmers.cafe.dto;

import com.programmers.cafe.global.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
public class OrderFilterDto {
    private DeliveryStatus deliveryStatus;
    private String email = "";
}
