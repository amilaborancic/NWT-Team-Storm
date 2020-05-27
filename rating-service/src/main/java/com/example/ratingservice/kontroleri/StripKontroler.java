package com.example.ratingservice.kontroleri;

import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.StripServis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class StripKontroler {
	@Autowired
	private StripServis stripServis;

	//vraca sve stripove
	@GetMapping(value="/stripovi")
	public List<Strip> all() {
		return (List<Strip>) stripServis.findAll();
	}

	//vraca info za neki strip
	@GetMapping(value="/strip/{id}")
	public Strip stripById(@PathVariable Long id) {
		return stripServis.getOne(id);
	}


}
