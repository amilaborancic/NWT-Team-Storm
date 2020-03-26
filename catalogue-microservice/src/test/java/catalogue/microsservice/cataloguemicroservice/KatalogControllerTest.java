package catalogue.microsservice.cataloguemicroservice;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KatalogControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void sviKatalozi() throws Exception {
		mockMvc.perform(get("/katalog/svi").param("id_korisnik", "1")
			      .param("brojStranice", "5"))
		 		  .andDo(print()).andExpect(status().isOk());
	 }
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	
	@Test
	public void getKatalog() throws Exception {
		mockMvc.perform(get("/katalog/jedan").param("id_katalog", "1"))
		 		  .andDo(print()).andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	 }


	@Test
	public void obrisiKatalog() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/katalog/brisanje-kataloga/?id_katalog=1"))
        .andExpect((status().isOk()));
	 }
	
	@Test
	public void dodajStripUKatalog() throws Exception {
		mockMvc.perform(put("/katalog/dodavanje-stripa").param("id_strip", "1")
			      .param("id_katalog", "2"))
		 		  .andDo(print()).andExpect(status().isOk());
	 }
	
	@Test
	public void obrisiStrip() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/katalog/brisanje-stripa/?id_strip=1&id_katalog=2"))
        .andExpect((status().isOk()));
	 }
	
	@Test
	public void kreirajKatalog() throws Exception{

		mockMvc.perform(post("/katalog/novi")
				.param("id_korisnik","1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(
				    new Katalog()
				))).andExpect(status().isOk());
	}
}
	

	
