package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Math.ceil;
import static java.lang.Math.toIntExact;

@RestController
@RequestMapping(value="/strip")
public class StripController {
    @Autowired
    StripRepository stripRepository;

    //get sa paginacijom - svi stripovi
    @GetMapping(value="/all")
    public List<Strip> sviStripovi(@Param("brojStranice") int brojStranice){
        var brojNaJednojStranici = 5; //5 stripova se odjednom dobavlja
        var ukupnoStripova = stripRepository.count(); //ukupno stripova u bazi
        var ukupnoStranica = ceil((double)ukupnoStripova/(double)brojNaJednojStranici);

        var prvi_indeks = (brojStranice - 1)*brojNaJednojStranici;
        var zadnji_indeks = prvi_indeks + brojNaJednojStranici;

        if(brojStranice == ukupnoStranica) zadnji_indeks = toIntExact(ukupnoStripova);

        return stripRepository.findAll().subList(prvi_indeks, zadnji_indeks);
    }

    @PostMapping(value="/add")
    public Long dodajStrip(@RequestBody Strip strip){
        stripRepository.save(strip);
        return strip.getId();
    }


}
