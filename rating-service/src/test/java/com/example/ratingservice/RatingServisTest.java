package com.example.ratingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

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

	@Test
	public void addRating() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(Long.valueOf(1)),stripServis.getOne(Long.valueOf(2)),2,"los strip");
		ratingServis.addRating(rating);
		assertThat(ratingServis.getOne(rating.getId()).getOcjena()).isEqualTo(2);
		assertThat(ratingServis.getOne(rating.getId()).getKomentar()).isEqualTo("los strip");
		assertThat(ratingServis.getOne(rating.getId()).getKorisnik().getId()).isEqualTo(Long.valueOf(1));
		assertThat(ratingServis.getOne(rating.getId()).getStrip().getId()).isEqualTo(Long.valueOf(2));
	}
	

	@Test(expected = ApiRequestException.class)
	public void getOneExc() throws Exception{
		 Rating r=ratingServis.getOne(Long.valueOf(999));
	}
	
	@Test(expected = ApiRequestException.class)
	public void findByKorisnikExc() throws Exception{
		 List<Rating> r=ratingServis.findByKorisnik(Long.valueOf(999));
	}
	
	@Test(expected = ApiRequestException.class)
	public void saveExc() throws Exception{
		 Rating r=new Rating(korisnikServis.getOne(Long.valueOf(-1234)),stripServis.getOne(Long.valueOf(-1)),5,"lal");
	}
	
	@Test(expected = ApiRequestException.class)
	public void findByStripExc() throws Exception{
		 List<Rating> r=ratingServis.findByStrip(Long.valueOf(999));
	}
	
}
