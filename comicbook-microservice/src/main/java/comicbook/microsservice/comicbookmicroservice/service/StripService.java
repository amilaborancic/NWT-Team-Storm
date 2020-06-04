package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
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
import static java.lang.StrictMath.round;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return stripRepository.findAllByOrderByNaziv(PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public Strip jedanStrip(Long id_strip){
        Optional<Strip> strip = stripRepository.findById(id_strip);
        if(strip.isEmpty()) throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        return strip.get();
    }

    public List<Strip> stripoviPoAutoru(String ime, String prezime, int brojStranice, int brojStripovaNaStranici){
        if(ime == null || prezime == null || ime.equals("") && prezime.equals("")) return new ArrayList<>();
        else if(ime.equals("")) return stripRepository.findAllByAutori_PrezimeContainsOrderByNaziv(prezime, PageRequest.of(brojStranice, brojStripovaNaStranici));
        else if(prezime.equals("")) return stripRepository.findAllByAutori_ImeContainsOrderByNaziv(ime, PageRequest.of(brojStranice, brojStripovaNaStranici));
        return stripRepository.findAllByAutori_ImeContainsAndAutori_PrezimeContainsOrderByNaziv(ime, prezime, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoIzdavacu(Long id_izdavac, int brojStranice, int brojStripovaNaStranici){
        return stripRepository.findByIdIzdavacOrderByNaziv(id_izdavac, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoZanru(Long id_zanr, int brojStranice, int brojStripovaNaStranici){
        return stripRepository.findByIdZanrOrderByNaziv(id_zanr, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoNazivu(String naziv, int brojStranice, int brojStripovaNaStranici){
        if(naziv == null) {
            throw new ApiRequestException("Naziv mora biti poslan!");
        }
        if(naziv.length() < 3) {
            throw new ApiRequestException("Potrebna su barem tri slova u nazivu.");
        }
        return stripRepository.findByNazivContainsOrderByNaziv(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public Long dodajStrip(Strip strip){
        Long idIzdavac = strip.getIdIzdavac();
        Long idZanr = strip.getIdZanr();
        Double rating = strip.getUkupniRating();
        Integer brojKom = strip.getUkupnoKomentara();
        //validacija parametara za inicijalizaciju stripa
        if(strip.getNaziv().equals("") || strip.getNaziv() == null) {
            throw new ApiRequestException("Strip mora imati naziv!");
        }
        if(idIzdavac == null){
            throw new ApiRequestException("Strip mora imati izdavaca!");
        }
        if(idZanr == null) {
            throw new ApiRequestException("Strip mora imati zanr!");
        }
        if(rating < 0) {
            throw new ApiRequestException("Strip mora imati pozitivan rating!");
        }
        if(strip.getSlika() == null) {
            throw new ApiRequestException("Strip mora imati sliku!");
        }
        if(brojKom < 0) {
            throw new ApiRequestException("Strip mora imati pozitivan broj komentara!");
        }
        if(strip.getAutori() == null || strip.getAutori().size() == 0) {
            throw new ApiRequestException("Strip mora imati autore!");
        }
        //provjera postoje li proslijedjeni zanr i izdavac
        if(zanrRepository.findById(idZanr).isEmpty()) {
            throw new ApiRequestException("Zanr sa id-jem " + idZanr + " ne postoji.");
        }
        if(izdavacRepository.findById(idIzdavac).isEmpty()) {
            throw new ApiRequestException("Izdavac sa id-jem " + idIzdavac + " ne postoji.");
        }
        stripRepository.save(strip);
        return strip.getId();
    }

    public Long brojStripovaUBazi(){
        return stripRepository.count();
    }
    
	public void azurirajStrip(StripRatingInfo stripRatingInfo) {
		if (stripRepository.findById(stripRatingInfo.getId()).isPresent()) {
			Strip strip_iz_baze = stripRepository.getOne(stripRatingInfo.getId());
			strip_iz_baze.setUkupniRating(stripRatingInfo.getUkupniRating());
			strip_iz_baze.setUkupnoKomentara(stripRatingInfo.getUkupnoKomentara());
			stripRepository.save(strip_iz_baze);
		}
		else {
            throw new ApiRequestException("Strip sa id-jem " + stripRatingInfo.getId().toString() + " ne postoji.");
        }
	}

	//pomocna metoda
	public List<Strip> sviStripoviPoId(List<Long> idStripova) {
		return stripRepository.findAllByIdIn(idStripova);
	}

	public ResponseEntity<Map<String, String>> komentariStripa(Long id) {
		if (stripRepository.findById(id).isPresent()) {
			ResponseEntity<Map<String, String>> komentari_stripa = restTemplate.exchange("http://rating-service/rating/komentari-stripa/" + id.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, String>>() {});
			return komentari_stripa;
		}
		throw new ApiRequestException("Strip sa id-jem " + id.toString() + " ne postoji.");
	}

	public Long brojStripovaPoZanru(Long idZanr){return stripRepository.countStripByIdZanr(idZanr);}
	public Long brojStripovaPoIzdavacu(Long idIzdavac){return stripRepository.countStripByIdIzdavac(idIzdavac);}

	public Long brojStripovaPoAutorIme(String ime, String prezime){
        if(ime == null || prezime == null || ime.equals("") && prezime.equals("")) return 0L;
        else if(ime.equals("")) return stripRepository.countStripByAutori_PrezimeContains(prezime);
        else if(prezime.equals("")) return stripRepository.countStripByAutori_ImeContains(ime);
        return stripRepository.countStripByAutori_ImeContainsAndAutori_PrezimeContains(ime, prezime);
    }
    public Long brojStripovaPoNazivu(String naziv){
        return stripRepository.countStripByNazivContains(naziv);
    }
	public int brojStranica(int brojStripova, int brojNaStranici){
        return (int) round((double)brojStripova/brojNaStranici + 0.5);
    }
	
    public List<Autor> autoriStripa(Long id){
        List<Autor> autori=stripRepository.getOne(id).getAutori();
        return autori;
    }

}
