package catalogue.microsservice.cataloguemicroservice.service;

import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StripService {
    @Autowired
    StripRepository stripRepozitorij;

    public List<Long> sviIzJednogKataloga(Long id_katalog, int brojStranice, int brojStripovaNaStranici){
        List<Long> stripovi = new ArrayList<>();
        stripRepozitorij.findByKatalozi_Id(id_katalog, PageRequest.of(brojStranice, brojStripovaNaStranici)).forEach(strip->stripovi.add(strip.getIdStrip()));
        return stripovi;
    }
}
