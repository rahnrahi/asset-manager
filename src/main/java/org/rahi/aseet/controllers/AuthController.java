package org.rahi.aseet.controllers;


import jakarta.validation.Valid;
import org.rahi.aseet.Entities.UserAccountEntity;
import org.rahi.aseet.payload.request.LoginRequest;
import org.rahi.aseet.payload.request.SignUpRequest;
import org.rahi.aseet.payload.response.JwtResponse;
import org.rahi.aseet.security.JwtUtils;
import org.rahi.aseet.services.UserDetailsImpl;
import org.rahi.aseet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping()
    public String index(){
        return "Hello";
    }

    @GetMapping("user/{userId}")
    public UserAccountEntity getUser(@PathVariable UUID userId) throws Exception {
        return userService.getUser(userId);
    }

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @ModelAttribute LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("sign-up")
    public UserAccountEntity saveUser(@Valid @ModelAttribute SignUpRequest userModel){
        userModel.setPassword(encoder.encode(userModel.getPassword()));

        return userService.saveUser(userModel);
    }


}
