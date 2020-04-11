package com.example.ratingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.servisi.UserServis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RatingServisTest {

	@Autowired
	StripServis stripServis;
	
	@Autowired
	UserServis korisnikServis;
	
	@Autowired
	RatingServis ratingServis;
	
	@Autowired
	RestTemplate restTemplate;
/*	
	@Test
	public void findAll() throws Exception{
		assertThat(ratingServis.findAll().size()).isNotEqualTo(0);
	}
	

	@Test
	public void getOne()throws Exception {
		assertThat(ratingServis.getOne(Long.valueOf(1)).getId()).isEqualTo(Long.valueOf(1));
	}
	
	@Test
	public void findByKorisnik()throws Exception {
		assertThat(ratingServis.findByKorisnik(Long.valueOf(1)).size()).isNotEqualTo(0);
	}
	
	@Test
	public void findByStrip()throws Exception {
		assertThat(ratingServis.findByStrip(Long.valueOf(1)).size()).isNotEqualTo(0);
	}
	
	
	@Test
	public void save() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),stripServis.getOne(Long.valueOf(2)),2,"los strip");
		ratingServis.save(rating);
		assertThat(ratingServis.getOne(rating.getId()).getOcjena()).isEqualTo(2);
	}
*//*
	@Test
	public void addRating() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),stripServis.getOne(Long.valueOf(2)),2,"los strip");
		ratingServis.addRating(rating);
		assertThat(ratingServis.getOne(rating.getId()).getOcjena()).isEqualTo(2);
		assertThat(ratingServis.getOne(rating.getId()).getKomentar()).isEqualTo("los strip");
		assertThat(ratingServis.getOne(rating.getId()).getKorisnik().getId()).isEqualTo(Long.valueOf(1));
		assertThat(ratingServis.getOne(rating.getId()).getStrip().getId()).isEqualTo(Long.valueOf(2));
	}
	*/
	@Test
	public void addRatingInvalidKorisnik() throws Exception{
		User korisnik=new User();
		korisnik.setId(Long.valueOf(9999));
		Rating rating=new Rating(korisnik,stripServis.getOne(Long.valueOf(1)),2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Id korisnika je pogrešan!"));
	}
	
	@Test
	public void addRatingInvalidStrip() throws Exception{
		Strip strip=new Strip();
		strip.setId(Long.valueOf(9999));
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),strip,2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Id stripa je pogrešan!"));
	}
	
	@Test
	public void addRatingDuplicate() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),stripServis.getOne(Long.valueOf(1)),2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Korisnik je već ostavio rating na dati strip"));
	}
	
	@Test
	public void addRatingInvalidOcjena() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),stripServis.getOne(Long.valueOf(2)),9999,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Ocjena mora bit u rasponu od 1 do 5!"));
	}
	
	
	@Test
	public void commentsByStripInvalidStrip() {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.commentsByStrip(Long.valueOf(-2)));
		assertTrue(exception.getMessage().contains("Strip sa id -2 nije pronađen!"));
	}
	
	@Test
	public void getOneInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.getOne(Long.valueOf(999)));
		assertTrue(exception.getMessage().contains("Rating sa id 999 nije pronađen!"));
	}

	@Test
	public void findByKorisnikInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class,
				() -> ratingServis.findByKorisnik(Long.valueOf(999)));
		assertTrue(exception.getMessage().contains("Korisnik sa id 999 nije pronađen!"));
	}


	@Test
	public void findByStripInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.findByStrip(Long.valueOf(-2)));
		assertTrue(exception.getMessage().contains("Strip sa id -2 nije pronađen!"));
	}

}
