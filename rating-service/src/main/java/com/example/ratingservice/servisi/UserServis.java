package com.example.ratingservice.servisi;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServis {

	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;

	@Autowired
	private RatingRepozitorij ratingRepozitorij;

	public User getOne(Long id) {
		if(korisnikRepozitorij.findById(id).isPresent()) {
			return korisnikRepozitorij.getOne(id);
		}
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronadjen!");
	}

	public List<User> findAll() {
		return korisnikRepozitorij.findAll();
	}

	public void save(User korisnik) {
		korisnikRepozitorij.save(korisnik);
	}


}
