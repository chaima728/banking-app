package com.workshop.bankingapp.Controllers;


import com.workshop.bankingapp.Configuration.JwtUtils;
import com.workshop.bankingapp.Dtos.AuthenticationRequest;
import com.workshop.bankingapp.Dtos.AuthenticationResponse;
import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Repositories.UserRepository;
import com.workshop.bankingapp.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto user) {

        return ResponseEntity.ok(userService.register(user));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return  ResponseEntity.ok(userService.Authenticate(authenticationRequest));



    }


}
