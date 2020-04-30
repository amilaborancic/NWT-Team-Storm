package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.grpc.EventSubmission;
import comicbook.microsservice.comicbookmicroservice.grpc.Events;
import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IzdavacService {
    @Autowired
    IzdavacRepository izdavacRepository;

    private Long idAdmin = 1000L;
    private Long idLogovanogKorisnika = 500L;

    public List<Izdavac> sviIzdavaci(){
        EventSubmission.submitEvent(idAdmin, Events.ActionType.GET, "Svi izdavaci");
        return izdavacRepository.findAll();
    }

    public Long dodajIzdavaca(Izdavac izdavac){
        if(izdavac.getNaziv().equals("") || izdavac.getNaziv() == null) throw new ApiRequestException("Naziv izdavaca ne smije biti prazan!");
        izdavacRepository.save(izdavac);
        EventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Novi izdavac, id: " + izdavac.getId());
        return izdavac.getId();
    }
}
