package com.example.ratingservice;

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

public class KorisnikKontrolerTest {

	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Test
	 public void all() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		  .get("/korisnici")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	 }
	 
	 @Test
	 public void userById () throws Exception {

		mockMvc.perform( MockMvcRequestBuilders
	      .get("/korisnik/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	 }
	 
	 @Test
	 public void save() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		  .get("/dodaj-korisnika")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	 }
	 
	 
	 
}
