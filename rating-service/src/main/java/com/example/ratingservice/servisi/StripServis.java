package com.example.ratingservice.servisi;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class StripServis {
	
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	
	
	public Strip getOne(Long id) {
		if(stripRepozitorij.findById(id).isPresent())
			return stripRepozitorij.getOne(id);
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronađen!");
	}
	
	public void save(Strip strip) {
		stripRepozitorij.save(strip);
	}
	
	public List<Strip> findAll() {
		return stripRepozitorij.findAll();
	}

	public Optional<Strip> findById(Long id) {
		return stripRepozitorij.findById(id);
	}


		
}