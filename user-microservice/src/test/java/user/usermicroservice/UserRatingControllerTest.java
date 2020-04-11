package user.usermicroservice;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import user.usermicroservice.DTO.UserRatingDTO;
import user.usermicroservice.Models.User;
import user.usermicroservice.Servisi.UserServis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserRatingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserServis userServis;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void azurirajStrip() throws Exception {
		UserRatingDTO urinfo = new UserRatingDTO(Long.valueOf(1), 0, 0);
		mockMvc.perform(
				put("/user/update-rating").contentType(MediaType.APPLICATION_JSON).content(asJsonString(urinfo)))
				.andExpect(status().isOk());
	}

}
