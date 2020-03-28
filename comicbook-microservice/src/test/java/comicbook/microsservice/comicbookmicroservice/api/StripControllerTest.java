package comicbook.microsservice.comicbookmicroservice.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
class StripControllerTest {

    @Autowired
    private MockMvc MockMVC;

    @Test
    void sviStripovi() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/svi").param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void jedanStrip() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/?id_strip=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void stripoviPoAutoru() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-autor")
                .param("ime", "Stan")
                .param("prezime", "")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void stripoviPoIzdavacu() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-izdavac")
                .param("id_izdavac", "2")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-izdavac")
                .param("id_izdavac", "1")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-izdavac")
                .param("id_izdavac", "3")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void stripoviPoZanru() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-zanr")
                .param("id_zanr", "1")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void stripoviPoNazivu() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-naziv")
                .param("naziv", "batman")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        MockMVC.perform(MockMvcRequestBuilders.get("/strip/trazi-naziv")
                .param("naziv", "bdfdbg")
                .param("brojStranice", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
/*
    @Test
    void dodajStrip() throws Exception {
        Strip novi = new Strip("Test", "Testni opis", "svdvdds", 0.0, 0, null, 1L, 1L);
        Gson gson = new Gson();
        String json = gson.toJson(novi);

        MockMVC.perform(MockMvcRequestBuilders.post("/strip/noviStrip")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("7"));
    }*/
}