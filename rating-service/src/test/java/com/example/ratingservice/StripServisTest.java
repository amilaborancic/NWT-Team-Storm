package com.example.ratingservice;

import com.example.ratingservice.exception.ApiRequestException;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

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
		Strip strip = stripServis.getOne(1L);
		assertThat(strip.getId()).isEqualTo(1);
	}

	@Test
	public void findAll()throws Exception {
		assertThat(stripServis.findAll().size()).isNotEqualTo(0);
	}

	@Test(expected = ApiRequestException.class)
	public void getOneExc() throws Exception{
		Strip s = stripServis.getOne(999L);
	}

}
	
	