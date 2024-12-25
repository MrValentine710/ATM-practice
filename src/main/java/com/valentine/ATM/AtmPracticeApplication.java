package com.valentine.ATM;

import com.valentine.ATM.model.Account;
import com.valentine.ATM.repository.AccountRepository;
import com.valentine.ATM.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class AtmPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtmPracticeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(AccountRepository accountRepository, AccountService accountService) {
        return args -> {
            // Check if account with accountNumber '12345' exists
            Account existingAccount = accountRepository.findByAccountNumber("12345");

            // If account doesn't exist, create one with balance 0.0
            if (existingAccount == null) {
                Account account = new Account();
                account.setAccountNumber("12345");
                account.setBalance(0.0); // Initialize with 0 balance
                accountRepository.save(account);
                System.out.println("Default account created with account number 12345 and balance 0.0");
            }

            // Start interactive console input
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\nATM Console: Choose an operation:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Check Balance");
                System.out.println("4. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                if (choice == 1) {
                    System.out.print("Enter deposit amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.println(accountService.deposit("12345", amount));
                } else if (choice == 2) {
                    System.out.print("Enter withdrawal amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    System.out.println(accountService.withdraw("12345", amount));
                } else if (choice == 3) {
                    Account account = accountRepository.findByAccountNumber("12345");
                    System.out.println("Current balance: " + account.getBalance());
                } else if (choice == 4) {
                    System.out.println("Exiting ATM system...");
                    break;
                } else {
                    System.out.println("Invalid choice, please try again.");
                }
            }
            scanner.close();
        };
    }
}
