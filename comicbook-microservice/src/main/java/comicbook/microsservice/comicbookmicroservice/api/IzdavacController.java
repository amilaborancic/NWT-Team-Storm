package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.service.IzdavacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/izdavac")
public class IzdavacController {
    @Autowired
    IzdavacService izdavacService;
    private String jsonTemplate = "jsonTemplate";

    @GetMapping(value="/svi")
    public String sviIzdavaci(Model model){
        model.addAttribute("izdavaci", izdavacService.sviIzdavaci());
        model.addAttribute("nazivResursa", "Svi izdavači.");
        return jsonTemplate;
    }

    @PostMapping(value="/novi")
    public String dodajIzdavaca(@RequestBody Izdavac izdavac, Model model){
        model.addAttribute("id", izdavacService.dodajIzdavaca(izdavac));
        model.addAttribute("nazivResursa", "Novi izdavač.");
        return jsonTemplate;
    }
}
