package com.example.ratingservice.servisi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ratingservice.DTO.KorisnikInfoRating;
import com.example.ratingservice.DTO.StripInfoRating;
import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Transactional
@Service
public class RatingServis {
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	@Autowired
	private StripRepozitorij stripRepozitorij;
	@Autowired
	private RestTemplate restTemplate;
	
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
	
	public void addRating(Rating rating) throws JsonMappingException, JsonProcessingException {

		// strip-rating, stripservis vraca ima li trazenog stripa
		String url = "http://comicbook-service/strip?id_strip=" + rating.getStrip().getId();
		ResponseEntity<String> response_strip = restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response_strip.getBody());
		// podaci o stripu
		Double ukupni_rating = Double.valueOf(root.path("ukupniRating").toString());
		Long strip_id = Long.valueOf(root.path("id").toString());
		Integer ukupno_komentara = Integer.valueOf(root.path("ukupnoKomentara").toString());

		// user-rating
		url = "http://user-service/user/" + rating.getKorisnik().getId();
		ResponseEntity<String> response_korisnik = restTemplate.getForEntity(url, String.class);
		mapper = new ObjectMapper();
		root = mapper.readTree(response_korisnik.getBody());
		// podaci o korisniku
		Long korisnik_id = Long.valueOf(root.path("id").toString());
		Integer broj_losih_reviewa = Integer.valueOf(root.path("broj_losih_reviewa").toString());
		Integer ukupno_reviewa = Integer.valueOf(root.path("ukupno_reviewa").toString());

		// logika za rating
		if (ukupno_reviewa != 0) {
			double procenat = ((ukupno_reviewa - broj_losih_reviewa) / ukupno_reviewa) * 100;
			if (procenat >= 40 && procenat <= 60) {
				ukupni_rating = (ukupni_rating + Double.valueOf(rating.getOcjena())) / 2;
			}
		}
		if (rating.getOcjena() < 4)
			broj_losih_reviewa++;
		ukupno_komentara++;
		ukupno_reviewa++;

		// strip azuriranje
		Strip strip;
		if (stripRepozitorij.getOne(strip_id) != null) {
			strip = stripRepozitorij.getOne(strip_id);
		} else {
			strip = new Strip();
			strip.setId(strip_id);// da bude isti id kao u strip servisu
			stripRepozitorij.save(strip);
		}
		// korisnik azuriranje
		User korisnik;
		if (korisnikRepozitorij.getOne(korisnik_id) != null)
			korisnik = korisnikRepozitorij.getOne(korisnik_id);
		else {
			korisnik = new User();
			korisnik.setId(korisnik_id);
			korisnikRepozitorij.save(korisnik);
		}
		KorisnikInfoRating korisnik_r = new KorisnikInfoRating(korisnik_id, broj_losih_reviewa, ukupno_reviewa);
		StripInfoRating strip_r = new StripInfoRating(strip_id, ukupno_komentara, ukupni_rating);

		// update strip
		url = "http://comicbook-service/strip/update-rating";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<StripInfoRating> requestEntity = new HttpEntity<StripInfoRating>(strip_r, headers);
		restTemplate.put(url, requestEntity);
		// update korisnik
		url = "http://user-service/user/update-rating";
		headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<KorisnikInfoRating> requestBody = new HttpEntity<KorisnikInfoRating>(korisnik_r, headers);
		restTemplate.put(url, requestBody);
		stripRepozitorij.save(strip);
		korisnikRepozitorij.save(korisnik);
		rating.setKorisnik(korisnik);
		rating.setStrip(strip);
		ratingRepozitorij.save(rating);
	}

	public Map<String, String> commentsByStrip(Long id) {
		List<Rating> all_ratings = ratingRepozitorij.findAll();
		Map<String, String> korisnik_komentar = new HashMap<String, String>();

		for (Rating r : all_ratings) {
			if (r.getStrip().getId() == id) {
				ResponseEntity<String> username = restTemplate.exchange(
						"http://user-service/username/" + r.getKorisnik().getId().toString(), HttpMethod.GET, null,
						String.class);
				korisnik_komentar.put(username.getBody(),r.getKomentar());
			}
		}
		
		return korisnik_komentar;

	}
	
	
}
