package comicbook.microsservice.comicbookmicroservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import comicbook.microsservice.comicbookmicroservice.exceptions.ApiRequestException;
import comicbook.microsservice.comicbookmicroservice.grpc.EventSubmission;
import comicbook.microsservice.comicbookmicroservice.grpc.Events;
import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    AutorRepository autorRepository;

    public ObjectNode sviAutori(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode res = mapper.createObjectNode();
        ArrayNode arrayNode = mapper.createArrayNode();
        List<Autor> autori = autorRepository.findAll();
        for(Autor autor : autori){
            ObjectNode autorProperties = mapper.createObjectNode();
            autorProperties.put("id", autor.getId());
            autorProperties.put("ime", autor.getIme());
            autorProperties.put("prezime", autor.getPrezime());
            arrayNode.add(autorProperties);
        }
        res.putPOJO("autori", arrayNode);
        return res;
    }
    public Long dodajAutora(Autor autor){
        //provjera da li su ime i prezime korektni
        if(autor.getIme().equals("") || autor.getIme() == null) {
            throw new ApiRequestException("Ime autora ne smije biti prazno!");
        }
        if(autor.getPrezime().equals("") || autor.getPrezime() == null) {
            throw new ApiRequestException("Prezime autora ne smije biti prazno!");
        }
        autorRepository.save(autor);
        return autor.getId();
    }
}
