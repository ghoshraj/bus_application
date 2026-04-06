package com.busapp.user.model;

import com.busapp.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperatorResponse {

    private String companyName;
    private Status status;
}
