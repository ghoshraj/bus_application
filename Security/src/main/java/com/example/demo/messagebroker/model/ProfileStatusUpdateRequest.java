package com.example.demo.messagebroker.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileStatusUpdateRequest {

    private int userId;
}
