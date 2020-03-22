package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import comicbook.microsservice.comicbookmicroservice.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/zanr")
public class ZanrController {

    @Autowired
    ZanrRepository zanrRepository;

    @GetMapping(value="/svi")
    public List<Zanr> sviZanrovi(){
        return zanrRepository.findAll();
    }

    @PostMapping(value="/novi")
    public Long dodajZanr(@RequestBody Zanr zanr){
        zanrRepository.save(zanr);
        return zanr.getId();
    }
}
