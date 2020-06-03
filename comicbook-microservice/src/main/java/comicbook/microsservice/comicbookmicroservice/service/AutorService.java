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

    @Autowired
    private EventSubmission eventSubmission;
    private Long idAdmin = 1000L;

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
        res.put("nazivResursa", "Svi autori");
        return res;
    }
    public ObjectNode dodajAutora(Autor autor){
        //provjera da li su ime i prezime korektni
        if(autor.getIme().equals("") || autor.getIme() == null) {
          //  eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Ime autora ne smije biti prazno!");
            throw new ApiRequestException("Ime autora ne smije biti prazno!");
        }
        if(autor.getPrezime().equals("") || autor.getPrezime() == null) {
            eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Prezime autora ne smije biti prazno!");
            throw new ApiRequestException("Prezime autora ne smije biti prazno!");
        }
        autorRepository.save(autor);
        eventSubmission.submitEvent(idAdmin, Events.ActionType.CREATE, "Novi autor, id: " + autor.getId());
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode1 = mapper.createObjectNode();
        objectNode1.put("id", autor.getId().toString());
        objectNode1.put("nazivResursa", "Novi autor, id: " + autor.getId().toString());
        return objectNode1;
    }
}
