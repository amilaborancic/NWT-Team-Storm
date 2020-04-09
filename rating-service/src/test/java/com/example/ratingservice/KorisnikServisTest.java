package com.example.ratingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.UserServis;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)

public class KorisnikServisTest {

	@Autowired
	UserServis korisnikServis;
	@Autowired
	RatingServis ratingServis;
	
	@Test
	public void getOne() throws Exception{
		User korisnik=korisnikServis.getOne(Long.valueOf(1));
		assertThat(korisnik.getId()).isEqualTo(1);
	}
	

	@Test
	public void findAll()throws Exception {
		assertThat(korisnikServis.findAll().size()).isNotEqualTo(0);
	}
	

	@Test
	public void save()throws Exception {
		
		int vel1=korisnikServis.findAll().size();
		User korisnik=new User();
		korisnikServis.save(korisnik);
		int vel2=korisnikServis.findAll().size();
		assertThat(vel1).isNotEqualTo(vel2);
	}
	
	@Test(expected = ApiRequestException.class)
	public void getOneExc() throws Exception{
		 User k=korisnikServis.getOne(Long.valueOf(999));
	}
	
	
}
	
	