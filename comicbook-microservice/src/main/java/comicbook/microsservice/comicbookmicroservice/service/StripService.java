package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.DTO.StripRatingInfo;
import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.grpc.EventSubmission;
import comicbook.microsservice.comicbookmicroservice.grpc.Events;
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
    @Autowired
    EventSubmission eventSubmission;
    private Long idAdmin = 1000L;
    private Long idLogovanogKorisnika = 500L;

    public List<Strip> sviStripovi(int brojStranice, int brojStripovaNaStranici){
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Svi stripovi, stranica " + brStr );
        return stripRepository.findAll(PageRequest.of(brojStranice, brojStripovaNaStranici)).getContent();
    }

    public Strip jedanStrip(Long id_strip){
        Optional<Strip> strip = stripRepository.findById(id_strip);
        if(strip.isEmpty()) {
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Strip ne postoji!");
            throw new ApiRequestException("Strip sa id-jem " + id_strip + " ne postoji.");
        }

        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Jedan strip, id: " + id_strip);
        return strip.get();
    }

    public List<Strip> stripoviPoAutoru(String ime, String prezime, int brojStranice, int brojStripovaNaStranici){

        if(ime == null || prezime == null || ime.equals("") && prezime.equals("")) return new ArrayList<>();
        else if(ime.equals("")) return stripRepository.findAllByAutori_PrezimeContains(prezime, PageRequest.of(brojStranice, brojStripovaNaStranici));
        else if(prezime.equals("")) return stripRepository.findAllByAutori_ImeContains(ime, PageRequest.of(brojStranice, brojStripovaNaStranici));
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Svi stripovi jednog autora, stranica " + brStr);

        return stripRepository.findAllByAutori_ImeContainsAndAutori_PrezimeContains(ime, prezime, PageRequest.of(brojStranice, brojStripovaNaStranici));

    }

    public List<Strip> stripoviPoIzdavacu(Long id_izdavac, int brojStranice, int brojStripovaNaStranici){
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Svi stripovi jednog izdavaca, stranica " + brStr);
        return stripRepository.findByIdIzdavac(id_izdavac, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoZanru(Long id_zanr, int brojStranice, int brojStripovaNaStranici){
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Svi stripovi jednog zanra, stranica " + brStr);
        return stripRepository.findByIdZanr(id_zanr, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public List<Strip> stripoviPoNazivu(String naziv, int brojStranice, int brojStripovaNaStranici){
        if(naziv == null) {
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Naziv mora biti poslan!");
            throw new ApiRequestException("Naziv mora biti poslan!");
        }
        if(naziv.length() < 3) {
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Potrebna su barem tri slova u nazivu.");
            throw new ApiRequestException("Potrebna su barem tri slova u nazivu.");
        }
        int brStr = brojStranice + 1;
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Svi stripovi sa kljucnom rjecju " + naziv + ", stranica " + brStr);
        return stripRepository.findByNazivContains(naziv, PageRequest.of(brojStranice, brojStripovaNaStranici));
    }

    public Long dodajStrip(Strip strip){
        Long idIzdavac = strip.getIdIzdavac();
        Long idZanr = strip.getIdZanr();
        Double rating = strip.getUkupniRating();
        Integer brojKom = strip.getUkupnoKomentara();
        //validacija parametara za inicijalizaciju stripa
        if(strip.getNaziv().equals("") || strip.getNaziv() == null) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati naziv!");
            throw new ApiRequestException("Strip mora imati naziv!");
        }
        if(idIzdavac == null){
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati izdavaca!");
            throw new ApiRequestException("Strip mora imati izdavaca!");
        }
        if(idZanr == null) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati zanr!");
            throw new ApiRequestException("Strip mora imati zanr!");
        }
        if(rating < 0) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati pozitivan rating!");
            throw new ApiRequestException("Strip mora imati pozitivan rating!");
        }
        if(strip.getSlika() == null) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati sliku!");
            throw new ApiRequestException("Strip mora imati sliku!");
        }
        if(brojKom < 0) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati pozitivan broj komentara!");
            throw new ApiRequestException("Strip mora imati pozitivan broj komentara!");
        }
        if(strip.getAutori() == null || strip.getAutori().size() == 0) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Strip mora imati autore!");
            throw new ApiRequestException("Strip mora imati autore!");
        }
        //provjera postoje li proslijedjeni zanr i izdavac
        if(zanrRepository.findById(idZanr).isEmpty()) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.GET, "Zanr ne postoji");
            throw new ApiRequestException("Zanr sa id-jem " + idZanr + " ne postoji.");
        }
        if(izdavacRepository.findById(idIzdavac).isEmpty()) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.GET, "Izdavac ne postoji");
            throw new ApiRequestException("Izdavac sa id-jem " + idIzdavac + " ne postoji.");
        }
        stripRepository.save(strip);

        eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Novi strip, id: " + strip.getId());

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
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.UPDATE, "Rating stripa azuriran.");
		}
		else {
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Strip ne postoji!.");
            throw new ApiRequestException("Strip sa id-jem " + stripRatingInfo.getId().toString() + " ne postoji.");
        }
	}

	//pomocna metoda
	public List<Strip> sviStripoviPoId(List<Long> idStripova) {
        eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Stripovi po id-ju.");
		return stripRepository.findAllByIdIn(idStripova);
	}

	public ResponseEntity<Map<String, String>> komentariStripa(Long id) {
		if (stripRepository.findById(id).isPresent()) {
			ResponseEntity<Map<String, String>> komentari_stripa = restTemplate.exchange(
					"http://rating-service/komentari-stripa/" + id.toString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<Map<String, String>>() {
					});
            eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Komentari stripa, id: " + id);
			return komentari_stripa;
		}
		eventSubmission.submitEvent(idLogovanogKorisnika, Events.ActionType.GET, "Strip ne postoji!");
		throw new ApiRequestException("Strip sa id-jem " + id.toString() + " ne postoji.");
	}

}
