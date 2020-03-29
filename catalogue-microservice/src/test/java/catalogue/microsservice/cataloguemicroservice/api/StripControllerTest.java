package catalogue.microsservice.cataloguemicroservice.api;

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


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StripControllerTest {
	@Autowired 
	private MockMvc mockMvc;
	
	@Test
	public void sviIzJednogKataloga() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders
						 .get("/katalog/iz-kataloga/2?brojStranice=1")
					     .accept(MediaType.APPLICATION_JSON))
					     .andDo(print())
					     .andExpect(status().isOk());	 	
	}
}
