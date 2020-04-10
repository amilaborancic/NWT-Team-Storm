package comicbook.microsservice.comicbookmicroservice.api;

import com.google.gson.Gson;
import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ZanrControllerTest {

    @Autowired
    private MockMvc MockMVC;

    @Test
    void sviZanrovi() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/zanr/svi"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void dodajZanr() throws Exception {
        Zanr novi = new Zanr("test");
        Gson gson = new Gson();
        String noviZanr = gson.toJson(novi);
        MockMVC.perform(MockMvcRequestBuilders.post("/zanr/novi")
                .contentType(MediaType.APPLICATION_JSON).content(noviZanr))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("4"));
    }
}