package com.example.ratingservice.servisi;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class StripServis {
	
	@Autowired
	private StripRepozitorij stripRepozitorij;

	public Strip getOne(Long id) {
		if(stripRepozitorij.findById(id).isPresent())
			return stripRepozitorij.getOne(id);
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronadjen!");
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