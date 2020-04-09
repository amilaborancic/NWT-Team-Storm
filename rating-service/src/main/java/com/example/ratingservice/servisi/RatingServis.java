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
import com.example.ratingservice.modeli.User;
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
		return ratingRepozitorij.getOne(id);
	//throw new ApiRequestException("Rating sa id "+id.toString()+" nije pronađen!");
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
		ratingRepozitorij.save(rating);
	}
	
	
}
