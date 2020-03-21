package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    KatalogRepository katalogRepository;
    @Autowired
    KorisnikRepository korisnikRepository;

    //svi katalozi za jednog usera
    @GetMapping(value="/all")
    public List<Katalog> sviKatalozi(@Param("id") Long id){
        return korisnikRepository.getOne(id).getKatalozi();
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
    @GetMapping(value="/user-catalogue")
    public List<Strip> stripoviUKatalogu(@Param("id") Long id){
        return katalogRepository.getOne(id).getStripovi();
    }


    //pomocna funkcija za racunanje paginacije
    private Pair<Integer, Integer> Paginacija(int brojStranice, int ukupnoStripova){
        var ukupnoStranica = ceil((double)ukupnoStripova/(double)brojStripovaNaStranici);
        var prvi_indeks = (brojStranice - 1)*brojStripovaNaStranici;
        var zadnji_indeks = prvi_indeks + brojStripovaNaStranici;
        if(brojStranice == ukupnoStranica || ukupnoStripova < brojStripovaNaStranici) zadnji_indeks = toIntExact(ukupnoStripova);
        return Pair.of(prvi_indeks, zadnji_indeks);
    }

}
