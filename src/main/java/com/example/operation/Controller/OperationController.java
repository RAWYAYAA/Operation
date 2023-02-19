package com.example.operation.Controller;

import com.example.operation.Entity.Operation;
import com.example.operation.OPerationService.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class OperationController {
    @Autowired
    OperationService operationService;
    @PostMapping(value="/operation")
    public Operation saveOperation(@RequestBody Operation operation){
        return operationService.saveOperation(operation);
    }
}
