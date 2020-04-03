package com.example.ratingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.servisi.KorisnikServis;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RatingServisTest {

	@Autowired
	StripServis stripServis;
	
	@Autowired
	KorisnikServis korisnikServis;
	
	@Autowired
	RatingServis ratingServis;
	
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
