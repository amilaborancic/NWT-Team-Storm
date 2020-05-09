package com.example.demo.controller;

import com.example.demo.models.AuthenticationRequest;
import com.example.demo.models.AuthenticationResponse;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwt;
    @Autowired
    private PasswordEncoder passwordEncoder;
    //dummy request
    @GetMapping(value="/hello")
    public String pozdrav(){
        return "Cao!";
    }

    @PostMapping(value="/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUserName(),authReq.getSifra()));
        }
        catch(BadCredentialsException e){
            System.out.println(e);
            throw new Exception("Netacan username ili sifra!", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUserName());
        final String token = jwt.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
