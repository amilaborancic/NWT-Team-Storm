package com.example.ratingservice.kontroleri;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingservice.exception.ApiRequestException;
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
	Rating ratingById (@PathVariable Long id){
		return ratingServis.getOne(id);
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
	public void addRating(@RequestBody @Valid Rating rating, Errors errors) throws Exception{
		
		//errori u body-u
		if(errors.hasErrors()) {
			List<ConstraintViolation<?>> violations = new ArrayList<>();
			for(ObjectError e:errors.getAllErrors()) {
				violations.add(e.unwrap(ConstraintViolation.class));
			}
			
			String exc="";
			//za svaki input
			for(ConstraintViolation<?>e: violations) {
				exc+=e.getPropertyPath()+" "+e.getMessage()+"\n";
			}
			throw new Exception("Unos za rating je neispravan: "+exc.toString());
		}
		ratingServis.save(rating);
	}
	

}
