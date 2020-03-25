package com.example.ratingservice.servisi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;

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
	
	public Optional<Rating> findById(Long id) {
		return ratingRepozitorij.findById(id);
	}
	
	public List<Rating> findByKorisnik(Long id) {
		List<Rating> all_ratings=ratingRepozitorij.findAll();
		List<Rating> ratings_by_user=new ArrayList<Rating>();
		
		for (Rating r:all_ratings) {
			if(r.getKorisnik().getId()==id) {
				ratings_by_user.add(r);
			}
		}
		return ratings_by_user;
	}
	
	public List<Rating> findByStrip(Long id) {
		List<Rating> all_ratings=ratingRepozitorij.findAll();
		List<Rating> ratings_by_strip=new ArrayList<Rating>();
		
		for (Rating r:all_ratings) {
			if(r.getStrip().getId()==id) {
				ratings_by_strip.add(r);
			}
		}
		return ratings_by_strip;
	}
	
	
	public void save(Rating rating) {
		ratingRepozitorij.save(rating);
	}
	
	public Rating addRating(Long user_id, Long strip_id, Integer ocjena, String komentar){
		Korisnik korisnik=korisnikRepozitorij.getOne(user_id);
		Strip strip=stripRepozitorij.getOne(strip_id);
		Rating rating=ratingRepozitorij.save(new Rating(korisnik,strip,ocjena,komentar));
		return rating;
	}
	
}
