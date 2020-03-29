package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.service.IzdavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/izdavac")
public class IzdavacController {
    @Autowired
    IzdavacService izdavacService;

    @GetMapping(value="/svi")
    public List<Izdavac> sviIzdavaci(){
        return izdavacService.sviIzdavaci();
    }

    @PostMapping(value="/novi")
    public Long dodajIzdavaca(@RequestBody Izdavac izdavac){
        return izdavacService.dodajIzdavaca(izdavac);
    }
}
