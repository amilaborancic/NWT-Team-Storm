package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/katalog")
public class KatalogController {
    @Autowired
    KatalogRepository katalogRepository;
    @Autowired
    KorisnikRepository korisnikRepository;

    //svi katalozi za 1 usera
    @GetMapping(value="/all")
    public List<Katalog> sviKatalozi(@Param("id") Long id){
        return korisnikRepository.getOne(id).getKatalozi();
    }

    @PostMapping(value="/create")
    public Long kreirajKatalog(@RequestBody Katalog katalog, @Param("id_korisnik") Long id_korisnik){
        //dodavanje kataloga u bazu
        katalogRepository.save(katalog);

        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8080/korisnik/update?id_korisnik="+id_korisnik, katalog);
        return katalog.getId();
    }
}
