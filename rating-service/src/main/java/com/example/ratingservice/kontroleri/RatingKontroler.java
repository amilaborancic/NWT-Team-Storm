package com.example.ratingservice.kontroleri;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(value="/dodaj-rating", method=RequestMethod.POST, consumes = "application/json")
	public void addRating(@RequestBody Rating rating)
	{
		Strip strip_stari=rating.getStrip();
		Korisnik korisnik_stari=rating.getKorisnik();
		
		Rating novi_rating=new Rating();
		Korisnik korisnik=korisnikServis.getOne(korisnik_stari.getId());
		Strip strip=stripServis.getOne(strip_stari.getId());
		
		int ocjena=rating.getOcjena();
		int ukupno_komentara=strip.getUkupno_komentara();
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
			korisnik.setBroj_losih_reviewa(br_losih_reviewa+1);
				
		strip.setUkupno_komentara(ukupno_komentara+1);
		korisnik.setUkupno_reviewa(ukupno_reviewa+1);

		novi_rating.setKomentar(rating.getKomentar());
		novi_rating.setOcjena(rating.getOcjena());
		novi_rating.setKorisnik(korisnik);
		novi_rating.setStrip(strip);
		
		
		korisnikServis.save(korisnik);
		stripServis.save(strip);
		ratingServis.save(novi_rating);
	}
	

}
