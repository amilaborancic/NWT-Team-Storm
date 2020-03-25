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



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RatingServisTest {
	 
	 @Autowired
	 private MockMvc mockMvc;

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
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	 }
	 
	 @Test
	 public void ratingsByStrip () throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/rating-stripa/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	 }
	 
	
	 @Test
	 public void addRating () throws Exception {
		 
		 mockMvc.perform(post("/dodaj-rating").param("strip_id", "1")
			      .param("korisnik_id", "1")
		 		  .param("ocjena", "5")
				  .param("komentar", "super je"))
		 		  .andDo(print()).andExpect(status().isOk());

	 }
	 

}
