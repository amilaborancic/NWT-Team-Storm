package com.example.ratingservice.servisi;

import java.util.Optional;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;

@Transactional
@Service
public class UserServis {
	
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	
	public User getOne(Long id) {
		
		if(korisnikRepozitorij.findById(id).isPresent()) {
			return korisnikRepozitorij.getOne(id);
		}
		
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronađen!");	
	}
	
	public List<User> findAll() {
		return korisnikRepozitorij.findAll();
	}
	
	public void save(User korisnik) {
		korisnikRepozitorij.save(korisnik);
	}
	

}
