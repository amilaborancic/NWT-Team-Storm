package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KatalogService katalogService;

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