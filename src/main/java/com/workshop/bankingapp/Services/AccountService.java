package com.workshop.bankingapp.Services;


import com.workshop.bankingapp.Dtos.AccountDTo;
import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Exceptions.ObjectValidationException;
import com.workshop.bankingapp.Interfaces.Iaccount;
import com.workshop.bankingapp.Models.Account;
import com.workshop.bankingapp.Models.User;
import com.workshop.bankingapp.Repositories.AccountRepository;
import com.workshop.bankingapp.Validators.ObjectsValidators;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements Iaccount {

    @Autowired
    private AccountRepository accountRepository;

    private final ObjectsValidators<AccountDTo> validator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Integer save(AccountDTo accountDTo) {
        validator.Validaate(accountDTo);
        // Dto to Entity
        Account account = modelMapper.map(accountDTo, Account.class);
        // generate random Iban
        if(accountDTo.getId()==null) {
            account.setIban(NewIban());
        }
        return accountRepository.save(account).getId();
    }

    @Override
    public List<Account> Allaccounts() {
        return accountRepository.findAll();
    }


    @Override
    public AccountDTo findAccountByid(Integer id) {
        Account account = accountRepository.findById(id).get();
        //Entity to Dto
        AccountDTo accountDTo = modelMapper.map(account,AccountDTo.class);
        return accountDTo;


    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);

    }

    public String NewIban() {
        String iban = Iban.random(CountryCode.TN).toFormattedString();
        Boolean ibanExits = accountRepository.findByIban(iban).isPresent();

        if (ibanExits) {
                NewIban();
        }
        return iban;
    }
}
