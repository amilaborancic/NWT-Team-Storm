package catalogue.microsservice.cataloguemicroservice.api;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Transactional
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
	public void dodajKatalogUListu() throws Exception{
		Katalog kat = new Katalog("Tralala", (long) 2);
		mockMvc.perform(put("/korisnik/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(kat)))
				.andExpect(status().isOk());
	}

}
