package com.example.ratingservice.servisi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.StripRepozitorij;


@Service
public class StripServis {
	
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	public Strip getOne(Long id) {
		
		if(stripRepozitorij.findById(id).isPresent()) {
			return stripRepozitorij.getOne(id);
		}
		
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronađen!");
		
	}
	
	public void save(Strip strip) {
		stripRepozitorij.save(strip);
	}
	
	public void deleteById(Long id) {
		
		if(stripRepozitorij.findById(id).isPresent()){
			stripRepozitorij.deleteById(id);
		}
		
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronađen!");
	}
	
	public Iterable<Strip> findAll() {
		return stripRepozitorij.findAll();
	}
	
}