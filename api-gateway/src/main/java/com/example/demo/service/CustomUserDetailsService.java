package com.example.demo.service;
import com.example.demo.models.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //dobavimo usere iz user servisa i pronadjemo onog sa username-om username

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ResponseEntity<AuthenticationRequest> response = restTemplate.getForEntity("http://user-service/user/single/"+username, AuthenticationRequest.class);

        if(response.getBody() == null){
            throw new UsernameNotFoundException("User sa username-om " + username + " nije pronadjen!");
        }

        //username, sifra, list of authorities
        return new User(response.getBody().getUserName(),response.getBody().getSifra(), getGrantedAuthorities(response.getBody().getUserName()));
        //return new User("ami", "lmao", new ArrayList<>());
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(String username){
        Collection<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        ResponseEntity<String> rola = restTemplate.getForEntity("http://user-service/user/naziv-role/"+username, String.class);
        grantedAuthorities.add(new SimpleGrantedAuthority(rola.getBody()));
        return grantedAuthorities;
    }

}
