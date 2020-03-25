package comicbook.microsservice.comicbookmicroservice.api;

import com.google.gson.Gson;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
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
@SpringBootTest
class AutorControllerTest {

    @Autowired
    private MockMvc MockMVC;

    @Test
    void sviAutori() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/autor/svi"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    void dodajAutora() throws Exception {
        Autor novi = new Autor("Testni", "Autor");
        Gson gson = new Gson();
        String noviAutor = gson.toJson(novi);
        MockMVC.perform(MockMvcRequestBuilders.post("/autor/novi")
                .contentType(MediaType.APPLICATION_JSON).content(noviAutor))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("8"));
    }
}