package com.example.demo.messagebroker.model;


import com.example.demo.enums.Gender;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravellerProfileRequest {

    private int userId;
    private String name;
    private String phoneNumber;
    private Gender gender;
}
