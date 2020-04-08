package user.usermicroservice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.DTO.KatalogDeleteDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserCatalogueControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void kreirajKatalogKorisniku() throws Exception{
        KatalogDTO noviKatalog = new KatalogDTO("testni katalog", (long) 1);
        mockMvc.perform(put("/katalog/create-katalog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(noviKatalog)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void obrisiKorisnikovKatalog() throws Exception {
        KatalogDeleteDTO katalogKojiSeBrise = new KatalogDeleteDTO((long) 1, (long) 2);
        mockMvc.perform(delete("/katalog/delete-katalog")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(katalogKojiSeBrise)))
                .andDo(print()).andExpect(status().isOk());
    }
}