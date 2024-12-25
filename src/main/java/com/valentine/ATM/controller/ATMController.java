package com.valentine.ATM.controller;

import com.valentine.ATM.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    private final AccountService accountService;

    public ATMController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.withdraw(accountNumber, amount);
    }
}
