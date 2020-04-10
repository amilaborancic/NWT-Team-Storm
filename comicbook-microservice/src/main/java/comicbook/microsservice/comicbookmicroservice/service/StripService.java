package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import comicbook.microsservice.comicbookmicroservice.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StripService {
    @Autowired
    StripRepository stripRepository;
    @Autowired
    ZanrRepository zanrRepository;
    @Autowired
    IzdavacRepository izdavacRepository;
    @Autowired
    AutorRepository autorRepository;
    @Autowired
	RestTemplate restTemplate;

    public List<Strip> sviStripovi(int brojStranice, int brojStripovaNaStranici){
        return stripRepository.findAll(PageRequest.of(brojStranice, brojStripovaNaStranici)).getContent();
    }

    public Strip jedanStrip(Long id_strip){
        Optional<Strip> strip = stripRepository.findById(id_strip);
        if(strip.isEmpty()) throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        return strip.get();
    }

    public List<Strip> stripoviPoAutoru(String ime, String prezime, int brojStranice, int brojStripovaNaStranici){
        if(ime == null || ime == "") ime = "-";
        if(prezime == null || prezime == "") prezime = "-";
        Set<Strip> stripovi = new HashSet<Strip>(stripRepository.findAllByAutori_ImeContains(ime, PageRequest.of(brojStranice, brojStripovaNaStranici)));
        stripovi.addAll(stripRepository.findAllByAutori_PrezimeContains(prezime, PageRequest.of(brojStranice, brojStripovaNaStranici)));
        return new ArrayList<Strip>(stripovi);
    }

    public List<Strip> stripoviPoIzdavacu(Long id_izdavac, int brojStranice, int brojStripovaNaStranici){
        return stripRepository.findByIdIzdavac(id_izdavac, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoZanru(Long id_zanr, int brojStranice, int brojStripovaNaStranici){
        return stripRepository.findByIdZanr(id_zanr, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoNazivu(String naziv, int brojStranice, int brojStripovaNaStranici){
        if(naziv == null) throw new ApiRequestException("Naziv mora biti poslan!");
        if(naziv.length() < 3) throw new ApiRequestException("Potrebna su barem tri slova u nazivu.");
        return stripRepository.findByNazivContains(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public Long dodajStrip(Strip strip){
        Long idIzdavac = strip.getIdIzdavac();
        Long idZanr = strip.getIdZanr();
        Double rating = strip.getUkupniRating();
        Integer brojKom = strip.getUkupnoKomentara();
        //validacija parametara za inicijalizaciju stripa
        if(strip.getNaziv().equals("") || strip.getNaziv() == null) throw new ApiRequestException("Strip mora imati naziv!");
        if(idIzdavac == null) throw new ApiRequestException("Strip mora imati izdavaca!");
        if(idZanr == null) throw new ApiRequestException("Strip mora imati zanr!");
        if(rating < 0) throw new ApiRequestException("Strip mora imati pozitivan rating!");
        if(strip.getSlika() == null) throw new ApiRequestException("Strip mora imati sliku!");
        if(brojKom < 0) throw new ApiRequestException("Strip mora imati pozitivan broj komentara!");
        if(strip.getAutori() == null || strip.getAutori().size() == 0) throw new ApiRequestException("Strip mora imati autore!");
        //provjera postoje li proslijedjeni zanr i izdavac
        if(zanrRepository.findById(idZanr).isEmpty()) throw new ApiRequestException("Zanr sa id-jem " + idZanr + " ne postoji.");
        if(izdavacRepository.findById(idIzdavac).isEmpty()) throw new ApiRequestException("Izdavac sa id-jem " + idIzdavac + " ne postoji.");
        stripRepository.save(strip);
        return strip.getId();
    }

    public Long brojStripovaUBazi(){
        return stripRepository.count();
    }
    
    public void azurirajStrip(StripRatingInfo stripRatingInfo) {
    	Strip strip_iz_baze=stripRepository.getOne(stripRatingInfo.getId());
    	strip_iz_baze.setUkupniRating(stripRatingInfo.getUkupniRating());
    	strip_iz_baze.setUkupnoKomentara(stripRatingInfo.getUkupnoKomentara());
    	stripRepository.save(strip_iz_baze);
	}
    
    public List<Strip> sviStripoviPoId(List<Long> idStripova){
        return stripRepository.findAllByIdIn(idStripova);
    }

	public ResponseEntity<Map<String, String>> komentariStripa(Long id) {
		ResponseEntity<Map<String, String>> komentari_stripa = restTemplate.exchange(
				"http://rating-service/komentari-stripa/" + id.toString(), HttpMethod.GET, null,
				new ParameterizedTypeReference<Map<String, String>>() {
				});
		return komentari_stripa;
	}

}
