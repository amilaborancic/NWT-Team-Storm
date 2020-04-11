package comicbook.microsservice.comicbookmicroservice.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import io.swagger.models.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StripRatingControllerTest {

	@Autowired
	private MockMvc MockMVC;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void azurirajStrip() throws Exception {
		StripRatingInfo srinfo = new StripRatingInfo(Long.valueOf(2), 5, 2.7);
		MockMVC.perform(
				put("/strip/update-rating").contentType(MediaType.APPLICATION_JSON).content(asJsonString(srinfo)))
				.andExpect(status().isOk());
	}

	@Test
	public void komentariStripa() throws Exception {
		MockMVC.perform(MockMvcRequestBuilders.get("/strip/komentari/{id}", 1).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

}
