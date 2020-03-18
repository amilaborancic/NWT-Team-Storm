package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public UUID dodajIzdavaca(@RequestBody Izdavac izdavac){
        izdavacRepository.save(izdavac);
        return izdavac.getId();
    }
}
