package com.example.ratingservice;

import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import com.example.ratingservice.servisi.UserServis;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RatingKontrolerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	UserServis korisnikServis;
	@Autowired
	StripServis stripServis;
	@Autowired
	RatingServis ratingServis;
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Test
	public void all() throws Exception {
		mockMvc.perform(get("/rating/svi")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void ratingById () throws Exception {
		mockMvc.perform( get("/rating/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	public void ratingsByUser () throws Exception {
		//sve okej
		mockMvc.perform(get("/rating/korisnika/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1));
		//user ne postoji
		mockMvc.perform(get("/rating/korisnika/{id}", 1232)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("Korisnik sa id 1232 nije pronadjen!"));
	}

	@Test
	public void ratingsByStrip () throws Exception {
		//sve okej
		mockMvc.perform( get("/rating/stripa/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1));
		//nepostojeci strip
		mockMvc.perform( get("/rating/stripa/{id}", 1123)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("Strip sa id 1123 nije pronadjen!"));
	}

	@Test
	public void addRating () throws Exception {
		User korisnik=korisnikServis.getOne(1L);
		Strip strip1 =stripServis.getOne(1L);
		Strip strip2 = stripServis.getOne(2L);
		Strip strip3 = stripServis.getOne(3L);
		Rating rating=new Rating(korisnik,strip2,1,"lose");
		//sve okej
		mockMvc.perform(post("/rating/dodaj-rating")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(rating)))
				.andExpect(status().isOk());
		//ocjena veca od 5
		Rating ocjenaNeValja = new Rating(korisnik, strip2, 6, "aks");
		mockMvc.perform(post("/rating/dodaj-rating")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(ocjenaNeValja)))
				.andExpect(status().is(400));
		//korisnik je vec ostavio rating na strip
		Rating ostavljen = new Rating(korisnik, strip2, 1, "lose");
		mockMvc.perform(post("/rating/dodaj-rating")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(ostavljen)))
				.andExpect(jsonPath("$.message").value("Korisnik je vec ostavio rating na dati strip."));
	}

	@Test
	public void komentariStripa() throws Exception {
		mockMvc.perform(get("/rating/komentari-stripa/{id}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

}
