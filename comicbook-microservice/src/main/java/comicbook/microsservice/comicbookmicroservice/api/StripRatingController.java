package comicbook.microsservice.comicbookmicroservice.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.service.StripService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class StripRatingController {

	@Autowired
	StripService stripService;
	@Autowired
	RestTemplate restTemplate;
	private String jsonTemplate = "jsonTemplate";

	@PutMapping(value = "/strip/update-rating")
	public String azurirajStrip(@RequestBody StripRatingInfo stripRatingInfo, Model model) {
		model.addAttribute("nazivResursa", "Rating stripa - ažuriran.");
		stripService.azurirajStrip(stripRatingInfo);
		return jsonTemplate;
	}

	@GetMapping(value = "/strip/komentari/{id}")
	public String komentariStripa(@PathVariable Long id, Model model) {
		model.addAttribute("komentari", stripService.komentariStripa(id));
		model.addAttribute("nazivResursa", "Komentari na strip sa id="+id+".");
		return jsonTemplate;
	}

}
