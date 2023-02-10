package com.workshop.bankingapp.Interfaces;

import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Models.User;

import java.util.List;

public interface IUser {

     Integer save (UserDto userDto);
     List<User> findall();
     UserDto findById(Integer id);
     void delete(Integer id);

     Integer validateAccount(Integer id);
}
