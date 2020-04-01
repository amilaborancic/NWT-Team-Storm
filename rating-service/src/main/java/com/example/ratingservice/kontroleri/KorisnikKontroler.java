package com.example.ratingservice.kontroleri;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.servisi.KorisnikServis;

@RestController
public class KorisnikKontroler {
	@Autowired
	private KorisnikServis korisnikServis;
	
	//vraca sve korisnike
	@RequestMapping(value="/korisnici", method=RequestMethod.GET)
	public List<Korisnik> all() {
		return (List<Korisnik>) korisnikServis.findAll();
    }
	
	//dodaje novog korisnika
	@RequestMapping(value="/dodaj-korisnika", method=RequestMethod.GET)
	public void addUser() {
		Korisnik korisnik=new Korisnik();
		korisnikServis.save(korisnik);
	}
	//vraca podatke o korisniku sa nekim id-om
		@RequestMapping(value="/korisnik/{id}", method=RequestMethod.GET)
		public Korisnik userById(@PathVariable Long id) {
			return korisnikServis.getOne(id);
		}
	
}
