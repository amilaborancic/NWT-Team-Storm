package comicbook.microsservice.comicbookmicroservice.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
class StripControllerTest {

    @Autowired
    private MockMvc MockMVC;

    @Test
    void sviStripovi() throws Exception {
        MockMVC.perform(MockMvcRequestBuilders.get("/strip/svi?brojStranice=0"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void jedanStrip() {
    }

    @Test
    void stripoviPoAutoru() {
    }

    @Test
    void stripoviPoIzdavacu() {
    }

    @Test
    void stripoviPoZanru() {
    }

    @Test
    void stripoviPoNazivu() {
    }

    @Test
    void dodajStrip() {
    }
}