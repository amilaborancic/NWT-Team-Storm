package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/autor")
@CrossOrigin(origins = "http://localhost:3000")
public class AutorController {

    @Autowired
    AutorService autorService;

    @GetMapping(value="/svi")
    public List<Autor> sviAutori(){
        return autorService.sviAutori();
    }

    @PostMapping(value="/novi")
    public Long dodajAutora(@RequestBody Autor autor){
        return autorService.dodajAutora(autor);
    }
}
