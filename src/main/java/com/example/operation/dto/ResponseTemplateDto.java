package com.example.operation.dto;

import com.example.operation.Entity.Operation;
import lombok.Data;

@Data
public class ResponseTemplateDto {
    private Wallet wallet;
    private Operation operation;
}
