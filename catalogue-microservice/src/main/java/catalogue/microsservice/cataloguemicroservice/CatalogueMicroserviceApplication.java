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
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
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
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
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
	@Autowired
	RestTemplate restTemplate;

	@Override
	public void run(String... args) throws Exception {

		//NE BRISATI OVO!!
		//korisnike dobavljamo od user servisa
		String resourceURL = "http://user-service/user/svi";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		root.forEach(korisnik->{
			Korisnik k = new Korisnik(korisnik.path("id").asLong());
			korisnikRepozitorij.save(k);
		});
/*
		Korisnik k1 = new Korisnik((long) 1);
		Korisnik k2 = new Korisnik((long) 2);
		Korisnik k3 = new Korisnik((long) 3);
		Korisnik k4 = new Korisnik((long) 4);
		korisnikRepozitorij.save(k1);
		korisnikRepozitorij.save(k2);
		korisnikRepozitorij.save(k3);
		korisnikRepozitorij.save(k4);

		Strip s1 = new Strip((long) 1);
		Strip s2 = new Strip((long) 2);
		Strip s3 = new Strip((long) 3);
		Strip s4 = new Strip((long) 4);
		Strip s5 = new Strip((long) 5);
		Strip s6 = new Strip((long) 6);


		stripRepozitorij.save(s1);
		stripRepozitorij.save(s2);
		stripRepozitorij.save(s3);
		stripRepozitorij.save(s4);
		stripRepozitorij.save(s5);
		stripRepozitorij.save(s6);
*/
		/*stripove dobijamo iz strip servisa*/

		String urlUkupnoStripova = "http://comicbook-service/strip/count";
		String urlBrojNaStranici = "http://comicbook-service/strip/brojNaStranici";
		String urlStripoviNaStranici = "http://comicbook-service/strip/svi";
		ResponseEntity<Long> responseBrojStripova = restTemplate.getForEntity(urlUkupnoStripova, Long.class);
		ResponseEntity<Integer> responseBrojNaStranici = restTemplate.getForEntity(urlBrojNaStranici, int.class);
		ObjectMapper mapperStripovi = new ObjectMapper();
		Long brojStripova = mapperStripovi.readTree(String.valueOf(responseBrojStripova.getBody())).asLong();
		Integer brojNaStranici = mapperStripovi.readTree(String.valueOf(responseBrojNaStranici.getBody())).asInt();
		int brojStranica = (int) round((double)brojStripova/brojNaStranici + 0.5);
		int i=0;
		while(i<brojStranica){
			ResponseEntity<String> stripoviSaStranice = restTemplate.getForEntity(urlStripoviNaStranici + "?brojStranice="+i, String.class);
			JsonNode svi = mapperStripovi.readTree(stripoviSaStranice.getBody()).path("stripovi");
			svi.forEach(strip->{
				Strip s = new Strip(strip.path("id").asLong());
				stripRepozitorij.save(s);
			});
			i++;
		}

		Korisnik korisnik = korisnikRepozitorij.getOne(2L);
		Korisnik korisnik_2 = korisnikRepozitorij.getOne(3L);
		Katalog kat1 = new Katalog("Prvi katalog", korisnik.getId());
		Katalog kat2 = new Katalog("Drugi katalog", korisnik.getId());
		Katalog kat3 = new Katalog("Treći katalog", korisnik_2.getId());
		Katalog kat4 = new Katalog("Test", korisnik.getId());
		Katalog kat5 = new Katalog("Pročitani stripovi", korisnik_2.getId());
		Katalog kat6 = new Katalog("Želim pročitati", korisnik_2.getId());
		Katalog kat7 = new Katalog("Pročitani stripovi", korisnik.getId());
		Katalog kat8 = new Katalog("Želim pročitati", korisnik.getId());

		katalogRepositorij.save(kat1);
		katalogRepositorij.save(kat2);
		katalogRepositorij.save(kat3);
		katalogRepositorij.save(kat4);
		katalogRepositorij.save(kat5);
		katalogRepositorij.save(kat6);
		katalogRepositorij.save(kat7);
		katalogRepositorij.save(kat8);

		//dodavanje stripova u nekoliko kataloga
		List<Strip> stripovi = new ArrayList<>();
		stripovi.add(stripRepozitorij.getOne(2L));
		stripovi.add(stripRepozitorij.getOne(4L));
		stripovi.add(stripRepozitorij.getOne(1L));
		kat3.setStripovi(stripovi);
		katalogRepositorij.save(kat3);

		stripovi = new ArrayList<>();
		stripovi.add(stripRepozitorij.getOne(1L));
		stripovi.add(stripRepozitorij.getOne(2L));
		stripovi.add(stripRepozitorij.getOne(3L));
		stripovi.add(stripRepozitorij.getOne(4L));
		stripovi.add(stripRepozitorij.getOne(5L));
		stripovi.add(stripRepozitorij.getOne(6L));
		kat1.setStripovi(stripovi);
		katalogRepositorij.save(kat1);

	}

}