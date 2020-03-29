package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
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
    KorisnikRepository korisnikRepository;
    @Autowired
    StripRepository stripRepository;

    public List<Katalog> sviKatalozi( Long id_korisnik, int brojStranice, int brojKatalogaNaStranici){
        return katalogRepository.findByIdKorisnik(id_korisnik, PageRequest.of(brojStranice, brojKatalogaNaStranici));
    }

    public Long kreirajKatalog(Katalog katalog){
        Long idKorisnik = katalog.getIdKorisnik();
        String naziv = katalog.getNaziv();
        //provjera postoji li user
        Optional<Korisnik> korisnik = korisnikRepository.findById(idKorisnik);
        if(korisnik.isEmpty()) throw new ApiRequestException("Korisnik sa id-jem " + idKorisnik + " ne postoji!");
        //provjera da li je naziv prazan
        if(naziv.equals("")) throw new ApiRequestException("Naziv kataloga ne smije biti prazan!");
        katalogRepository.save(katalog);
        return katalog.getId();
    }

    public void dodajStripUKatalog(Long id_strip, Long id_katalog){
        //provjera da li postoje i strip i katalog s ovim id-jem
        Optional<Katalog> katalog = katalogRepository.findById(id_katalog);
        Optional<Strip> strip = stripRepository.findById(id_strip);
        if(katalog.isEmpty()) throw new ApiRequestException("Katalog sa id-jem " + id_katalog + " ne postoji.");
        if(strip.isEmpty()) throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        Katalog katalogic = katalog.get();
        List<Strip> stripoviUKatalogu = katalogic.getStripovi();
        //ako je taj strip vec u katalogu
        if(stripoviUKatalogu.stream().map(Strip::getIdStrip).anyMatch(id_strip::equals)) throw new ApiRequestException("Strip je vec dodan u katalog!");
        stripoviUKatalogu.add(stripRepository.getOne(id_strip));
        katalogic.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalogic);
    }

    public Optional<Katalog> getKatalog(Long id_katalog){
        return katalogRepository.findById(id_katalog);
    }

    public void obrisiStrip(Long id_strip, Long id_katalog){
        //provjera da li postoji katalog
        Optional<Katalog> katalog = katalogRepository.findById(id_katalog);
        if(katalog.isEmpty()) throw new ApiRequestException("Katalog sa id-jem " + id_katalog + " ne postoji.");
        Katalog katalogic = katalog.get();
        List<Strip> stripoviUKatalogu = katalogic.getStripovi();
        stripoviUKatalogu.removeIf(strip->strip.getIdStrip().equals(id_strip));
        katalogic.setStripovi(stripoviUKatalogu);
        katalogRepository.save(katalogic);
    }

    public void obrisiKatalog(Long id_katalog){
        katalogRepository.delete(katalogRepository.getOne(id_katalog));
    }
}
