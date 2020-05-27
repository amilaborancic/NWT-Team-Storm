package com.example.ratingservice.kontroleri;

import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.servisi.UserServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserKontroler {
	@Autowired
	private UserServis korisnikServis;
	@Autowired
	private RatingRepozitorij ratingRepozitorij;

	//vraca sve korisnike
	@RequestMapping(value="/korisnici", method=RequestMethod.GET)
	public List<User> all() {
		return (List<User>) korisnikServis.findAll();
	}

	//dodaje novog korisnika - samo za testiranje kroz postman
	@RequestMapping(value="/dodaj-korisnika", method=RequestMethod.GET)
	public void addUser() {
		User korisnik=new User();
		korisnikServis.save(korisnik);
	}
	//vraca podatke o korisniku sa nekim id-om
	@RequestMapping(value="/korisnik/{id}", method=RequestMethod.GET)
	public User userById(@PathVariable Long id) {
		return korisnikServis.getOne(id);
	}

	@RequestMapping(value="/korisnici-stripa/{id}", method=RequestMethod.GET)
	public List<String> korisniciStripa(@PathVariable Long id){
		return korisnikServis.korisniciStripa(id);
	}

}
