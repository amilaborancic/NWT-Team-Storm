package com.example.ratingservice.kontroleri;
import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/rating")
@RestController
public class RatingKontroler {
	@Autowired
	private RatingServis ratingServis;
	@Autowired
	private StripServis stripServis;

	// vraca sve ratinge
	@GetMapping(value = "/svi")
	public List<Rating> all() {
		return ratingServis.findAll();
	}

	// vraca rating na osnovu id-a
	@GetMapping(value = "/{id}")
	Rating ratingById(@PathVariable Long id) {
		return ratingServis.getOne(id);
	}

	// vraca sve ratinge za nekog korisnika
	@GetMapping(value = "/korisnika/{id}", produces = "application/json")
	public List<Rating> ratingsByUser(@PathVariable Long id) {
		return ratingServis.findByKorisnik(id);
	}

	// vraca sve komentare i usera za neki strip
	//KOMUNICIRA SA USER SERVISOM
	@GetMapping(value = "/komentari-stripa/{id}", produces = "application/json")
	public Map<String, String> commentsByStrip(@PathVariable Long id) {

		if (stripServis.getOne(id) != null) {
			return ratingServis.commentsByStrip(id);
		}
		throw new ApiRequestException("Strip nije pronadjen");
	}

	// vraca sve ratinge za neki strip
	@GetMapping(value = "/stripa/{id}", produces = "application/json")
	List<Rating> ratingsByStrip(@PathVariable Long id) {
		return ratingServis.findByStrip(id);
	}

	// kreiranje novog ratinga - komunicira sa user i strip ms
	@PostMapping(value = "/dodaj-rating", consumes = "application/json")
	public String addRating(@RequestBody @Valid Rating rating) throws Exception {
		return ratingServis.addRating(rating);
	}

	//vraca ocjene
	@GetMapping(value = "/ocjene/{id}", produces = "application/json")
	public @ResponseBody List<Integer> ocjeneStripa(@PathVariable Long id){
		List<Integer> ocjeneStripa=new ArrayList<>();
		for(Rating r:ratingServis.findAll()){
			ocjeneStripa.add(r.getOcjena());
		}
		return ocjeneStripa;
	}

	//vraca komentare
	@GetMapping(value = "/komentari/{id}", produces = "application/json")
	public @ResponseBody List<String> komentariStripa(@PathVariable Long id){
		List<String> komentariStripa=new ArrayList<>();
		for(Rating r:ratingServis.findAll()){
			komentariStripa.add(r.getKomentar());
		}
		return komentariStripa;
	}
}
