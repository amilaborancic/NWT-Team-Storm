package com.example.ratingservice.servisi;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;

import antlr.collections.List;

@Service
public class KorisnikServis {
	
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	
	public Optional<Korisnik> findById(Long id) {
		return korisnikRepozitorij.findById(id);
	}
	public Korisnik getOne(Long id) {
		return korisnikRepozitorij.getOne(id);
	}
	public Iterable<Korisnik> findAll() {
		return korisnikRepozitorij.findAll();
	}
	
	public void save(Korisnik korisnik) {
		korisnikRepozitorij.save(korisnik);
	}
	public void deleteById(Long id) {
		korisnikRepozitorij.deleteById(id);
		
	}
	
}
