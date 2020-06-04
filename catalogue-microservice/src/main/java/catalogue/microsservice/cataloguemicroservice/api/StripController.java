package catalogue.microsservice.cataloguemicroservice.api;

import catalogue.microsservice.cataloguemicroservice.DTO.StripDTO;
import catalogue.microsservice.cataloguemicroservice.DTO.StripIdsDTO;
import catalogue.microsservice.cataloguemicroservice.service.KatalogService;
import catalogue.microsservice.cataloguemicroservice.service.StripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/katalog")
public class StripController {

    private int brojStripovaNaStranici = 6;

    @Autowired
    StripService stripService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KatalogService katalogService;

    //stripovi u jednom katalogu sa paginacijom
    @GetMapping(value="/iz-kataloga/{id_katalog}")
    public String sviIzJednogKataloga(@PathVariable("id_katalog") Long id_katalog, @Param("brojStranice") int brojStranice, Model model){
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
        ResponseEntity<Map> listaStripova = restTemplate.exchange("http://comicbook-service/strip/sviPoId", HttpMethod.POST, requestBody, Map.class);
        model.addAttribute("stripovi", listaStripova.getBody().get("stripovi"));

        int ukupnoStripova = stripService.ukupnoStripova(id_katalog).intValue();
        model.addAttribute("ukupnoStranica", stripService.brojStranica(ukupnoStripova, brojStripovaNaStranici));
        model.addAttribute("nazivResursa", "Stripovi iz kataloga id="+id_katalog+".");
        return "jsonTemplate";
    }


}
