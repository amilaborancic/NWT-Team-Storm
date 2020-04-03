package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KatalogService {
    @Autowired
    KatalogRepository katalogRepository;
    @Autowired
    KorisnikService korisnikService;
    @Autowired
    StripService stripService;

    public List<Katalog> sviKatalozi(Long id_korisnik, int brojStranice, int brojKatalogaNaStranici){
        //provjera na korisnika
        korisnikService.jedanKorisnik(id_korisnik);
        return katalogRepository.findByIdKorisnik(id_korisnik, PageRequest.of(brojStranice, brojKatalogaNaStranici));
    }

    public Long kreirajKatalog(Katalog katalog){
        Long idKorisnik = katalog.getIdKorisnik();
        String naziv = katalog.getNaziv();
        //provjera postoji li user
        korisnikService.jedanKorisnik(idKorisnik);
        //provjera da li je naziv prazan
        if(naziv.equals("")) throw new ApiRequestException("Naziv kataloga ne smije biti prazan!");
        katalogRepository.save(katalog);
        return katalog.getId();
    }

    public Long dodajStripUKatalog(Long id_strip, Long id_katalog){
        //provjera da li postoje i strip i katalog s ovim id-jem
        Katalog katalog = getKatalog(id_katalog);
        Strip strip = stripService.jedanStrip(id_strip);
        //ako je taj strip vec u katalogu
        if(stripService.postojiUKatalogu(id_strip, id_katalog)) throw new ApiRequestException("Strip je vec dodan u katalog!");
        List<Strip> stripoviUKatalogu = katalog.getStripovi();
        stripoviUKatalogu.add(strip);
        katalog.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalog);
        return id_katalog;
    }

    public Katalog getKatalog(Long id_katalog){
        Optional<Katalog> katalog = katalogRepository.findById(id_katalog);
        if(katalog.isEmpty()) throw new ApiRequestException("Katalog sa id-jem " + id_katalog + " ne postoji.");
        return katalog.get();
    }

    public boolean obrisiStrip(Long id_strip, Long id_katalog){
        //provjera da li postoji katalog
        Katalog katalogic = getKatalog(id_katalog);
        List<Strip> stripoviUKatalogu = katalogic.getStripovi();
        stripoviUKatalogu.removeIf(strip->strip.getIdStrip().equals(id_strip));
        katalogic.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalogic);
        return true;
    }

    public String obrisiKatalog(Long id_katalog){
        katalogRepository.delete(katalogRepository.getOne(id_katalog));
        return("Katalog je uspjesno obrisan!");
    }


}
