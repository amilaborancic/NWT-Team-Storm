package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.service.KatalogService;
import catalogue.microsservice.cataloguemicroservice.service.KorisnikService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/katalog")
public class KatalogController {

    private int brojKatalogaNaStranici = 5;

    @Autowired
    KatalogService katalogService;
    @Autowired
    KorisnikService korisnikService;

    @Autowired
    RestTemplate restTemplate;

    //svi katalozi za jednog usera sa paginacijom
    @GetMapping(value="/svi")
    public List<Katalog> sviKatalozi(@Param("id_korisnik") Long id_korisnik, @Param("brojStranice") int brojStranice){
        return katalogService.sviKatalozi(id_korisnik, brojStranice, brojKatalogaNaStranici);
    }

    //kreiranje kataloga za nekog usera
    @PostMapping(value="/novi")
    public Long kreirajKatalog(@RequestBody Katalog katalog){
        katalogService.kreirajKatalog(katalog);
        restTemplate.put("http://catalogue-service/korisnik/update", katalog);
        return katalog.getId();
    }

    //dodavanje stripa u katalog uz provjeru da li je prethodno dodan
    @PutMapping(value="/dodavanje-stripa")
    public String dodajStripUKatalog(@RequestBody Map<String, Long> requestBody){
        Long id_strip = requestBody.get("id_strip");
        Long id_katalog = requestBody.get("id_katalog");
        katalogService.dodajStripUKatalog(id_strip, id_katalog);
        return "Strip je uspješno dodan u katalog!";
    }

    //jedan katalog
    @GetMapping(value="/jedan")
    public Katalog getKatalog(@Param("id_katalog") Long id_katalog){
        return katalogService.getKatalog(id_katalog);
    }

    //brisanje stripa iz kataloga
    @DeleteMapping(value="/brisanje-stripa")
    public String obrisiStrip(@RequestBody Map<String, Long> body){
        Long id_katalog = body.get("id_katalog");
        Long id_strip = body.get("id_strip");
        if(katalogService.obrisiStrip(id_strip, id_katalog)) return "Strip je uspješno obrisan iz kataloga!";
        return "Strip sa id-jem " + id_strip + " se ne nalazi u katalogu!";
    }

    //brisanje kataloga
    @DeleteMapping(value="/brisanje-kataloga")
    public String obrisiKatalog(@RequestBody Map<String, Long> katalogKojiSeBrise){
        Long id_katalog = katalogKojiSeBrise.get("idKatalog");
        return katalogService.obrisiKatalog(id_katalog);
    }
}
