package catalogue.microsservice.cataloguemicroservice;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
import catalogue.microsservice.cataloguemicroservice.service.KorisnikService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.StrictMath.ceil;
import static java.lang.StrictMath.round;


@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = "catalogue.microsservice.cataloguemicroservice")
@EnableSwagger2
public class CatalogueMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogueMicroserviceApplication.class, args);
	}
}

@Component
class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private KatalogRepository katalogRepositorij;
	@Autowired
	private KorisnikRepository korisnikRepozitorij;
	@Autowired
	private StripRepository stripRepozitorij;

	@Override
	public void run(String... args) throws Exception {

		//NE BRISATI OVO!!

        //korisnike dobavljamo od user servisa
		/*
        RestTemplate korisnici = new RestTemplate();
        String resourceURL = "http://localhost:8080/svi/useri";
        ResponseEntity<String> response = korisnici.getForEntity(resourceURL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
		root.forEach(korisnik->{
			Korisnik k = new Korisnik(korisnik.path("id").asLong());
			korisnikRepozitorij.save(k);
		});*/

		Korisnik k1 = new Korisnik((long) 1);
		Korisnik k2 = new Korisnik((long) 2);
		korisnikRepozitorij.save(k1);
		korisnikRepozitorij.save(k2);

		/*Strip s1 = new Strip();
		Strip s2 = new Strip();
		Strip s3 = new Strip();
		Strip s4 = new Strip();

		stripRepozitorij.save(s1);
		stripRepozitorij.save(s2);
		stripRepozitorij.save(s3);
		stripRepozitorij.save(s4);*/

		/*stripove dobijamo iz strip servisa*/
		RestTemplate stripoviIzStripServisa = new RestTemplate();
		String urlUkupnoStripova = "http://localhost:8083/strip/count";
		String urlBrojNaStranici = "http://localhost:8083/strip/brojNaStranici";
		String urlStripoviNaStranici = "http://localhost:8083/strip/svi";
		ResponseEntity<Long> responseBrojStripova = stripoviIzStripServisa.getForEntity(urlUkupnoStripova, Long.class);
		ResponseEntity<Integer> responseBrojNaStranici = stripoviIzStripServisa.getForEntity(urlBrojNaStranici, int.class);
		ObjectMapper mapperStripovi = new ObjectMapper();
		Long brojStripova = mapperStripovi.readTree(String.valueOf(responseBrojStripova.getBody())).asLong();
		Integer brojNaStranici = mapperStripovi.readTree(String.valueOf(responseBrojNaStranici.getBody())).asInt();
		int brojStranica = (int) round((double)brojStripova/brojNaStranici + 0.5);
		int i=0;
		while(i<brojStranica){
			ResponseEntity<String> stripoviSaStranice = stripoviIzStripServisa.getForEntity(urlStripoviNaStranici + "?brojStranice="+i, String.class);
			JsonNode svi = mapperStripovi.readTree(stripoviSaStranice.getBody());
			svi.forEach(strip->{
				Strip s = new Strip(strip.path("id").asLong());
				stripRepozitorij.save(s);
			});
			i++;
		}


		Korisnik korisnik = korisnikRepozitorij.findAll().get(0);
		Korisnik korisnik_2 = korisnikRepozitorij.findAll().get(1);
		Katalog kat1 = new Katalog("Prvi katalog", korisnik.getId());
		Katalog kat2 = new Katalog("Drugi katalog", korisnik.getId());
		Katalog kat3 = new Katalog("Dummy za brisanje 1", korisnik_2.getId());
		Katalog kat4 = new Katalog("Test", korisnik.getId());
		//2 defaultna kataloga
		Katalog kat_procitano_1 = new Katalog("Procitano", korisnik.getId());
		Katalog kat_procitano_2 = new Katalog("Procitano", korisnik_2.getId());
		Katalog kat_zelim_procitati_1 = new Katalog("Zelim procitati", korisnik.getId());
		Katalog kat_zelim_procitati_2 = new Katalog("Zelim procitati", korisnik_2.getId());

		katalogRepositorij.save(kat1);
		katalogRepositorij.save(kat2);
		katalogRepositorij.save(kat3);
		katalogRepositorij.save(kat4);
		katalogRepositorij.save(kat_procitano_1);
		katalogRepositorij.save(kat_procitano_2);
		katalogRepositorij.save(kat_zelim_procitati_1);
		katalogRepositorij.save(kat_zelim_procitati_2);

		//dodavanje stripova
	/*	List<Strip> stripovi = new ArrayList<>();
		stripovi.add(s1);
		stripovi.add(s2);
		stripovi.add(s3);
		stripovi.add(s4);
		kat3.setStripovi(stripovi);*/

		katalogRepositorij.save(kat3);
	}

}