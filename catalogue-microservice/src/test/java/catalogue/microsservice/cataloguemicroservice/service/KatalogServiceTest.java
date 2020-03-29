package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class KatalogServiceTest {

    @Autowired
    KatalogService katalogService;

    @Test
    void sviKatalozi() {
        //sve okej
        assertThat(katalogService.sviKatalozi((long) 1, 0, 5)).size().isEqualTo(5);
        //nepostojeci korisnik
        ApiRequestException nemaUsera = assertThrows(
                ApiRequestException.class,
                ()->katalogService.sviKatalozi((long)123, 0, 5),
                "Trebalo bi baciti"
        );
        assertThat(nemaUsera.getMessage().contains("ne postoji"));
    }

    @Test
    void kreirajKatalog() {
        //sve okej
        assertThat(katalogService.kreirajKatalog(new Katalog("Testni katalog", (long) 2))).isEqualTo(9);
        //ne postoji korisnik
        ApiRequestException nemaUsera = assertThrows(
                ApiRequestException.class,
                ()->katalogService.kreirajKatalog(new Katalog("blabla", (long)1123)),
                "Trebalo bi baciti"
        );
        assertThat(nemaUsera.getMessage().contains("ne postoji"));
        //naziv prazan
        ApiRequestException nemaNaziva = assertThrows(
                ApiRequestException.class,
                ()->katalogService.kreirajKatalog(new Katalog("", (long) 2)),
                "Trebalo bi baciti"
        );
        assertThat(nemaNaziva.getMessage().contains("ne smije"));
    }

    @Test
    void dodajStripUKatalog() {
        //sve ok
        Katalog katalog = katalogService.getKatalog((long) 1);
        assertThat(katalogService.dodajStripUKatalog(katalog.getId(), (long) 1)).isEqualTo(1);
        //ne postoji katalog u bazi
        ApiRequestException nemaKataloga = assertThrows(
                ApiRequestException.class,
                ()->katalogService.dodajStripUKatalog((long) 1, (long) 221),
                "Trebalo bi baciti"
        );
        assertThat(nemaKataloga.getMessage().contains("ne postoji"));
        //ne postoji strip u bazi
        ApiRequestException nemaStripa = assertThrows(
                ApiRequestException.class,
                ()->katalogService.dodajStripUKatalog((long) 112, (long) 1),
                "Trebalo bi baciti"
        );
        assertThat(nemaStripa.getMessage().contains("ne postoji"));
        //strip je vec dodan u katalog
        ApiRequestException postojiUKatalogu = assertThrows(
                ApiRequestException.class,
                ()->katalogService.dodajStripUKatalog((long) 1, (long) 3),
                "Trebalo bi baciti"
        );
        assertThat(postojiUKatalogu.getMessage().contains("vec dodan"));

    }

    @Test
    void getKatalog() {
        //nema kataloga
        ApiRequestException nemaKataloga = assertThrows(
                ApiRequestException.class,
                ()->katalogService.getKatalog((long) 221),
                "Trebalo bi baciti"
        );
        assertThat(nemaKataloga.getMessage().contains("ne postoji"));
    }

    @Test
    void obrisiStrip() {
        //sve okej
        assertThat(katalogService.obrisiStrip((long) 1, (long) 3)).isTrue();
        //nema kataloga
        ApiRequestException nemaKataloga = assertThrows(
                ApiRequestException.class,
                ()->katalogService.obrisiStrip((long) 1,(long) 221),
                "Trebalo bi baciti"
        );
        assertThat(nemaKataloga.getMessage().contains("ne postoji"));
    }

    @Test
    void obrisiKatalog() {
        assertThat(katalogService.getKatalog((long) 1)).toString().contains("uspjesno");
    }
}