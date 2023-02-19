package com.example.operation.OPerationService;

import com.example.operation.Entity.Operation;
import com.example.operation.Entity.Type;
import com.example.operation.Repository.OperationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
@Service
public class OperationService {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    WalletService walletService;
    public Operation saveOperation(Operation operation){
        Double soldWallet= getBalance(operation.getIdWallet());
        if(operation.getType().equals(Type.Depot)){
            soldWallet=soldWallet+operation.getAmount();
        } else if (operation.getType().equals(Type.Retrait)) {
            if (!validateOperation(soldWallet,operation.getAmount())) {
                return null;
            }
            soldWallet=soldWallet-operation.getAmount();
        }
        operation.setDate(LocalDate.now());
        if (updateWallet(operation.getIdWallet(), soldWallet)) {
            return operationRepository.save(operation);
        }
        return null;
    }
    public List<Operation> findAll(){
        return operationRepository.findAll();
    }
    public Double getBalance(Long id){
        Double balance = walletService.getBallance(id);
        return balance;
    }
    public Boolean validateOperation(Double balance,Double amout){
        return balance >= amout;
    }
    public Boolean updateWallet(Long idWallet,Double balance){
        return walletService.updateWallet(idWallet,balance);
    }
}
