package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/katalog")
public class KorisnikController {

    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KatalogRepository katalogRepository;

    @PostMapping(value="/novi-korisnik")
    public Long dodajKorisnika(@RequestBody Korisnik korisnik){
        //privremeno
        //kasnije dodati logiku da se zove korisnik servis za id korisnika
        korisnikRepository.save(korisnik);
        return korisnik.getId();
    }

    @PutMapping(value="/update")
    public Long dodajKatalogUListu(@RequestBody Katalog katalog)  {
        var korisnik = korisnikRepository.getOne(katalog.getIdKorisnik());
        var katalozi = korisnik.getKatalozi();
        katalozi.add(katalog);
        korisnik.setKatalozi(katalozi);
        korisnikRepository.save(korisnik);
        return katalog.getId();
    }

}
