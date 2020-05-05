package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.grpc.EventSubmission;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KatalogService katalogService;
    @Autowired
    EventSubmission eventSubmission;

    public Long dodajKorisnika(Korisnik korisnik){
        //prvo dodamo korisnika
        korisnikRepository.save(korisnik);
        //dva defaultna kataloga
        Katalog zelim = new Katalog("Zelim procitati", korisnik.getId());
        Katalog procitano = new Katalog("Procitano", korisnik.getId());
        //dodamo ih u tabelu kataloga
        katalogService.kreirajKatalog(zelim);
        katalogService.kreirajKatalog(procitano);
        return korisnik.getId();
    }

    public Long dodajKatalogUListu(Katalog katalog)  {
        var korisnik = jedanKorisnik(katalog.getIdKorisnik());
        var katalozi = korisnik.getKatalozi();
        katalozi.add(katalog);
        korisnik.setKatalozi(katalozi);
        korisnikRepository.save(korisnik);
        return katalog.getId();
    }
    public Korisnik jedanKorisnik(Long id_korisnik){
        Optional<Korisnik> korisnik = korisnikRepository.findById(id_korisnik);
        if(korisnik.isEmpty()) throw new ApiRequestException("Korisnik sa id-jem " + id_korisnik + " ne postoji.");
        return korisnik.get();
    }
}
