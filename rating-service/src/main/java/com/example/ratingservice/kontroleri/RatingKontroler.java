package com.example.ratingservice.kontroleri;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.KorisnikServis;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;


@RestController
public class RatingKontroler {
	@Autowired
	private RatingServis ratingServis;
	@Autowired
	private KorisnikServis korisnikServis;
	@Autowired
	private StripServis stripServis;
	
	//vraca sve ratinge
	@RequestMapping(value="/ratings", method=RequestMethod.GET)
	public List<Rating> all(){
		return ratingServis.findAll();
	}
	
	//vraca rating na osnovu id-a
	@RequestMapping(value="/rating/{id}", method=RequestMethod.GET)
	Optional<Rating> ratingById (@PathVariable Long id){
		return ratingServis.findById(id);
	}
	
	//vraca sve ratinge za nekog korisnika
	@RequestMapping(value="/rating-korisnika/{id}", method=RequestMethod.GET,produces = "application/json")
	List<Rating> ratingsByUser (@PathVariable Long id){
		return ratingServis.findByKorisnik(id);
	}
	
	//vraca sve ratinge za neki strip
		@RequestMapping(value="/rating-stripa/{id}", method=RequestMethod.GET,produces = "application/json")
		List<Rating> ratingsByStrip (@PathVariable Long id){
			return ratingServis.findByStrip(id);
		}
		
	
	//kreiranje novog ratinga
	@RequestMapping(value="/dodaj-rating", method=RequestMethod.POST)
	public void addRating(@RequestParam(value="strip_id") Long strip_id,@RequestParam(value="korisnik_id") Long user_id,@RequestParam(value="ocjena") Integer ocjena,@RequestParam(value="komentar") String komentar)
	{
		Korisnik korisnik=korisnikServis.getOne(user_id);
		Strip strip=stripServis.getOne(strip_id);

		int br_losih_reviewa=korisnik.getBroj_losih_reviewa();
		int ukupno_reviewa=korisnik.getUkupno_reviewa();
		double procenat;
		if(ukupno_reviewa!=0) {
			 procenat=((ukupno_reviewa-br_losih_reviewa)/ukupno_reviewa)*100;
			 if(procenat>=40 && procenat<=60) {
				 double rating_stripa=strip.getUkupni_rating();
				 strip.setUkupni_rating((rating_stripa+Double.valueOf(ocjena))/2);
			 }
		}
		if(ocjena<4)	
			korisnik.setBroj_losih_reviewa(korisnik.getBroj_losih_reviewa()+1);
				
		strip.setUkupno_komentara(strip.getUkupno_komentara()+1);
		korisnik.setUkupno_reviewa(korisnik.getUkupno_reviewa()+1);
		Rating rating=new Rating(korisnik,strip,ocjena,komentar);
		ratingServis.save(rating);
	
	}
	

}
