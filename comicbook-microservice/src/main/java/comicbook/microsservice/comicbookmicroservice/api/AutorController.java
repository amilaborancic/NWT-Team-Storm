package comicbook.microsservice.comicbookmicroservice.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(value="/autor")
public class AutorController {

    @Autowired
    AutorService autorService;

    private String jsonTemplate = "jsonTemplate";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value="/svi")
    public String sviAutori(Model model){
        ObjectNode obj = autorService.sviAutori();
        model.addAttribute("autori", obj.get("autori"));
        model.addAttribute("nazivResursa", obj.get("nazivResursa"));
        return jsonTemplate;
    }

    @PostMapping(value="/novi")
    public String dodajAutora(@RequestBody Autor autor, Model model){
        ObjectNode obj = autorService.dodajAutora(autor);
        model.addAttribute("id", obj.get("id"));
        model.addAttribute("nazivResursa", obj.get("nazivResursa"));
        return jsonTemplate;
    }
}
