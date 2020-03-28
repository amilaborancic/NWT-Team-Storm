package comicbook.microsservice.comicbookmicroservice.api;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import comicbook.microsservice.comicbookmicroservice.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value="/strip")
public class StripController {

    private int brojStripovaNaStranici = 5;

    @Autowired
    StripRepository stripRepository;
    @Autowired
    ZanrRepository zanrRepository;
    @Autowired
    IzdavacRepository izdavacRepository;
    @Autowired
    AutorRepository autorRepository;

    //get sa paginacijom - svi stripovi
    @GetMapping(value="/svi")
    public List<Strip> sviStripovi(@Param("brojStranice") int brojStranice){
        return stripRepository.findAll(PageRequest.of(brojStranice, brojStripovaNaStranici)).getContent();
    }

    //jedan konkretan strip, parametar je id stripa
    @GetMapping()
    public Strip jedanStrip(@Param("id_strip") Long id_strip){
        Optional<Strip> strip = stripRepository.findById(id_strip);
        if(strip.isEmpty()) throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        return strip.get();
    }

    //svi stripovi jednog autora sa paginacijom - SEARCH BY AUTHOR funkcionalnost
    @GetMapping(value="/trazi-autor")
    public List<Strip> stripoviPoAutoru(@Param("ime") String ime, @Param("prezime") String prezime, @Param("brojStranice") int brojStranice){
        if(ime == null || ime == "") ime = "-";
        if(prezime == null || prezime == "") prezime = "-";
        Set<Strip> stripovi = new HashSet<Strip>(stripRepository.findAllByAutori_ImeContainsAndAutori_PrezimeContains(ime, prezime, PageRequest.of(brojStranice, brojStripovaNaStranici)));
        return new ArrayList<Strip>(stripovi);
    }

    //svi stripovi jednog izdavaca sa paginacijom - SEARCH BY PUBLISHER funkcionalnost
    @GetMapping(value="/trazi-izdavac")
    public List<Strip> stripoviPoIzdavacu(@Param("id_izdavac") Long id_izdavac, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdIzdavac(id_izdavac, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi jednog zanra sa paginacijom - SEARCH BY GENRE funkcionalnost
    @GetMapping(value="/trazi-zanr")
    public List<Strip> stripoviPoZanru(@Param("id_zanr") Long id_zanr, @Param("brojStranice") int brojStranice){
        return stripRepository.findByIdZanr(id_zanr, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    //svi stripovi sa odredjenim nazivom - SEARCH BY TITLE funkcionalnost
    @GetMapping(value="/trazi-naziv")
    public List<Strip> stripoviPoNazivu(@Param("naziv") String naziv, @Param("brojStranice") int brojStranice){
        return stripRepository.findByNazivContains(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    @PostMapping(value="/noviStrip")
    public Long dodajStrip(@RequestBody Strip strip){
        Long idIzdavac = strip.getIdIzdavac();
        Long idZanr = strip.getIdZanr();
        Double rating = strip.getUkupniRating();
        Integer brojKom = strip.getUkupnoKomentara();
        //validacija parametara za inicijalizaciju stripa
        if(strip.getNaziv().equals("") || strip.getNaziv() == null) throw new ApiRequestException("Strip mora imati naziv!");
        if(idIzdavac == null) throw new ApiRequestException("Strip mora imati izdavaca!");
        if(idZanr == null) throw new ApiRequestException("Strip mora imati zanr!");
        if(rating == null || rating < 0) throw new ApiRequestException("Strip mora imati nenegativan rating!");
        if(strip.getSlika() == null) throw new ApiRequestException("Strip mora imati sliku!");
        if(brojKom == null || brojKom < 0) throw new ApiRequestException("Strip mora imati nenegativan broj komentara!");
        if(strip.getAutori() == null || strip.getAutori().size() == 0) throw new ApiRequestException("Strip mora imati autore!");
        //provjera postoje li proslijedjeni zanr i izdavac
        if(zanrRepository.findById(idZanr).isEmpty()) throw new ApiRequestException("Zanr sa id-jem " + idZanr + " ne postoji.");
        if(izdavacRepository.findById(idIzdavac).isEmpty()) throw new ApiRequestException("Izdavac sa id-jem " + idZanr + " ne postoji.");
        stripRepository.save(strip);
        return strip.getId();
    }


}
