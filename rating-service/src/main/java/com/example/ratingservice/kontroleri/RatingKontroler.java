package com.example.ratingservice.kontroleri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.UserServis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;


@RestController
public class RatingKontroler {
	@Autowired
	private RatingServis ratingServis;
	@Autowired
	private UserServis korisnikServis;
	@Autowired
	private StripServis stripServis;
	@Autowired
	private RestTemplate restTemplate;
	
	//vraca sve ratinge
	@RequestMapping(value="/ratings", method=RequestMethod.GET)
	public List<Rating> all(){
		return ratingServis.findAll();
	}
	
	//vraca rating na osnovu id-a
	@RequestMapping(value="/rating/{id}", method=RequestMethod.GET)
	Rating ratingById (@PathVariable Long id){
		return ratingServis.getOne(id);
	}
	
	//vraca sve ratinge za nekog korisnika
	@RequestMapping(value="/rating-korisnika/{id}", method=RequestMethod.GET,produces = "application/json")
	public List<Rating> ratingsByUser (@PathVariable Long id){
		return ratingServis.findByKorisnik(id);
	}
	
	//vraca sve komentare i usera za neki strip-NOVO
	@RequestMapping(value="/komentari-stripa/{id}", method=RequestMethod.GET,produces = "application/json")
	public Map<String,String> commentsByStrip (@PathVariable Long id){	
			
		if(stripServis.getOne(id)!=null) {
			List<Rating> all_ratings=ratingServis.findAll();
			Map<String,String> korisnik_komentar=new HashMap<String,String>();
		
		for (Rating r:all_ratings) {
			if(r.getStrip().getId()==id) {
				ResponseEntity<String> username=restTemplate.exchange("http://user-service/username/"+r.getKorisnik().getId().toString(), HttpMethod.GET,null,String.class);
				korisnik_komentar.put(r.getKomentar(),username.getBody());
			}
		}
		return korisnik_komentar;
	}
		throw new ApiRequestException("Strip nije pronadjen");
	}
	
	
	//vraca sve ratinge za neki strip
		@RequestMapping(value="/rating-stripa/{id}", method=RequestMethod.GET,produces = "application/json")
		List<Rating> ratingsByStrip (@PathVariable Long id){	
			return ratingServis.findByStrip(id);
		}
		
	//kreiranje novog ratinga
	@RequestMapping(value="/dodaj-rating", method=RequestMethod.POST, consumes = "application/json")
	public void addRating(@RequestBody @Valid Rating rating, Errors errors) throws Exception{
		
		//VALIDACIJA
		//errori u body-u
		if(errors.hasErrors()) {
			List<ConstraintViolation<?>> violations = new ArrayList<>();
			for(ObjectError e:errors.getAllErrors()) {
				violations.add(e.unwrap(ConstraintViolation.class));
			}	
			String exc="";
			//za svaki input
			for(ConstraintViolation<?>e: violations) {
				exc+=e.getPropertyPath()+" "+e.getMessage()+"\n";
			}
			throw new Exception("Unos za rating je neispravan: "+exc.toString());
		}

		//strip-rating, stripservis vraca ima li trazenog stripa
		String url="http://comicbook-service/strip?id_strip="+rating.getStrip().getId();
		ResponseEntity<String> response_strip=restTemplate.getForEntity(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root=mapper.readTree(response_strip.getBody());
		//podaci o stripu
		Double ukupni_rating=Double.valueOf(root.path("ukupniRating").toString());
		Long strip_id=Long.valueOf(root.path("id").toString());
	    Integer ukupno_komentara=Integer.valueOf(root.path("ukupnoKomentara").toString());

		//user-rating
		url="http://user-service/user/"+rating.getKorisnik().getId();
		ResponseEntity<String> response_korisnik=restTemplate.getForEntity(url, String.class);
		mapper = new ObjectMapper();
		root=mapper.readTree(response_korisnik.getBody());
		//podaci o korisniku
		Long korisnik_id=Long.valueOf(root.path("id").toString());
		Integer broj_losih_reviewa=Integer.valueOf(root.path("broj_losih_reviewa").toString());
		Integer ukupno_reviewa=Integer.valueOf(root.path("ukupno_reviewa").toString());

		//logika za rating
		if(ukupno_reviewa!=0) {
			 double procenat=((ukupno_reviewa-broj_losih_reviewa)/ukupno_reviewa)*100;
			 if(procenat>=40 && procenat<=60) {
				 ukupni_rating=(ukupni_rating+Double.valueOf(rating.getOcjena()))/2;
			 }
		}
		if(rating.getOcjena()<4)	
			broj_losih_reviewa++;
		ukupno_komentara++;
		ukupno_reviewa++;
		
		//strip azuriranje
		Strip strip;
		if(stripServis.getOne(strip_id)!=null)  {
			strip=stripServis.getOne(strip_id);
		}
		else{
			strip=new Strip();
			strip.setId(strip_id);//da bude isti id kao u strip servisu
		}
		strip.setUkupniRating(ukupni_rating);
		strip.setUkupnoKomentara(ukupno_komentara);
		//korisnik azuriranje
		User korisnik;
		if(korisnikServis.getOne(korisnik_id)!=null)
			korisnik=korisnikServis.getOne(korisnik_id);
		else {
			korisnik=new User();
			korisnik.setId(korisnik_id);
		}
		korisnik.setBroj_losih_reviewa(broj_losih_reviewa);
		korisnik.setUkupno_reviewa(ukupno_reviewa);	
		
		//update strip
		url="http://comicbook-service/strip/update-rating";
		HttpHeaders headers=new HttpHeaders();
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Strip> requestEntity=new HttpEntity<Strip>(strip,headers);
		restTemplate.put(url,requestEntity);
	    //update korisnik
		url="http://user-service/user/update-rating";
		headers=new HttpHeaders();
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<User> requestBody=new HttpEntity<User>(korisnik,headers);
		restTemplate.put(url, requestBody);
		stripServis.save(strip);
		korisnikServis.save(korisnik);
		rating.setKorisnik(korisnik);
		rating.setStrip(strip);
		ratingServis.save(rating);
		
	}
	
}
