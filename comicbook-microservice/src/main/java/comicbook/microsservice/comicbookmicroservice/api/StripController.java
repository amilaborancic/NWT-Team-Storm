package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static java.lang.Math.ceil;
import static java.lang.Math.toIntExact;

@RestController
@RequestMapping(value="/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripRepository stripRepository;

    //get sa paginacijom - svi stripovi
    @GetMapping(value="/all")
    public List<Strip> sviStripovi(@Param("brojStranice") int brojStranice){
        return stripRepository.findAll(PageRequest.of(brojStranice, brojStripovaNaStranici)).getContent();
    }

    //jedan konkretan strip, parametar je ID
    @GetMapping()
    public Optional<Strip> jedanStrip(@Param("id") Long id){
        if(id != null) return stripRepository.findById(id);
        return null;
    }

    //svi stripovi jednog autora - SEARCH BY AUTHOR funkcionalnost
    @GetMapping(value="/search-author")
    public List<Strip> stripoviPoAutoru(@RequestBody Autor autor){
        Set<Strip> stripovi = new HashSet<>(); //koristimo set kako bi se uklonili duplikati
        if(autor.getIme().length() > 2) stripovi.addAll(stripRepository.findByAutori_ImeContaining(autor.getIme()));
        if(autor.getPrezime().length() > 3) stripovi.addAll(stripRepository.findByAutori_PrezimeContaining(autor.getPrezime()));
        return new ArrayList<>(stripovi);
    }

    //svi stripovi jednog izdavaca sa paginacijom - SEARCH BY PUBLISHER funkcionalnost
    @GetMapping(value="/search-publisher")
    public List<Strip> stripoviPoIzdavacu(@Param("id") Long id, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdIzdavac(id, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi jednog zanra sa paginacijom - SEARCH BY GENRE funkcionalnost
    @GetMapping(value="/search-genre")
    public List<Strip> stripoviPoZanru(@Param("id") Long id, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdZanr(id, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi sa odredjenim nazivom - SEARCH BY TITLE funkcionalnost
    @GetMapping(value="/search-title")
    public List<Strip> stripoviPoNazivu(@Param("naziv") String naziv, @Param("brojStranice") int brojStranice){
        return stripRepository.findByNazivContaining(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //pomocna funkcija za racunanje paginacije
    private Pair<Integer, Integer> Paginacija(int brojStranice, int ukupnoStripova){
        var ukupnoStranica = ceil((double)ukupnoStripova/(double)brojStripovaNaStranici);
        var prvi_indeks = (brojStranice - 1)*brojStripovaNaStranici;
        var zadnji_indeks = prvi_indeks + brojStripovaNaStranici;
        if(brojStranice == ukupnoStranica || ukupnoStripova < brojStripovaNaStranici) zadnji_indeks = toIntExact(ukupnoStripova);
        return Pair.of(prvi_indeks, zadnji_indeks);
    }
    @PostMapping(value="/add")
    public Long dodajStrip(@RequestBody Strip strip){
        stripRepository.save(strip);
        return strip.getId();
    }


}
