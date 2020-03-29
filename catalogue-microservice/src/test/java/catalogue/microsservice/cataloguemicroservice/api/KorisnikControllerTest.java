package catalogue.microsservice.cataloguemicroservice.api;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class KorisnikControllerTest {
	@Autowired 
	private MockMvc mockMvc;
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Test
	public void dodajKorisnika() throws Exception{
		Korisnik korisnik=new Korisnik();
		mockMvc.perform(post("/katalog/novi-korisnik")
				.contentType(MediaType.APPLICATION_JSON)
	            .content(asJsonString(korisnik)))
				.andExpect(status().isOk());
	}

	@Test
	public void dodajKatalogUListu() throws Exception{

		mockMvc.perform(put("/katalog/update")
				.param("id_korisnik","1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(
				    new Katalog()
				))).andExpect(status().isOk());
	}

}
