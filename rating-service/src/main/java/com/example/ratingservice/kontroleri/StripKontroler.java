package com.example.ratingservice.kontroleri;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.KorisnikServis;
import com.example.ratingservice.servisi.StripServis;
@RestController
public class StripKontroler {
	@Autowired
	private StripServis stripServis;
	
	
	//vraca sve stripove
		@RequestMapping(value="/stripovi", method=RequestMethod.GET)
		public List<Strip> all() {
			return (List<Strip>) stripServis.findAll();
	    }
		
	//vraca info za neki strip
		@RequestMapping(value="/strip/{id}", method=RequestMethod.GET)
		public Optional<Strip> stripById(@PathVariable Long id) {
			return stripServis.findById(id);
		}	
		
	//dodaje strip novi na osnovu imena
		@RequestMapping(value="/dodaj-strip/{naziv}", method=RequestMethod.GET)
		public String addStrip(@PathVariable String naziv) {
			Strip strip=new Strip();
			strip.setNaziv(naziv);
			stripServis.save(strip);
			return "Novi strip je dodan sa nazivom "+strip.getNaziv();
		}	
							
		
		
}
