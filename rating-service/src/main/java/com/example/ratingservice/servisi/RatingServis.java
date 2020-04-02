package com.example.ratingservice.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;

@Transactional
@Service
public class RatingServis {
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	
	public List<Rating> findAll(){
		return ratingRepozitorij.findAll();
	}
	
	public Rating getOne(Long id) {
		if(ratingRepozitorij.findById(id).isPresent()) {
			return ratingRepozitorij.getOne(id);
		}
		throw new ApiRequestException("Rating sa id "+id.toString()+" nije pronađen!");
	}
	
	public List<Rating> findByKorisnik(Long id) {
		
		if(korisnikRepozitorij.findById(id).isPresent()) {
			List<Rating> all_ratings=ratingRepozitorij.findAll();
			List<Rating> ratings_by_user=new ArrayList<Rating>();
			
		for (Rating r:all_ratings) {
			if(r.getKorisnik().getId()==id) {
				ratings_by_user.add(r);
			}
		}
		return ratings_by_user;
		}
		
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronađen!");
	}
	
	public List<Rating> findByStrip(Long id) {
		
		if(stripRepozitorij.findById(id).isPresent()) {
			List<Rating> all_ratings=ratingRepozitorij.findAll();
			List<Rating> ratings_by_strip=new ArrayList<Rating>();
		
		for (Rating r:all_ratings) {
			if(r.getStrip().getId()==id) {
				ratings_by_strip.add(r);
			}
		}
		return ratings_by_strip;
		}
		
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronađen!");
	}
	
	
	public void save(Rating rating) {
		
		Strip strip_stari=rating.getStrip();
		Korisnik korisnik_stari=rating.getKorisnik();
		if(!korisnikRepozitorij.findById(rating.getKorisnik().getId()).isPresent()) {
			throw new ApiRequestException("Korisnik ne postoji!");
		}
		
		if(!stripRepozitorij.findById(rating.getStrip().getId()).isPresent()) {
			throw new ApiRequestException("Strip ne postoji!");
		}
		

		Korisnik korisnik=korisnikRepozitorij.getOne(korisnik_stari.getId());
		Strip strip=stripRepozitorij.getOne(strip_stari.getId());
		
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
		
		
		korisnikRepozitorij.save(korisnik);
		stripRepozitorij.save(strip);
		ratingRepozitorij.save(rating);
	}
	
	
}
