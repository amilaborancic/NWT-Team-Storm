package catalogue.microsservice.cataloguemicroservice.RabbitMQ;
import catalogue.microsservice.cataloguemicroservice.exception.ApiRequestException;
import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    KorisnikRepository korisnikRepository;
    @Autowired
    KatalogRepository katalogRepository;

    public void receiveMessage(String id_korisnika){

        logger.info("Poruka primljena");
        if(id_korisnika!=null && korisnikRepository.findById(Long.valueOf(id_korisnika)).isEmpty()) {
                korisnikRepository.save(new Korisnik(Long.valueOf(id_korisnika)));
                Katalog zelim = new Katalog("Zelim procitati", Long.valueOf(id_korisnika));
                Katalog procitano = new Katalog("Procitano", Long.valueOf(id_korisnika));
                katalogRepository.save(zelim);
                katalogRepository.save(procitano);
      	        logger.info("Kreirani defaultni katalozi");
        }
        else throw new ApiRequestException("Pogrešan id korisnika!");
    }
}
