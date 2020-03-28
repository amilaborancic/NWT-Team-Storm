package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Collections;
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
    public Long kreirajKatalog(@RequestBody Katalog katalog){
        Long idKorisnik = katalog.getIdKorisnik();
        String naziv = katalog.getNaziv();
        //provjera postoji li user
        Optional<Korisnik> korisnik = korisnikRepository.findById(idKorisnik);
        if(korisnik.isEmpty()) throw new ApiRequestException("Korisnik sa id-jem " + idKorisnik + " ne postoji!");
        //provjera da li je naziv prazan
        if(naziv.equals("")) throw new ApiRequestException("Naziv kataloga ne smije biti prazan!");
        katalogRepository.save(katalog);
        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8080/katalog/update", katalog);
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
