package com.example.ratingservice;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.modeli.User;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import com.example.ratingservice.servisi.UserServis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		assertThat(ratingServis.getOne(1L).getId()).isEqualTo(Long.valueOf(1));
	}
	
	@Test
	public void findByKorisnik()throws Exception {
		assertThat(ratingServis.findByKorisnik(1L).size()).isNotEqualTo(0);
	}
	
	@Test
	public void findByStrip()throws Exception {
		assertThat(ratingServis.findByStrip(1L).size()).isNotEqualTo(0);
	}
	
	
	@Test
	public void save() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(1L),stripServis.getOne(2L),2,"los strip");
		ratingServis.save(rating);
		assertThat(ratingServis.getOne(rating.getId()).getOcjena()).isEqualTo(2);
	}

	@Test
	public void addRating() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(1L),stripServis.getOne(2L),2,"los strip");
		ratingServis.addRating(rating);
		assertThat(ratingServis.getOne(rating.getId()).getOcjena()).isEqualTo(2);
		assertThat(ratingServis.getOne(rating.getId()).getKomentar()).isEqualTo("los strip");
		assertThat(ratingServis.getOne(rating.getId()).getKorisnik().getId()).isEqualTo(Long.valueOf(1));
		assertThat(ratingServis.getOne(rating.getId()).getStrip().getId()).isEqualTo(Long.valueOf(2));
	}
	
	@Test
	public void addRatingInvalidKorisnik() throws Exception{
		User korisnik=new User();
		korisnik.setId(9999L);
		Rating rating=new Rating(korisnik,stripServis.getOne(1L),2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Id korisnika je pogre?an!"));
	}
	
	@Test
	public void addRatingInvalidStrip() throws Exception{
		Strip strip=new Strip();
		strip.setId(9999L);
		Rating rating=new Rating(korisnikServis.getOne(1L),strip,2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Id stripa je pogre?an!"));
	}
	
	@Test
	public void addRatingDuplicate() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(1L),stripServis.getOne(1L),2,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Korisnik je ve? ostavio rating na dati strip"));
	}
	
	@Test
	public void addRatingInvalidOcjena() throws Exception{
		Rating rating=new Rating(korisnikServis.getOne(1L),stripServis.getOne(2L),9999,"los strip");
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.addRating(rating));
		assertTrue(exception.getMessage().contains("Ocjena mora bit u rasponu od 1 do 5!"));
	}
	
	
	@Test
	public void commentsByStripInvalidStrip() {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.commentsByStrip((long) -2));
		assertTrue(exception.getMessage().contains("Strip sa id -2 nije prona?en!"));
	}
	
	@Test
	public void getOneInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.getOne(999L));
		assertTrue(exception.getMessage().contains("Rating sa id 999 nije prona?en!"));
	}

	@Test
	public void findByKorisnikInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class,
				() -> ratingServis.findByKorisnik(999L));
		assertTrue(exception.getMessage().contains("Korisnik sa id 999 nije prona?en!"));
	}


	@Test
	public void findByStripInvalidId() throws Exception {
		Exception exception = assertThrows(ApiRequestException.class, () -> ratingServis.findByStrip((long) -2));
		assertTrue(exception.getMessage().contains("Strip sa id -2 nije prona?en!"));
	}

}
