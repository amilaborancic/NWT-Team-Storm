package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripRepository stripRepository;

    //get sa paginacijom - svi stripovi
    @GetMapping(value="/svi")
    public List<Strip> sviStripovi(@Param("brojStranice") int brojStranice){
        return stripRepository.findAll(PageRequest.of(brojStranice, brojStripovaNaStranici)).getContent();
    }

    //jedan konkretan strip, parametar je id stripa
    @GetMapping()
    public Optional<Strip> jedanStrip(@Param("id_strip") Long id_strip){
        if(id_strip != null) return stripRepository.findById(id_strip);
        return null;
    }

    //svi stripovi jednog autora sa paginacijom - SEARCH BY AUTHOR funkcionalnost
    @GetMapping(value="/trazi-autor")
    public List<Strip> stripoviPoAutoru(@Param("ime") String ime, @Param("prezime") String prezime, @Param("brojStranice") int brojStranice){
        if(ime == null) ime = "-";
        if(prezime == null) prezime = "-";
        Set<Strip> stripovi = new HashSet<Strip>(stripRepository.findAllByAutori_ImeContainsOrAutori_PrezimeContains(ime, prezime, PageRequest.of(brojStranice, brojStripovaNaStranici)));
        return new ArrayList<Strip>(stripovi);
    }

    //svi stripovi jednog izdavaca sa paginacijom - SEARCH BY PUBLISHER funkcionalnost
    @GetMapping(value="/trazi-izdavac")
    public List<Strip> stripoviPoIzdavacu(@Param("id_izdavac") Long id_izdavac, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdIzdavac(id_izdavac, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi jednog zanra sa paginacijom - SEARCH BY GENRE funkcionalnost
    @GetMapping(value="/trazi-zanr")
    public List<Strip> stripoviPoZanru(@Param("id_zanr") Long id_zanr, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdZanr(id_zanr, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi sa odredjenim nazivom - SEARCH BY TITLE funkcionalnost
    @GetMapping(value="/trazi-naziv")
    public List<Strip> stripoviPoNazivu(@Param("naziv") String naziv, @Param("brojStranice") int brojStranice){
        return stripRepository.findByNazivContains(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    @PostMapping(value="/noviStrip")
    public Long dodajStrip(@RequestBody Strip strip){
        stripRepository.save(strip);
        return strip.getId();
    }


}
