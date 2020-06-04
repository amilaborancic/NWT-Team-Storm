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

    public List<Zanr> sviZanrovi(){
        return zanrRepository.findAll();
    }

    public Long dodajZanr(Zanr zanr){
        if(zanr.getNaziv().equals("") || zanr.getNaziv() == null) {
            throw new ApiRequestException("Naziv zanra ne smije biti prazan!");
        }
        zanrRepository.save(zanr);
        return zanr.getId();
    }
}
