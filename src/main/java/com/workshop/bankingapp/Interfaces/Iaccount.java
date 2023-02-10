package com.workshop.bankingapp.Interfaces;

import com.workshop.bankingapp.Dtos.AccountDTo;
import com.workshop.bankingapp.Models.Account;

import java.util.List;

public interface Iaccount {
    Integer save(AccountDTo accountDTo);
    List<Account> Allaccounts();
    AccountDTo findAccountByid(Integer id);
    void deleteAccount(Integer id);
}
