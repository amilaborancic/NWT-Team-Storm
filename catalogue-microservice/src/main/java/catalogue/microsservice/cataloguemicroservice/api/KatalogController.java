package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/katalog")
public class KatalogController {

    private int brojKatalogaNaStranici = 5;

    @Autowired
    KatalogRepository katalogRepository;
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    StripRepository stripRepository;

    //svi katalozi za jednog usera sa paginacijom
    @GetMapping(value="/svi")
    public List<Katalog> sviKatalozi(@Param("id_korisnik") Long id_korisnik, @Param("brojStranice") int brojStranice){
        return katalogRepository.findByIdKorisnik(id_korisnik, PageRequest.of(brojStranice, brojKatalogaNaStranici));
    }

    //kreiranje kataloga za nekog usera
    @PostMapping(value="/novi")
    public Long kreirajKatalog(@RequestBody Katalog katalog, @Param("id_korisnik") Long id_korisnik){
        katalogRepository.save(katalog);
        //povezivanje s korisnikom
        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8080/katalog/update?id_korisnik="+id_korisnik, katalog);
        return katalog.getId();
    }

    //dodavanje stripa u katalog uz provjeru da li je prethodno dodan
    @PutMapping(value="/dodavanje-stripa")
    public void dodajStripUKatalog(@Param("id_strip") Long id_strip, @Param("id_katalog") Long id_katalog){
        Katalog katalog = katalogRepository.getOne(id_katalog);
        List<Strip> stripoviUKatalogu = katalog.getStripovi();
        if(stripoviUKatalogu.stream().map(Strip::getIdStrip).anyMatch(id_strip::equals)) return;
        stripoviUKatalogu.add(stripRepository.getOne(id_strip));
        katalog.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalog);
    }

    //jedan katalog
    @GetMapping(value="/jedan")
    public Optional<Katalog> getKatalog(@Param("id_katalog") Long id_katalog){
        return katalogRepository.findById(id_katalog);
    }

    //brisanje stripa iz kataloga
    @DeleteMapping(value="/brisanje-stripa")
    public void obrisiStrip(@Param("id_strip") Long id_strip, @Param("id_katalog") Long id_katalog){
        Katalog katalog = katalogRepository.getOne(id_katalog);
        List<Strip> stripoviUKatalogu = katalog.getStripovi();
        stripoviUKatalogu.removeIf(strip->strip.getIdStrip() == id_strip);
        katalog.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalog);
    }

    //brisanje kataloga
    @DeleteMapping(value="/brisanje-kataloga")
    public void obrisiKatalog(@Param("id_katalog") Long id_katalog){
        katalogRepository.delete(katalogRepository.getOne(id_katalog));
    }
}
