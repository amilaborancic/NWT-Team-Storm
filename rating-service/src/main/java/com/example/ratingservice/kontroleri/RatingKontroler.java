package com.example.ratingservice.kontroleri;
import com.example.ratingservice.DTO.KorisnikInfoRating;
import com.example.ratingservice.DTO.StripInfoRating;
import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import com.example.ratingservice.servisi.UserServis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class RatingKontroler {
	@Autowired
	private RatingServis ratingServis;
	@Autowired
	private UserServis korisnikServis;
	@Autowired
	private StripServis stripServis;

	// vraca sve ratinge
	@RequestMapping(value = "/ratings", method = RequestMethod.GET)
	public List<Rating> all() {
		return ratingServis.findAll();
	}

	// vraca rating na osnovu id-a
	@RequestMapping(value = "/rating/{id}", method = RequestMethod.GET)
	Rating ratingById(@PathVariable Long id) {
		return ratingServis.getOne(id);
	}

	// vraca sve ratinge za nekog korisnika
	@RequestMapping(value = "/rating-korisnika/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<Rating> ratingsByUser(@PathVariable Long id) {
		return ratingServis.findByKorisnik(id);
	}

	// vraca sve komentare i usera za neki strip-NOVO
	@RequestMapping(value = "/komentari-stripa/{id}", method = RequestMethod.GET, produces = "application/json")
	public Map<String, String> commentsByStrip(@PathVariable Long id) {

		if (stripServis.getOne(id) != null) {
			return ratingServis.commentsByStrip(id);
		}
		throw new ApiRequestException("Strip nije pronadjen");
	}

	// vraca sve ratinge za neki strip
	@RequestMapping(value = "/rating-stripa/{id}", method = RequestMethod.GET, produces = "application/json")
	List<Rating> ratingsByStrip(@PathVariable Long id) {
		return ratingServis.findByStrip(id);
	}

	// kreiranje novog ratinga
	@RequestMapping(value = "/dodaj-rating", method = RequestMethod.POST, consumes = "application/json")
	public void addRating(@RequestBody @Valid Rating rating) throws Exception {
		ratingServis.addRating(rating);
	}

}
