package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.DTO.UserAuthDTO;
import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.service.KatalogService;
import catalogue.microsservice.cataloguemicroservice.service.KorisnikService;
import catalogue.microsservice.cataloguemicroservice.util.JwtUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
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
    JwtUtil jwt;
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
    public String obrisiKatalog(@RequestBody Map<String, Long> katalogKojiSeBrise, @RequestHeader Map<String,String> headers){

        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        Long idKorisnik = katalogKojiSeBrise.get("idKorisnik");

        ResponseEntity<UserAuthDTO> res = restTemplate.getForEntity("http://user-service/user/single/id/" + idKorisnik, UserAuthDTO.class);

        System.out.println(res.getBody());
        System.out.println(idKorisnik);

        //provjera da li korisnik sa privilegijom smije dirati ovaj resurs
        if(res.getBody() == null || !username.equals(res.getBody().getUsername())) {
            System.out.println("Ilegala");
            throw new ApiRequestException("Radite nesto ilegalno!");
        }

        Long id_katalog = katalogKojiSeBrise.get("idKatalog");
        return katalogService.obrisiKatalog(id_katalog);
    }
}
