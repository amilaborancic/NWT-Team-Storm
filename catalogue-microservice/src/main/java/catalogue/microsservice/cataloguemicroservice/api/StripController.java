package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.service.StripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/katalog")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripService stripService;

    //stripovi u jednom katalogu sa paginacijom
    @GetMapping(value="/iz-kataloga/{id_katalog}")
    public List<Long> sviIzJednogKataloga(@PathVariable("id_katalog") Long id_katalog, @Param("brojStranice") int brojStranice){
        return stripService.sviIzJednogKataloga(id_katalog, brojStranice, brojStripovaNaStranici);
    }

    //svi stripovi - samo za testiranje
    @GetMapping(value="/svistripovi")
    public List<Strip> sviStripovi(){return stripService.stripovi();}

}
