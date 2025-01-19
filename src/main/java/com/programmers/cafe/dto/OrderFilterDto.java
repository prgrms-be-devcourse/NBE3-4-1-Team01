package com.programmers.cafe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Getter
@Setter
public class OrderFilterDto {
    private int deliveryStatus = 2;
    private String email = "";
}
