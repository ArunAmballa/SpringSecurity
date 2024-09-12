package com.arun.SpringSecurity.appSecurity.services;

import com.arun.SpringSecurity.appSecurity.dto.LoginDto;
import com.arun.SpringSecurity.appSecurity.dto.SignupDto;
import com.arun.SpringSecurity.appSecurity.dto.UserDto;
import com.arun.SpringSecurity.appSecurity.entities.User;
import com.arun.SpringSecurity.appSecurity.exceptions.ResourceNotFoundException;
import com.arun.SpringSecurity.appSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not Found "));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not Found "));
    }

    public UserDto signUp(SignupDto signUpDto) {
        Optional<User>  user= userRepository.findByEmail(signUpDto.getEmail());
        if(user.isPresent()) {
            throw new BadCredentialsException("user With Email Already Exists");
        }
        User userToBeSaved=modelMapper.map(signUpDto,User.class);
        userToBeSaved.setPassword(passwordEncoder.encode(userToBeSaved.getPassword()));
        User savedUser = userRepository.save(userToBeSaved);
        return modelMapper.map(savedUser,UserDto.class);

    }


}
