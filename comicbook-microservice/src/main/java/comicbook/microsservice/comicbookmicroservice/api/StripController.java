package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/strip")
public class StripController {
    @Autowired
    StripRepository stripRepository;

    @Autowired
    AutorRepository autorRepository; //autor-strip is many to many

    @GetMapping(value="/all")
    public List<Strip> sviStripovi(){
        //temporary, will add pagination later
        return stripRepository.findAll();
    }

    @PostMapping(value="/add")
    public Integer dodajStrip(@RequestBody Strip strip){
        stripRepository.save(strip);
        return strip.getId();
    }
}
