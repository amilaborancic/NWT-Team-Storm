package comicbook.microsservice.comicbookmicroservice.service;

import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public List<Autor> sviAutori(){
        return autorRepository.findAll();
    }
    public Long dodajAutora(Autor autor){
        //provjera da li su ime i prezime korektni
        if(autor.getIme().equals("") || autor.getIme() == null) throw new ApiRequestException("Ime autora ne smije biti prazno!");
        if(autor.getPrezime().equals("") || autor.getPrezime() == null) throw new ApiRequestException("Prezime autora ne smije biti prazno!");
        autorRepository.save(autor);
        return autor.getId();
    }
}
