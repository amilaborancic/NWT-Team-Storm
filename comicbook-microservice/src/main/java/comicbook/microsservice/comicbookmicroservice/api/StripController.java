package comicbook.microsservice.comicbookmicroservice.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import comicbook.microsservice.comicbookmicroservice.DTO.OcjenaKomentarDTO;
import comicbook.microsservice.comicbookmicroservice.DTO.RatingDTO;
import comicbook.microsservice.comicbookmicroservice.DTO.StripIdList;
import comicbook.microsservice.comicbookmicroservice.RabbitMQ.RabbitMQProducer;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.service.StripService;
import comicbook.microsservice.comicbookmicroservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value="/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;
    private String jsonTemplate = "jsonTemplate";
    @Autowired
    StripService stripService;
    @Autowired
    private RabbitMQProducer producer;
    @Autowired
    JwtUtil jwt;
    //get sa paginacijom - svi stripovi
    @GetMapping(value="/svi")
    public String sviStripovi(@Param("brojStranice") int brojStranice, Model model){
        model.addAttribute("stripovi", stripService.sviStripovi(brojStranice, brojStripovaNaStranici));
        int brojStripova = stripService.brojStripovaUBazi().intValue();
        model.addAttribute("brojStranica", stripService.brojStranica(brojStripova, brojStripovaNaStranici));
        return jsonTemplate;
    }

    //jedan konkretan strip, parametar je id stripa
    @GetMapping()
    public @ResponseBody Strip jedanStrip(@Param("id_strip") Long id_strip){
        return stripService.jedanStrip(id_strip);
    }

    //svi stripovi jednog autora sa paginacijom - SEARCH BY AUTHOR funkcionalnost
    @GetMapping(value="/trazi-autor")
    public String stripoviPoAutoru(@Param("ime") String ime, @Param("prezime") String prezime, @Param("brojStranice") int brojStranice, Model model){

        model.addAttribute("stripovi", stripService.stripoviPoAutoru(ime, prezime, brojStranice, brojStripovaNaStranici));
        int brojStripova = stripService.brojStripovaPoAutorIme(ime, prezime).intValue();
        model.addAttribute("brojStranica", stripService.brojStranica(brojStripova, brojStripovaNaStranici));

        return jsonTemplate;
    }

    //svi stripovi jednog izdavaca sa paginacijom - SEARCH BY PUBLISHER funkcionalnost
    @GetMapping(value="/trazi-izdavac")
    public String stripoviPoIzdavacu(@Param("id_izdavac") Long id_izdavac, @Param("brojStranice") int brojStranice, Model model){
        model.addAttribute("stripovi", stripService.stripoviPoIzdavacu(id_izdavac, brojStranice, brojStripovaNaStranici));
        int brojStripova = stripService.brojStripovaPoIzdavacu(id_izdavac).intValue();
        model.addAttribute("brojStranica", stripService.brojStranica(brojStripova, brojStripovaNaStranici));
        return jsonTemplate;
    }

    //svi stripovi jednog zanra sa paginacijom - SEARCH BY GENRE funkcionalnost
    @GetMapping(value="/trazi-zanr")
    public String stripoviPoZanru(@Param("id_zanr") Long id_zanr, @Param("brojStranice") int brojStranice, Model model){
        model.addAttribute("stripovi", stripService.stripoviPoZanru(id_zanr, brojStranice, brojStripovaNaStranici));
        int brojStripova = stripService.brojStripovaPoZanru(id_zanr).intValue();
        model.addAttribute("brojStranica", stripService.brojStranica(brojStripova, brojStripovaNaStranici));
        return jsonTemplate;
    }

    //svi stripovi sa odredjenim nazivom - SEARCH BY TITLE funkcionalnost
    @GetMapping(value="/trazi-naziv")
    public String stripoviPoNazivu(@Param("naziv") String naziv, @Param("brojStranice") int brojStranice, Model model){
        model.addAttribute("stripovi", stripService.stripoviPoNazivu(naziv, brojStranice, brojStripovaNaStranici));
        int brojStripova = stripService.brojStripovaPoNazivu(naziv).intValue();
        model.addAttribute("brojStranica", stripService.brojStranica(brojStripova, brojStripovaNaStranici));
        return jsonTemplate;
    }

    //svi stripovi ciji je id poslan kao request body
    @PostMapping(value="/sviPoId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Strip> stripoviPoId(@RequestBody StripIdList idStripova){
        return stripService.sviStripoviPoId(idStripova.getIdStripova());
    }


    @PutMapping(value="/ostavi-rating/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void ostaviRating(@PathVariable Long id, @RequestBody OcjenaKomentarDTO ocjenaKomentarDTO,@RequestHeader Map<String,String> headers) throws JsonProcessingException {
        String token = headers.get("authorization").substring(7);
        String username = jwt.extractUsername(token);
        Strip strip=stripService.jedanStrip(id);
        RatingDTO ratingDTO=new RatingDTO(username,ocjenaKomentarDTO.getOcjena(),ocjenaKomentarDTO.getKomentar(),strip.getUkupniRating(),strip.getUkupnoKomentara());
        producer.send(ratingDTO);
    }

    //pomocni endpointi
    @PostMapping(value="/noviStrip")
    public @ResponseBody Long dodajStrip(@RequestBody Strip strip){
        return stripService.dodajStrip(strip);
    }

   @GetMapping(value = "/autori/{id}")
    public @ResponseBody List<Autor> autoriStripa(@PathVariable Long id){
        return stripService.autoriStripa(id);
    }

    @GetMapping(value="/count")
    public @ResponseBody Long brojStripovaUBazi(){return stripService.brojStripovaUBazi();}

    @GetMapping(value="/brojNaStranici")
    public @ResponseBody int brojNaStranici(){return this.brojStripovaNaStranici;}

}
