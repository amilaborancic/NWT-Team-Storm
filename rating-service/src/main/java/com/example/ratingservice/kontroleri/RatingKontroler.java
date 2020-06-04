package com.example.ratingservice.kontroleri;
import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/rating")
@Controller
public class RatingKontroler {
	@Autowired
	private RatingServis ratingServis;
	@Autowired
	private StripServis stripServis;
	private String jsonTemplate = "jsonTemplate";

	// vraca sve ratinge
	@GetMapping(value = "/svi")
	public String all(Model model) {
		model.addAttribute("ratings", ratingServis.findAll());
		model.addAttribute("nazivResursa", "Svi rejtinzi.");
		return jsonTemplate;
	}

	// vraca sve ratinge za nekog korisnika
	@GetMapping(value = "/korisnika/{id}", produces = "application/json")
	public String ratingsByUser(@PathVariable Long id, Model model) {
		model.addAttribute("ratings", ratingServis.findByKorisnik(id));
		model.addAttribute("nazivResursa", "Rejtinzi korisnika id=" + id + ".");
		return jsonTemplate;
	}

	// vraca sve komentare i usera za neki strip
	//KOMUNICIRA SA USER SERVISOM
	@GetMapping(value = "/komentari-stripa/{id}", produces = "application/json")
	public String commentsByStrip(@PathVariable Long id, Model model) {
		if (stripServis.getOne(id) != null) {
			model.addAttribute("komentari", ratingServis.commentsByStrip(id));
			model.addAttribute("nazivResursa", "Komentari na strip.");
			return jsonTemplate;
		}
		throw new ApiRequestException("Strip nije pronadjen");
	}

	// kreiranje novog ratinga - komunicira sa user i strip ms
	@PostMapping(value = "/dodaj-rating", consumes = "application/json")
	public String addRating(@RequestBody @Valid Rating rating, @RequestHeader Map<String, String> headers, Model model) throws Exception {
		String token = headers.get("authorization").substring(7);
		model.addAttribute("nazivResursa", "Uspjesno ostavljena recenzija.");
		ratingServis.addRating(rating, token);
		return jsonTemplate;
	}

	//NO LONGER USED
	@GetMapping(value = "/ocjene/{id}", produces = "application/json")
	public String ocjeneStripa(@PathVariable Long id, Model model){
		List<Integer> ocjeneStripa=new ArrayList<>();
		for(Rating r:ratingServis.findAll()){
			ocjeneStripa.add(r.getOcjena());
		}
		model.addAttribute("ocjene", ocjeneStripa);
		model.addAttribute("nazivResursa", "Ocjene stripa.");
		return jsonTemplate;
	}
	//NO LONGER USED
	@GetMapping(value = "/komentari/{id}", produces = "application/json")
	public String komentariStripa(@PathVariable Long id, Model model){
		List<String> komentariStripa=new ArrayList<>();
		for(Rating r:ratingServis.findAll()){
			komentariStripa.add(r.getKomentar());
		}
		model.addAttribute("komentari", komentariStripa);
		model.addAttribute("nazivResursa", "Komentari stripa.");
		return jsonTemplate;
	}
}
