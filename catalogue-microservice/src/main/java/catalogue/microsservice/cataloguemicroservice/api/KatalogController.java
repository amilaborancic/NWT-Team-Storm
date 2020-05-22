package catalogue.microsservice.cataloguemicroservice.api;
import catalogue.microsservice.cataloguemicroservice.DTO.UserAuthDTO;
import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.exception.ApiUnauthorizedException;
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
@CrossOrigin(origins = "http://localhost:3000")
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
    public List<Katalog> sviKatalozi(@Param("id_korisnik") Long id_korisnik){
        return katalogService.sviKatalozi(id_korisnik);
    }

    //kreiranje kataloga za nekog usera
    @PostMapping(value="/novi")
    public Long kreirajKatalog(@RequestBody Katalog katalog, @RequestHeader Map<String,String> headers){
        //provjera na vlasnika
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        Long id_user = katalog.getIdKorisnik();
        if(!isUserAdmin(username)){
            ResponseEntity<UserAuthDTO> res = restTemplate.getForEntity("http://user-service/user/single/id/" + id_user, UserAuthDTO.class);
            if(res.getBody() == null || !res.getBody().getUsername().equals(username)) throw new ApiUnauthorizedException("Nemate privilegiju da kreirate ovaj katalog!");
        }

        katalogService.kreirajKatalog(katalog);
        restTemplate.put("http://catalogue-service/korisnik/update", katalog);
        return katalog.getId();
    }

    //kreiranje kataloga za nekog usera
    @PostMapping(value="/new")
    public Long newCatalogueRegistration(@RequestBody Long idKorisnik){
        Korisnik korisnik = new Korisnik(idKorisnik);
        return korisnikService.dodajKorisnika(korisnik);
    }

    //dodavanje stripa u katalog uz provjeru da li je prethodno dodan
    @PutMapping(value="/dodavanje-stripa")
    public String dodajStripUKatalog(@RequestBody Map<String, Long> requestBody,  @RequestHeader Map<String,String> headers){
        Long id_strip = requestBody.get("id_strip");
        Long id_katalog = requestBody.get("id_katalog");

        //provjera na vlasnika
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);

        if(!isUserAdmin(username)){
            vlasnikKataloga(id_katalog, username, "Nemate privilegiju da dodate strip u ovaj katalog!");
        }
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
    public String obrisiStrip(@RequestBody Map<String, Long> body, @RequestHeader Map<String,String> headers){
        //provjera vlasnika kataloga
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        Long id_katalog = body.get("id_katalog");
        Long id_strip = body.get("id_strip");
        //provjera da li je admin
        if(!isUserAdmin(username)){
            //nadjemo vlasnika kataloga
            vlasnikKataloga(id_katalog, username, "Nemate privilegiju da obrisete strip iz ovog kataloga!");
        }
        if(katalogService.obrisiStrip(id_strip, id_katalog)) return "Strip je uspjesno obrisan iz kataloga!";
        return "Strip sa id-jem " + id_strip + " se ne nalazi u katalogu!";
    }

    //brisanje kataloga
    @DeleteMapping(value="/brisanje-kataloga")
    public String obrisiKatalog(@RequestBody Map<String, Long> katalogKojiSeBrise, @RequestHeader Map<String,String> headers){
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        Long id_katalog = katalogKojiSeBrise.get("idKatalog");

        //provjera da li je admin
        if(!isUserAdmin(username)){
            //nadjemo vlasnika kataloga
            vlasnikKataloga(id_katalog, username, "Nemate privilegiju da obrisete ovaj katalog!");
        }

        return katalogService.obrisiKatalog(id_katalog);
    }

    private void vlasnikKataloga(Long id_katalog, String username, String errorMsg) {
        Katalog kat = katalogService.getKatalog(id_katalog);
        Long id_vlasnik = kat.getIdKorisnik();
        ResponseEntity<UserAuthDTO> res = restTemplate.getForEntity("http://user-service/user/single/id/" + id_vlasnik, UserAuthDTO.class);
        if(res.getBody() == null || !res.getBody().getUsername().equals(username)) throw new ApiUnauthorizedException(errorMsg);
    }
    private boolean isUserAdmin(Long id){
        ResponseEntity<UserAuthDTO> res = restTemplate.getForEntity("http://user-service/user/single/id/" + id, UserAuthDTO.class);
        if(res.getBody() == null || !res.getBody().getRola().equals("ROLE_ADMIN")) return false;
        return true;
    }
    private boolean isUserAdmin(String username){
        ResponseEntity<UserAuthDTO> res = restTemplate.getForEntity("http://user-service/user/single/" + username, UserAuthDTO.class);
        if(res.getBody() == null || !res.getBody().getRola().equals("ROLE_ADMIN")) return false;
        return true;
    }
}
