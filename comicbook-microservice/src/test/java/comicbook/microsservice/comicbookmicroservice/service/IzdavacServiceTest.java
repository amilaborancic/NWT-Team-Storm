package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class IzdavacServiceTest {

    @Autowired
    IzdavacService izdavacService;
    @Test
    void sviIzdavaci() {
        assertThat(izdavacService.sviIzdavaci()).size().isEqualTo(3);
    }

    @Test
    void dodajIzdavaca() {
        //sve uredu
        assertThat(izdavacService.dodajIzdavaca(new Izdavac("dark horse comics"))).isEqualTo(4);
        //ne posalje se naziv
        ApiRequestException nemaNaziva = assertThrows(
                ApiRequestException.class,
                ()->izdavacService.dodajIzdavaca(new Izdavac("")),
                "Trebalo bi baciti"
        );
        assertThat(nemaNaziva.getMessage().contains("ne smije"));
    }
}