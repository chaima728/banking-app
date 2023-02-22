package com.workshop.bankingapp.Services;


import com.workshop.bankingapp.Configuration.JwtUtils;
import com.workshop.bankingapp.Dtos.AccountDTo;
import com.workshop.bankingapp.Dtos.AuthenticationRequest;
import com.workshop.bankingapp.Dtos.AuthenticationResponse;
import com.workshop.bankingapp.Dtos.UserDto;
import com.workshop.bankingapp.Interfaces.IUser;
import com.workshop.bankingapp.Models.Role;
import com.workshop.bankingapp.Models.User;
import com.workshop.bankingapp.Repositories.RoleRepository;
import com.workshop.bankingapp.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements IUser{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final JwtUtils jwtUtils;

    private static final String ROLE_USER = "ROLE_USER";
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;





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
        //activate account
        user.setActive(true);
        //create a bank account
        AccountDTo accountDTo= AccountDTo.builder().build();
        accountService.save(accountDTo);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("NO USER FOUND FOR INVALIDATION "));
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public AuthenticationResponse register(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        var savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",savedUser.getId());
        claims.put("fullName",savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser);
        return AuthenticationResponse.builder().token(token).build();
    }

    @Override
    public AuthenticationResponse Authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        final User savedUser  = userRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",savedUser.getId());
        claims.put("fullName",savedUser.getFirstname() + " " + savedUser.getLastname());
        final String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder().token(token).build();
    }


    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }


}
