package com.example.demo.controller;

import com.example.demo.exception.ApiRequestException;
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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwt;

    @PostMapping(value="/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authReq) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(),authReq.getPassword()));
        }
        catch(BadCredentialsException e){
            throw new ApiRequestException("Netačan username ili šifra!");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
        final String token = jwt.generateToken(userDetails);
        final String role = userDetails.getAuthorities().toArray()[0].toString();
        return ResponseEntity.ok(new AuthenticationResponse(token, role));
    }

    @PostMapping(value="/loggedUser")
    public ResponseEntity<?> retrieveUser(@RequestBody Map<String, String> body){
        String token = body.get("token");
        Map<String, String> res = new HashMap<>();
        res.put("username", jwt.extractUsername(token));
        return ResponseEntity.ok(res);
    }
}
