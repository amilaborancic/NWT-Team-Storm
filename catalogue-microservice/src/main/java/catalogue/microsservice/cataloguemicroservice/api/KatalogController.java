package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import static java.lang.Math.ceil;
import static java.lang.Math.toIntExact;

import java.util.List;

@RestController
@RequestMapping("/katalog")
public class KatalogController {

    private int brojStripovaNaStranici = 5;
    private int brojKatalogaNaStranici = 2;

    @Autowired
    KatalogRepository katalogRepository;
    @Autowired
    KorisnikRepository korisnikRepository;

    //svi katalozi za jednog usera sa paginacijom
    @GetMapping(value="/all")
    public List<Katalog> sviKatalozi(@Param("id") Long id, @Param("brojStranice") int brojStranice){
        return katalogRepository.findByIdKorisnik(id, PageRequest.of(brojStranice, brojKatalogaNaStranici));
    }

    //kreiranje kataloga za jednog usera
    @PostMapping(value="/create")
    public Long kreirajKatalog(@RequestBody Katalog katalog, @Param("id_korisnik") Long id_korisnik){
        katalogRepository.save(katalog);
        //povezivanje s korisnikom
        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8080/korisnik/update?id_korisnik="+id_korisnik, katalog);
        return katalog.getId();
    }

    //stripovi unutar jednog kataloga sa paginacijom
   /* @GetMapping(value="/user-catalogue")
    public List<Strip> stripoviUKatalogu(@Param("id") Long id, @Param("brojStranice") int brojStranice){
        return katalogRepository.findById(id, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }*/


}
