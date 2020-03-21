package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/izdavac")
public class IzdavacController {
    @Autowired
    IzdavacRepository izdavacRepository;

    @GetMapping(value="/all")
    public List<Izdavac> sviIzdavaci(){
        return izdavacRepository.findAll();
    }

    @PostMapping(value="/add")
    public Integer dodajIzdavaca(@RequestBody Izdavac izdavac){
        izdavacRepository.save(izdavac);
        return izdavac.getId();
    }
}
