package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class AutorServiceTest {

    @Autowired
    AutorService autorService;

    @Test
    void sviAutori() {
        assertThat(autorService.sviAutori()).size().isEqualTo(7);
    }

    @Test
    void dodajAutora() {
        Autor autor = new Autor("neko", "nekic");
        //sve uredu
        assertThat(autorService.dodajAutora(autor)).isEqualTo((long) 8);
        //izostavi se ime
        ApiRequestException nemaImena = assertThrows(
                ApiRequestException.class,
                ()->autorService.dodajAutora(new Autor("", "nekic")),
                "trebalo baciti"
        );
        assertThat(nemaImena.getMessage().contains("ne smije"));
        //izostavi se prezime
        ApiRequestException nemaPrezimena = assertThrows(
                ApiRequestException.class,
                ()->autorService.dodajAutora(new Autor("neko", "")),
                "trebalo baciti"
        );
        assertThat(nemaPrezimena.getMessage().contains("ne smije"));
    }
}