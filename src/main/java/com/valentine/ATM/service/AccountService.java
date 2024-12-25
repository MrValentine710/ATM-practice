package com.valentine.ATM.service;

import com.valentine.ATM.model.Account;
import com.valentine.ATM.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    @Transactional
    public String deposit(String accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) {
            return "Account not found!";
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return "Deposit successful. New balance: " + account.getBalance();
    }

    @Transactional
    public String withdraw(String accountNumber, double amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) {
            return "Account not found!";
        }
        if (account.getBalance() < amount) {
            return "Insufficient funds!";
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return "Withdrawal successful. Remaining balance: " + account.getBalance();
    }
}
