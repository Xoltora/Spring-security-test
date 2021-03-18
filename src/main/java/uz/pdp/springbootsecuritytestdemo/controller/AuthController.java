package uz.pdp.springbootsecuritytestdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springbootsecuritytestdemo.entity.User;
import uz.pdp.springbootsecuritytestdemo.entity.enums.RoleName;
import uz.pdp.springbootsecuritytestdemo.payload.ApiResponse;
import uz.pdp.springbootsecuritytestdemo.payload.RecSignIn;
import uz.pdp.springbootsecuritytestdemo.payload.UserDto;
import uz.pdp.springbootsecuritytestdemo.service.AuthService;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/registerAdmin")
    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    public HttpEntity<?> registerAdmin(@RequestBody UserDto dto){
        ApiResponse apiResponse = authService.register(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PostMapping("/registerClient")
    public HttpEntity<?> registerClient(@RequestBody UserDto dto){
        dto.setRoleNames(Collections.singletonList(RoleName.ROLE_CLIENT));
        ApiResponse apiResponse = authService.register(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }
    
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody RecSignIn recSignIn){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(recSignIn.getLogin(), recSignIn.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }
}
