
package com.example.ratingservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class StripKontrolerTest {

	 @Autowired
	 private MockMvc mockMvc;
	
	 @Test
	 public void all() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders
		  .get("/stripovi")
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk());
	 }
	 
	 @Test
	 public void stripById () throws Exception {
	 	//sve okej
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/strip/{id}", 1)
	      .accept(MediaType.APPLICATION_JSON))
	      .andDo(print())
	      .andExpect(status().isOk())
	      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
		//nepostojeci strip
		 mockMvc.perform( MockMvcRequestBuilders
				 .get("/strip/{id}", 124)
				 .accept(MediaType.APPLICATION_JSON))
				 .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Strip sa id 124 nije pronadjen!"));
	 }

}
