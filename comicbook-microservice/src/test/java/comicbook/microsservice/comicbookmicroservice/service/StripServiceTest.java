package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class StripServiceTest {

    @Autowired
    StripService stripService;
    @Autowired
    AutorService autorService;

    @Test
    void sviStripovi() {
        assertThat(stripService.sviStripovi(0, 5)).size().isEqualTo(5);
        assertThat(stripService.sviStripovi(1, 5)).size().isEqualTo(1);
        assertThat(stripService.sviStripovi(2,5)).size().isEqualTo(0);
    }

    @Test
    void jedanStrip() {
        //test za exception
        ApiRequestException thrown = assertThrows(
                ApiRequestException.class,
                ()->stripService.jedanStrip((long) 123),
                "Trebalo je baciti ali nije bacilo"
        );
        assertTrue(thrown.getMessage().contains("ne postoji"));
        assertThat(stripService.jedanStrip((long) 1).getId()).isEqualTo((long) 1);
        assertThat(stripService.jedanStrip((long) 1).getId()).isNotNull();
    }

    @Test
    void stripoviPoAutoru() {
        //kada se unesu i ime i prezime
        assertThat(stripService.stripoviPoAutoru("Stan", "Lee", 0, 5)).size().isEqualTo(2);

        //kada se ime unese polovicno
        assertThat(stripService.stripoviPoAutoru("Dav", "", 0, 5)).size().isEqualTo(1);

        //kada se unese prezime polovicno
        assertThat(stripService.stripoviPoAutoru("", "Ben", 0, 5)).size().isEqualTo(1);

        //kada se unese ime, a prezime je prazno
        assertThat(stripService.stripoviPoAutoru("Scott", null, 0, 5)).size().isEqualTo(2);

        //kada se unese prezime, a ime je prazno
        assertThat(stripService.stripoviPoAutoru(null, "Sny", 0, 5)).size().isEqualTo(2);

        //kada su i ime i prezime prazni
        assertThat(stripService.stripoviPoAutoru(null, null, 0, 5)).size().isEqualTo(0);
    }

    @Test
    void stripoviPoIzdavacu() {
        Long id_marvel = (long) 1;
        Long id_dc = (long) 2;
        Long id_mirage = (long) 3;
        assertThat(stripService.stripoviPoIzdavacu(id_marvel, 0, 5)).size().isEqualTo(2);
        assertThat(stripService.stripoviPoIzdavacu(id_dc, 0, 5)).size().isEqualTo(3);
        assertThat(stripService.stripoviPoIzdavacu(id_mirage, 0, 5)).size().isEqualTo(1);
    }

    @Test
    void stripoviPoZanru() {
        Long id_akcija = (long) 1;
        Long id_horor = (long) 2;
        Long id_avantura = (long) 3;
        assertThat(stripService.stripoviPoZanru(id_akcija, 0, 5)).size().isEqualTo(5);
        assertThat(stripService.stripoviPoZanru(id_horor, 0, 5)).size().isEqualTo(0);
        assertThat(stripService.stripoviPoZanru(id_avantura, 0, 5)).size().isEqualTo(0);

    }

    @Test
    void stripoviPoNazivu() {
        //kada je naziv prazan
        ApiRequestException bacenIzuzetak = assertThrows(
                ApiRequestException.class,
                ()->stripService.stripoviPoNazivu("", 0, 5),
                "Trebalo je baciti ali nije bacilo"
        );
        assertTrue(bacenIzuzetak.getMessage().contains("barem tri slova"));
        //kada je naziv identican
        assertThat(stripService.stripoviPoNazivu("Batman Detective Comics", 0, 5)).size().isEqualTo(1);
        //kada je naziv polovican
        assertThat(stripService.stripoviPoNazivu("iron", 0, 5)).size().isEqualTo(1);
        //kada je naziv null (nije ni poslan)
        ApiRequestException bacenIzuzetakNull = assertThrows(
                ApiRequestException.class,
                ()->stripService.stripoviPoNazivu(null, 0, 5),
                "Trebalo je baciti ali nije bacilo"
        );
        assertTrue(bacenIzuzetakNull.getMessage().contains("mora biti"));
    }

    @Test
    void dodajStrip() {
        List<Autor> autori = autorService.sviAutori().subList(0,2);
        Strip novi = new Strip("Test", "testni strip", "slikica", 0.0, 0, null, (long) 1, (long) 2, autori);
        assertThat(stripService.dodajStrip(novi)).isEqualTo((long) 7);
        //kada su autori prazan niz
        ApiRequestException nemaAutora = assertThrows(
                ApiRequestException.class,
                ()->stripService.dodajStrip(new Strip("Test", "testni strip", "slikica", 0.0, 0, null, (long) 1, (long) 2, new ArrayList<>())),
                "Trebalo je baciti ali nije bacilo"
        );
        assertTrue(nemaAutora.getMessage().contains("mora imati"));
        //kada se ne posalje naziv
        ApiRequestException nemaNaziva = assertThrows(
                ApiRequestException.class,
                ()->stripService.dodajStrip(new Strip("", "testni strip", "slikica", 0.0, 0, null, (long) 1, (long) 2, autori)),
                "Trebalo je baciti ali nije bacilo"
        );
        assertTrue(nemaNaziva.getMessage().contains("mora imati naziv"));
    }
}