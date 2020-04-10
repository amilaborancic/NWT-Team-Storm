package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.DTO.StripDTO;
import catalogue.microsservice.cataloguemicroservice.DTO.StripIdsDTO;
import catalogue.microsservice.cataloguemicroservice.service.KatalogService;
import catalogue.microsservice.cataloguemicroservice.service.StripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/katalog")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripService stripService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KatalogService katalogService;

    //stripovi u jednom katalogu sa paginacijom
    @GetMapping(value="/iz-kataloga/{id_katalog}")
    public List<StripDTO> sviIzJednogKataloga(@PathVariable("id_katalog") Long id_katalog, @Param("brojStranice") int brojStranice){
        //provjera postoji li katalog
        katalogService.getKatalog(id_katalog);
        List<Long> idjevi = stripService.sviIzJednogKataloga(id_katalog, brojStranice, brojStripovaNaStranici);
        //postavimo headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        StripIdsDTO stripovi = new StripIdsDTO(idjevi);
        HttpEntity<StripIdsDTO> requestBody = new HttpEntity<>(stripovi, headers);
        //zovemo endpoint iz strip servisa
        ResponseEntity<List> listaStripova = restTemplate.exchange("http://comicbook-service/strip/sviPoId", HttpMethod.POST, requestBody, List.class);
        return listaStripova.getBody();
    }


}
