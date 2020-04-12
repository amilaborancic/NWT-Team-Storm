package com.example.ratingservice;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.UserServis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)

public class UserServisTest {

	@Autowired
	UserServis korisnikServis;
	@Autowired
	RatingServis ratingServis;
	
	@Test
	public void getOne() throws Exception{
		//sve okej
		User korisnik=korisnikServis.getOne(1L);
		assertThat(korisnik.getId()).isEqualTo(1);
		//korisnik ne postoji
		ApiRequestException nemaUsera = assertThrows(
				ApiRequestException.class,
				()->korisnikServis.getOne(121L)
		);
		assertThat(nemaUsera.getMessage().contains("nije pronadjen"));
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
	
	