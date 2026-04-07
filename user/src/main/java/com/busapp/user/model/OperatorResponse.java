package com.busapp.user.model;

import com.busapp.user.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OperatorResponse {

    private String companyName;
    private Status status;
}
