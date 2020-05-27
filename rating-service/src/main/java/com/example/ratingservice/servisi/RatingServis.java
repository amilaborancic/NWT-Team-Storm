package com.example.ratingservice.servisi;

import com.example.ratingservice.DTO.KorisnikInfoRating;
import com.example.ratingservice.DTO.StripInfoRating;
import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.grpc.EventSubmission;
import com.example.ratingservice.grpc.Events;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@EnableEurekaClient
@Transactional
@Service
public class RatingServis {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	EventSubmission eventSubmission;
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	@Autowired
	private StripRepozitorij stripRepozitorij;

	public List<Rating> findAll(){
		eventSubmission.addEvent(Events.ActionType.GET,"svi rejtinzi");
		return ratingRepozitorij.findAll();
	}

	public Rating getOne(Long id) {

		if(ratingRepozitorij.findById(id).isPresent()) {
			eventSubmission.addEvent(Events.ActionType.GET, "rating sa id "+id.toString());
			return ratingRepozitorij.getOne(id);
		}

		else throw new ApiRequestException("Rating sa id "+id.toString()+" nije pronadjen!");
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
			eventSubmission.addEvent(Events.ActionType.GET, "rejtinzi korisnika");
			return ratings_by_user;
		}
		throw new ApiRequestException("Korisnik sa id "+id.toString()+" nije pronadjen!");
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
			eventSubmission.addEvent(Events.ActionType.GET, "rejtinzi stripa");
			return ratings_by_strip;
		}
		throw new ApiRequestException("Strip sa id "+id.toString()+" nije pronadjen!");
	}

	public void save(Rating rating) {
		eventSubmission.addEvent(Events.ActionType.CREATE,"dodavanje ratinga");
		ratingRepozitorij.save(rating);
	}

	public String addRating(Rating rating) throws JsonMappingException, JsonProcessingException {

		// provjera
		if (rating.getOcjena() < 1 || rating.getOcjena() > 5)
			throw new ApiRequestException("Ocjena mora biti u rasponu od 1 do 5!");

		if (rating.getKorisnik() == null || rating.getStrip() == null)
			throw new ApiRequestException("Nepotpun zahtjev!");
		// dodano zbog exceptiona
		ResponseEntity<Long> korisnici = restTemplate.getForEntity("http://user-service/user/count", Long.class);
		ResponseEntity<Long> stripovi = restTemplate.getForEntity("http://comicbook-service/strip/count", Long.class);

		// provjera
		if (rating.getKorisnik().getId() > korisnici.getBody() || rating.getKorisnik().getId() < 0)
			throw new ApiRequestException("Id korisnika je pogresan!");
		if (rating.getStrip().getId() > stripovi.getBody() || rating.getStrip().getId() < 0)
			throw new ApiRequestException("Id stripa je pogresan!");

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

		// provjera
		List<Rating> rating_lista = ratingRepozitorij.findAll();
		for (Rating r : rating_lista) {
			if (r.getKorisnik().getId() == korisnik_id && r.getStrip().getId() == strip_id)
				throw new ApiRequestException("Korisnik je vec ostavio rating na dati strip.");
		}

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
		if (stripRepozitorij.findById(strip_id).get() != null) {
			strip = stripRepozitorij.getOne(strip_id);
		} else {
			strip = new Strip();
			strip.setId(strip_id);// da bude isti id kao u strip servisu
			stripRepozitorij.save(strip);
		}
		// korisnik azuriranje
		User korisnik;
		if (korisnikRepozitorij.findById(korisnik_id).get() != null)
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

		eventSubmission.addEvent(Events.ActionType.CREATE, "kreiranje ratinga");
		return "Uspjesno ste ostavili recenziju na strip!";
	}

	//komunicira sa user ms-om
	public Map<String, String> commentsByStrip(Long id) {
		List<Rating> all_ratings = ratingRepozitorij.findAll();
		Map<String, String> korisnik_komentar = new HashMap<String, String>();
		if (stripRepozitorij.findById(id).isPresent()) {
			for (Rating r : all_ratings) {
				if (r.getStrip().getId() == id) {
					ResponseEntity<String> username = restTemplate.exchange(
							"http://user-service/user/username/" + r.getKorisnik().getId().toString(), HttpMethod.GET, null,
							String.class);
					korisnik_komentar.put(username.getBody(), r.getKomentar());
				}
			}
			eventSubmission.addEvent(Events.ActionType.GET, "komentari korisnika na strip");
			return korisnik_komentar;
		} else throw new ApiRequestException("Strip sa id " + id.toString() + " nije pronadjen!");
	}

	public List<Integer> ocjeneStripa(Long id){
		List<Integer> ocjeneStripa=new ArrayList<>();
		for(Rating r:ratingRepozitorij.findAll()){
			ocjeneStripa.add(r.getOcjena());
		}
		return ocjeneStripa;
	}

}
