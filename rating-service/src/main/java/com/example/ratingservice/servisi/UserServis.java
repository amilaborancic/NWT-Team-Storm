package com.example.ratingservice.servisi;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.grpc.Events;
import com.example.ratingservice.grpc.actionGrpc;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class UserServis {

	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;

	@Autowired
	private RatingRepozitorij ratingRepozitorij;


	public void addEvent(Events.ActionType tipAkcije, String nazivResursa) {

		try {
			ManagedChannel channel= ManagedChannelBuilder.forAddress("localhost", 8084).usePlaintext().build();
			actionGrpc.actionBlockingStub stub=actionGrpc.newBlockingStub(channel);
			Calendar c=Calendar.getInstance();
			String ts=c.getTime().toString();

			Events.Response response=stub.logAction(Events.Request.newBuilder()
					.setTimestamp(ts)
					.setNazivServisa("rating-service")
					.setIdKorisnik(10000L)
					.setTipAkcije(tipAkcije)
					.setNazivResursa(nazivResursa)
					.build()
			);
			System.out.println(response.getResponseTypeValue());
			System.out.println(response.getResponseContent());
			channel.shutdown();
		}
		catch(Exception e) {
			System.out.println("Greska u grpc komunikaciji!");
		}

	}

	public User getOne(Long id) {
		if(korisnikRepozitorij.findById(id).isPresent()) {
			addEvent(Events.ActionType.GET,"user id "+id.toString());
			return korisnikRepozitorij.getOne(id);
		}
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronadjen!");
	}

	public List<User> findAll() {
		addEvent(Events.ActionType.GET,"svi korisnici");
		return korisnikRepozitorij.findAll();
	}

	public void save(User korisnik) {
		addEvent(Events.ActionType.GET,"dodavanje korisnika");
		korisnikRepozitorij.save(korisnik);
	}


}
