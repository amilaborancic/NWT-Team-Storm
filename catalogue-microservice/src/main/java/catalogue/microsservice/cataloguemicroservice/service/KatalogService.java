package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.grpc.EventSubmission;
import catalogue.microsservice.cataloguemicroservice.grpc.Events;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import jdk.jfr.Event;
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
    @Autowired
    EventSubmission eventSubmission;

    private Long idAdmin = 1000L;
    private Long idLogovanogKorisnika = 500L;

    public List<Katalog> sviKatalozi(Long id_korisnik, int brojStranice, int brojKatalogaNaStranici){
        //provjera na korisnika
        korisnikService.jedanKorisnik(id_korisnik);
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(id_korisnik, Events.ActionType.GET, "Svi katalozi jednog usera, stranica " + brStr);
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
        eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Novi katalog");
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
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.UPDATE, "Dodan strip sa id = " + id_strip + " u katalog sa id = " + id_katalog);
        return id_katalog;
    }

    public Katalog getKatalog(Long id_katalog){
        Optional<Katalog> katalog = katalogRepository.findById(id_katalog);
        if(katalog.isEmpty()) throw new ApiRequestException("Katalog sa id-jem " + id_katalog + " ne postoji.");
        eventSubmission.submitEvent(idAdmin, Events.ActionType.GET, "Katalog sa id = " + id_katalog);
        return katalog.get();
    }

    public boolean obrisiStrip(Long id_strip, Long id_katalog){
        //provjera da li postoji katalog
        Katalog katalogic = getKatalog(id_katalog);
        List<Strip> stripoviUKatalogu = katalogic.getStripovi();
        int brojStripovaUKatalogu = stripoviUKatalogu.size();
        stripoviUKatalogu.removeIf(strip->strip.getIdStrip().equals(id_strip));
        if(stripoviUKatalogu.size() == brojStripovaUKatalogu) return false;
        katalogic.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalogic);
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.UPDATE, "Obrisan strip sa id = " + id_strip + " iz kataloga sa id = " + id_katalog);
        return true;
    }

    public String obrisiKatalog(Long id_katalog){
        //provjera postoji li katalog
        Katalog kat = getKatalog(id_katalog);
        katalogRepository.delete(katalogRepository.getOne(id_katalog));
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.DELETE, "Obrisan katalog sa id = " + id_katalog);
        return("Katalog je uspjesno obrisan!");
    }


}
