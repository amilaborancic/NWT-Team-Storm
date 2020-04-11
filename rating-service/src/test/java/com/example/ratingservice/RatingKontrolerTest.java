package com.example.ratingservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.ratingservice.modeli.User;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.servisi.UserServis;
import com.example.ratingservice.servisi.RatingServis;
import com.example.ratingservice.servisi.StripServis;
import com.fasterxml.jackson.databind.ObjectMapper;



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
		 mockMvc.perform(MockMvcRequestBuilders
		  .get("/ratings")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	 }

	 @Test
	 public void ratingById () throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/rating/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	 }
	 
	 @Test
	 public void ratingsByUser () throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/rating-korisnika/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
	 }
	 
	 @Test
	 public void ratingsByStrip () throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/rating-stripa/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1));
	 }
	 
	
	 @Test
	 public void addRating () throws Exception {
		 
		  User korisnik=korisnikServis.getOne(Long.valueOf(1));
		  Strip strip=stripServis.getOne(Long.valueOf(2));
		  Rating rating=new Rating(korisnik,strip,1,"lose");

		mockMvc.perform(post("/dodaj-rating")
				  .contentType(MediaType.APPLICATION_JSON)
		 		  .content(asJsonString(rating)))
				  .andExpect(status().isOk());
	 }
	 
	 
	 
	 
	 
	 

}
