package comicbook.microsservice.comicbookmicroservice.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.service.StripService;


@RestController
public class StripRatingController {

	@Autowired
	StripService stripService;
	@Autowired
	RestTemplate restTemplate;

	@PutMapping(value = "/strip/update-rating")
	public void azurirajStrip(@RequestBody StripRatingInfo stripRatingInfo) {
		stripService.azurirajStrip(stripRatingInfo);
	}

	@GetMapping(value = "strip/komentari/{id}")
	public ResponseEntity<Map<String, String>> komentariStripa(@PathVariable Long id) {
		return stripService.komentariStripa(id);
	}

}
