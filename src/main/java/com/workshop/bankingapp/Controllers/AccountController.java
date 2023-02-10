package com.workshop.bankingapp.Controllers;


import com.workshop.bankingapp.Dtos.AccountDTo;
import com.workshop.bankingapp.Models.Account;
import com.workshop.bankingapp.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/newaccount")
    public ResponseEntity<?> saveNewAccount(@RequestBody AccountDTo accountDTo) {
        accountService.save(accountDTo);
        return ResponseEntity.ok("An Account is added with success ");

    }

    @GetMapping("/accounts")
    public List<Account> GetallAccounts() {
        return accountService.Allaccounts();

    }

    @GetMapping("/accByid/{id}")
    public AccountDTo FindACCbyId(@PathVariable Integer id){
        return accountService.findAccountByid(id);

    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccountByID(@PathVariable Integer id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("the ACcount is deleted with success ");
    }
}
