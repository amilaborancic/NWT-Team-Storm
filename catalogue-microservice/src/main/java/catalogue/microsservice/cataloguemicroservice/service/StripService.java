package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.StrictMath.round;

@Service
public class StripService {
    @Autowired
    StripRepository stripRepozitorij;
    @Autowired
    KatalogService katalogService;

    public List<Long> sviIzJednogKataloga(Long id_katalog, int brojStranice, int brojStripovaNaStranici){
        List<Long> stripovi = new ArrayList<>();
        //provjera postoji li katalog s proslijedjenim id-jem
        katalogService.getKatalog(id_katalog);
        stripRepozitorij.findByKatalozi_Id(id_katalog, PageRequest.of(brojStranice, brojStripovaNaStranici)).forEach(strip->stripovi.add(strip.getIdStrip()));
        return stripovi;
    }

    public Strip jedanStrip(Long id_strip){
        Optional<Strip> strip = stripRepozitorij.findById(id_strip);
        if(strip.isEmpty()) throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        return strip.get();
    }

    public boolean postojiUKatalogu(Long id_strip, Long id_katalog) {
        return stripRepozitorij.existsByIdStripAndKatalozi_Id(id_strip, id_katalog);
    }

    public Long ukupnoStripova(Long idKatalog){
        return stripRepozitorij.countStripByKatalozi_Id(idKatalog);
    }

    public int brojStranica(int brojStripova, int brojNaStranici){
        return (int) round((double)brojStripova/brojNaStranici + 0.5);
    }

}
