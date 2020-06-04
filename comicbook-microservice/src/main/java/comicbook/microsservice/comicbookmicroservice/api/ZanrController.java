package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import comicbook.microsservice.comicbookmicroservice.service.ZanrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/zanr")
public class ZanrController {

    @Autowired
    ZanrService zanrService;
    private String jsonTemplate = "jsonTemplate";

    @GetMapping(value="/svi")
    public String sviZanrovi(Model model){
        model.addAttribute("zanrovi", zanrService.sviZanrovi());
        model.addAttribute("nazivResursa", "Svi žanrovi.");
        return jsonTemplate;
    }

    @PostMapping(value="/novi")
    public String dodajZanr(@RequestBody Zanr zanr, Model model){
        model.addAttribute("id", zanrService.dodajZanr(zanr));
        model.addAttribute("nazivResursa", "Novi žanr.");
        return jsonTemplate;
    }
}
