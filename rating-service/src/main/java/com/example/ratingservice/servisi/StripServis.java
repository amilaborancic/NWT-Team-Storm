package com.example.ratingservice.servisi;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.StripRepozitorij;


@Service
public class StripServis {
	
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	public Optional<Strip> findById(Long id) {
		return stripRepozitorij.findById(id);
	}
	public Strip getOne(Long id) {
		return stripRepozitorij.getOne(id);
	}
	public Iterable<Strip> findAll() {
		return stripRepozitorij.findAll();
	}
	
	public void save(Strip strip) {
		stripRepozitorij.save(strip);
	}
	
}