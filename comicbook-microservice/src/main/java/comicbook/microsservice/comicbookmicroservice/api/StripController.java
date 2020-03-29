package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.service.StripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripService stripService;

    //get sa paginacijom - svi stripovi
    @GetMapping(value="/svi")
    public List<Strip> sviStripovi(@Param("brojStranice") int brojStranice){
        return stripService.sviStripovi(brojStranice, brojStripovaNaStranici);
    }

    //jedan konkretan strip, parametar je id stripa
    @GetMapping()
    public Strip jedanStrip(@Param("id_strip") Long id_strip){
        return stripService.jedanStrip(id_strip);
    }

    //svi stripovi jednog autora sa paginacijom - SEARCH BY AUTHOR funkcionalnost
    @GetMapping(value="/trazi-autor")
    public List<Strip> stripoviPoAutoru(@Param("ime") String ime, @Param("prezime") String prezime, @Param("brojStranice") int brojStranice){
        return stripService.stripoviPoAutoru(ime, prezime, brojStranice, brojStripovaNaStranici);
    }

    //svi stripovi jednog izdavaca sa paginacijom - SEARCH BY PUBLISHER funkcionalnost
    @GetMapping(value="/trazi-izdavac")
    public List<Strip> stripoviPoIzdavacu(@Param("id_izdavac") Long id_izdavac, @Param("brojStranice") int brojStranice){
        return stripService.stripoviPoIzdavacu(id_izdavac, brojStranice, brojStripovaNaStranici);
    }

    //svi stripovi jednog zanra sa paginacijom - SEARCH BY GENRE funkcionalnost
    @GetMapping(value="/trazi-zanr")
    public List<Strip> stripoviPoZanru(@Param("id_zanr") Long id_zanr, @Param("brojStranice") int brojStranice){
       return stripService.stripoviPoZanru(id_zanr, brojStranice, brojStripovaNaStranici);
    }

    //svi stripovi sa odredjenim nazivom - SEARCH BY TITLE funkcionalnost
    @GetMapping(value="/trazi-naziv")
    public List<Strip> stripoviPoNazivu(@Param("naziv") String naziv, @Param("brojStranice") int brojStranice){
        return stripService.stripoviPoNazivu(naziv, brojStranice, brojStripovaNaStranici);
    }

    @PostMapping(value="/noviStrip")
    public Long dodajStrip(@RequestBody Strip strip){
        return stripService.dodajStrip(strip);
    }


}
