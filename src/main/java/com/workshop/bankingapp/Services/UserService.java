package com.workshop.bankingapp.Services;


import com.workshop.bankingapp.Dtos.AccountDTo;
import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Interfaces.IUser;
import com.workshop.bankingapp.Models.User;
import com.workshop.bankingapp.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService implements IUser, UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Integer save(UserDto userDto) {
        //DTO TO entity
        User user = modelMapper.map(userDto, User.class);
        return  userRepository.save(user).getId();
    }

    @Override
    public List<User> findall() {
        return userRepository.findAll();

    }


    @Override
    public UserDto findById(Integer id) {
        //Entity to Dto
        User user= userRepository.findById(id).get();
        UserDto userDto= modelMapper.map(user,UserDto.class);
        return  userDto;
    }

    @Override
    public void delete(Integer id) {
         userRepository.deleteById(id);  }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("NO USER FOUND FOR VALIDATION "));
        user.setActive(true);
        //create a bank account
        AccountDTo accountDTo= new AccountDTo();
        accountService.save(accountDTo);
        return user.getId();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("No user was found with the provided email"));
    }
}
