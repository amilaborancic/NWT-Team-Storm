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

public class StripServisTest {

	@Autowired
	StripServis stripServis;
	@Autowired
	RatingServis ratingServis;
	
	@Test
	public void getOne() throws Exception{
		Strip strip=stripServis.getOne(Long.valueOf(1));
		assertThat(strip.getId()).isEqualTo(1);
	}

	/* ponovo
	@Test
	public void save()throws Exception {
		
		Strip strip=new Strip();
		strip.setNaziv("lalalalala");
		stripServis.save(strip);
		assertThat(stripServis.getOne(strip.getId()).getNaziv()).isEqualTo("lalalalala");
	}
	*/

	@Test
	public void findAll()throws Exception {
		assertThat(stripServis.findAll().size()).isNotEqualTo(0);
	}
	
	@Test(expected = ApiRequestException.class)
	public void getOneExc() throws Exception{
		 Strip s=stripServis.getOne(Long.valueOf(999));
	}

}
	
	