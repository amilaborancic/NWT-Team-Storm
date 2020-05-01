package com.example.ratingservice.servisi;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.grpc.Events;
import com.example.ratingservice.grpc.actionGrpc;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class StripServis {
	
	@Autowired
	private StripRepozitorij stripRepozitorij;

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

	public Strip getOne(Long id) {
		if(stripRepozitorij.findById(id).isPresent()) {
			addEvent(Events.ActionType.GET,"strip id "+id.toString());
			return stripRepozitorij.getOne(id);
		}
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronadjen!");
	}
	
	public void save(Strip strip) {
		addEvent(Events.ActionType.CREATE,"dodavanje stripa");
		stripRepozitorij.save(strip);
	}
	
	public List<Strip> findAll() {
		addEvent(Events.ActionType.GET,"svi stripovi");
		return stripRepozitorij.findAll();
	}

	public Optional<Strip> findById(Long id) {
		addEvent(Events.ActionType.GET,"strip id "+id.toString());
		return stripRepozitorij.findById(id);
	}


		
}