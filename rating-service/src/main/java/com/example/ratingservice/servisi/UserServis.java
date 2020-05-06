package com.example.ratingservice.servisi;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.grpc.EventSubmission;
import com.example.ratingservice.grpc.Events;
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

	@Autowired
	EventSubmission eventSubmission;


	public User getOne(Long id) {
		if(korisnikRepozitorij.findById(id).isPresent()) {
			eventSubmission.addEvent(Events.ActionType.GET,"user id "+id.toString());
			return korisnikRepozitorij.getOne(id);
		}
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronadjen!");
	}

	public List<User> findAll() {
		eventSubmission.addEvent(Events.ActionType.GET,"svi korisnici");
		return korisnikRepozitorij.findAll();
	}

	public void save(User korisnik) {
		eventSubmission.addEvent(Events.ActionType.GET,"dodavanje korisnika");
		korisnikRepozitorij.save(korisnik);
	}


}
