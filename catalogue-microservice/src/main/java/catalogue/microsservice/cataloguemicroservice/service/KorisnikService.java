package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KatalogRepository katalogRepository;

    public Long dodajKorisnika(Korisnik korisnik){
        //privremeno
        //kasnije dodati logiku da se zove user mikroservis za id korisnika
        korisnikRepository.save(korisnik);
        return korisnik.getId();
    }

    public Long dodajKatalogUListu(Katalog katalog)  {
        var korisnik = korisnikRepository.getOne(katalog.getIdKorisnik());
        var katalozi = korisnik.getKatalozi();
        katalozi.add(katalog);
        korisnik.setKatalozi(katalozi);
        korisnikRepository.save(korisnik);
        return katalog.getId();
    }
}
