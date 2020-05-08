package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.grpc.EventSubmission;
import comicbook.microsservice.comicbookmicroservice.grpc.Events;
import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import comicbook.microsservice.comicbookmicroservice.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZanrService {
    @Autowired
    ZanrRepository zanrRepository;
    private Long idAdmin = 1000L;
    @Autowired
    EventSubmission eventSubmission;

    public List<Zanr> sviZanrovi(){
        eventSubmission.submitEvent(idAdmin, Events.ActionType.GET, "Svi zanrovi");
        return zanrRepository.findAll();
    }

    public Long dodajZanr(Zanr zanr){
        if(zanr.getNaziv().equals("") || zanr.getNaziv() == null) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Naziv zanra ne smije biti prazan!");
            throw new ApiRequestException("Naziv zanra ne smije biti prazan!");
        }
        zanrRepository.save(zanr);
        eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Novi zanr, id: " + zanr.getId());
        return zanr.getId();
    }
}
