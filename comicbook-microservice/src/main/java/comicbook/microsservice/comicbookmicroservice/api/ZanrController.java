package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import comicbook.microsservice.comicbookmicroservice.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/zanr")
public class ZanrController {

    @Autowired
    ZanrService zanrService;

    @GetMapping(value="/svi")
    public List<Zanr> sviZanrovi(){
        return zanrService.sviZanrovi();
    }

    @PostMapping(value="/novi")
    public Long dodajZanr(@RequestBody Zanr zanr){
        return zanrService.dodajZanr(zanr);
    }
}
