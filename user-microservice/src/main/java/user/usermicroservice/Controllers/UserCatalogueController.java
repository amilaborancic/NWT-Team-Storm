package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.Servisi.UserServis;

@RestController
@RequestMapping("/katalog")
public class UserCatalogueController {
    @Autowired
    UserServis userServis;

    @PutMapping("/create-katalog")
    public Long kreirajKatalogKorisniku(@RequestBody KatalogDTO noviKatalog){
        //okinuti endpoint za pravljenje kataloga
        RestTemplate obj = new RestTemplate();
        obj.put("http://localhost:8082/katalog/update", noviKatalog);
        return (long) 1;
    }

}
