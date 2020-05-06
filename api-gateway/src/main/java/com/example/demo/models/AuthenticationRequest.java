package com.example.demo.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AuthenticationRequest {
    private String UserName;
    private String sifra;

    protected AuthenticationRequest(){}

    public AuthenticationRequest(String userName, String sifra){
        this.UserName = userName;
        this.sifra = sifra;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getSifra() {
        return sifra;
    }
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }
}
