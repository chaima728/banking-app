package com.workshop.bankingapp.Controllers;

import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Interfaces.IUser;
import com.workshop.bankingapp.Models.User;
import com.workshop.bankingapp.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;



@PostMapping("/new_user")
    public ResponseEntity<?> save (@RequestBody UserDto userDto) {
    userService.save(userDto);
    return ResponseEntity.ok("A User is added with success ");
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> findAllUsers() {
    return ResponseEntity.ok(userService.findall());
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("user-id")Integer id) {
    return  ResponseEntity.ok(userService.findById(id));
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id) {
    userService.delete(id);
    return ResponseEntity.ok("User is deleted succefully");
    }



}
