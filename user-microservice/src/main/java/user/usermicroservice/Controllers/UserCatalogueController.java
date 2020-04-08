package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.DTO.KatalogDeleteDTO;
import user.usermicroservice.Servisi.UserServis;

import java.util.Collections;


@RestController
@RequestMapping("/katalog")
public class UserCatalogueController {
    @Autowired
    UserServis userServis;

    @Autowired
    RestTemplate restTemplate;

    @PutMapping(value="/create-katalog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long kreirajKatalogKorisniku(@RequestBody KatalogDTO noviKatalog){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //request body
        HttpEntity<KatalogDTO> entity = new HttpEntity<>(noviKatalog, headers);
        ResponseEntity<Long> response = restTemplate.postForEntity("http://catalogue-service/katalog/novi", entity, Long.class);
        return response.getBody();
    }

    @DeleteMapping(value="/delete-katalog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String obrisiKorisnikovKatalog(@RequestBody KatalogDeleteDTO katalogKojiSeBrise){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<KatalogDeleteDTO> entity = new HttpEntity<>(katalogKojiSeBrise, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://catalogue-service/katalog/brisanje-kataloga", HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }

}
