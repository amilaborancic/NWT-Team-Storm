package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class ZanrServiceTest {

    @Autowired
    ZanrService zanrService;

    @Test
    void sviZanrovi() {
        assertThat(zanrService.sviZanrovi()).size().isEqualTo(3);
    }

    @Test
    void dodajZanr() {
        //sve okej
        assertThat(zanrService.dodajZanr(new Zanr("romansa"))).isEqualTo(4);
        //nema naziva
        ApiRequestException nemaNaziva = assertThrows(
                ApiRequestException.class,
                ()->zanrService.dodajZanr(new Zanr("")),
                "Trebalo bi baciti"
        );
        assertThat(nemaNaziva.getMessage().contains("ne smije"));
    }
}