package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.service.KatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/katalog")
public class KatalogController {

    private int brojKatalogaNaStranici = 5;

    @Autowired
    KatalogService katalogService;

    //svi katalozi za jednog usera sa paginacijom
    @GetMapping(value="/svi")
    public List<Katalog> sviKatalozi(@Param("id_korisnik") Long id_korisnik, @Param("brojStranice") int brojStranice){
        return katalogService.sviKatalozi(id_korisnik, brojStranice, brojKatalogaNaStranici);
    }

    //kreiranje kataloga za nekog usera
    @PostMapping(value="/novi")
    public Long kreirajKatalog(@RequestBody Katalog katalog){
        katalogService.kreirajKatalog(katalog);
        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8080/katalog/update", katalog);
        return katalog.getId();
    }

    //dodavanje stripa u katalog uz provjeru da li je prethodno dodan
    @PutMapping(value="/dodavanje-stripa")
    public void dodajStripUKatalog(@RequestBody Map<String, Long> requestBody){
        Long id_strip = requestBody.get("id_strip");
        Long id_katalog = requestBody.get("id_katalog");
        katalogService.dodajStripUKatalog(id_strip, id_katalog);
    }

    //jedan katalog
    @GetMapping(value="/jedan")
    public Optional<Katalog> getKatalog(@Param("id_katalog") Long id_katalog){
        return katalogService.getKatalog(id_katalog);
    }

    //brisanje stripa iz kataloga
    @DeleteMapping(value="/brisanje-stripa")
    public void obrisiStrip(@RequestBody Map<String, Long> body){
        Long id_katalog = body.get("id_katalog");
        Long id_strip = body.get("id_strip");
       katalogService.obrisiStrip(id_strip, id_katalog);
    }

    //brisanje kataloga
    @DeleteMapping(value="/brisanje-kataloga")
    public void obrisiKatalog(@Param("id_katalog") Long id_katalog){
        katalogService.obrisiKatalog(id_katalog);
    }
}
