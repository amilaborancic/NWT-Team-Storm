package user.usermicroservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import user.usermicroservice.DTO.KatalogDTO;
import user.usermicroservice.Servisi.UserServis;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping("/katalog")
public class UserCatalogueController {
    @Autowired
    UserServis userServis;

    @PutMapping(value="/create-katalog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long kreirajKatalogKorisniku(@RequestBody KatalogDTO noviKatalog){
        RestTemplate obj = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //request body
        HttpEntity<KatalogDTO> entity = new HttpEntity<>(noviKatalog, headers);
        ResponseEntity<Long> response = obj.postForEntity("http://localhost:8082/katalog/novi", entity, Long.class);
        return response.getBody();
    }

    @DeleteMapping(value="/delete-katalog", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String obrisiKorisnikovKatalog(@RequestBody Map<String, Long> katalogKojiSeBrise){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
        //izvucemo id kataloga koji se brise iz body
        Long id_kataloga = katalogKojiSeBrise.get("id_katalog");
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8082/katalog/brisanje-kataloga?id_katalog="+id_kataloga, HttpMethod.DELETE, entity, String.class);
        return response.getBody();
    }

}
