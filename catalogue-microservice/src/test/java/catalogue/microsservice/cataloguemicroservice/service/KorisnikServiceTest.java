package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
class KorisnikServiceTest {

    @Autowired
    KorisnikService korisnikService;

    @Test
    void dodajKorisnika() {
        //sve ok
        assertThat(korisnikService.dodajKorisnika(new Korisnik())).isEqualTo(3);
    }

    @Test
    void dodajKatalogUListu() {
        //sve ok
        assertThat(korisnikService.dodajKatalogUListu(new Katalog("Testic", (long)1)));
        //korisnik ne postoji
        ApiRequestException nemaKorisnika = assertThrows(
                ApiRequestException.class,
                ()->korisnikService.dodajKatalogUListu(new Katalog("ndiadns", (long) 123)),
                "Bacaj bruda"
        );
        assertThat(nemaKorisnika.getMessage().contains("ne postoji"));
    }

    @Test
    void jedanKorisnik(){
        //sve ok
        assertThat(korisnikService.jedanKorisnik((long)1).getId()).isEqualTo(1);
        //korisnik ne postoji
        ApiRequestException nemaKorisnika = assertThrows(
                ApiRequestException.class,
                ()->korisnikService.jedanKorisnik((long) 123),
                "Bacaj bruda"
        );
        assertThat(nemaKorisnika.getMessage().contains("ne postoji"));
    }
}