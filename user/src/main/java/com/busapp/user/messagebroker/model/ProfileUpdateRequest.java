package com.busapp.user.messagebroker.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileUpdateRequest {

    private int userId;
}
