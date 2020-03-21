package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/autor")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping(value="/all")
    public List<Autor> sviAutori(){
        return autorRepository.findAll();
    }

    @PostMapping(value="/add")
    public Long dodajAutora(@RequestBody Autor autor){
        autorRepository.save(autor);
        return autor.getId();
    }
}
