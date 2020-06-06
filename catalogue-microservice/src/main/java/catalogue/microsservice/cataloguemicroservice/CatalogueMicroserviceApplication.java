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

		//dobavimo usere iz user servisa
		String resourceURL = "http://user-service/user/svi";
		ResponseEntity<String> response = restTemplate.getForEntity(resourceURL, String.class);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		root.forEach(korisnik->{
			Korisnik k = new Korisnik(korisnik.path("id").asLong());
			korisnikRepozitorij.save(k);
		});

		//dobavimo stripove iz strip servisa
		String urlStripoviNaStranici = "http://comicbook-service/strip/svi";
		ObjectMapper mapperStripovi = new ObjectMapper();
		int brojStranica = 1;

		int i=0;
		while(i<brojStranica){
			ResponseEntity<String> stripoviSaStranice = restTemplate.getForEntity(urlStripoviNaStranici + "?brojStranice="+i, String.class);
			brojStranica = mapperStripovi.readTree(stripoviSaStranice.getBody()).path("brojStranica").asInt();
			JsonNode svi = mapperStripovi.readTree(stripoviSaStranice.getBody()).path("stripovi");
			List<Strip> stripovi = new ArrayList<>();
			svi.forEach(strip->{
				Strip s = new Strip(strip.path("id").asLong());
				stripovi.add(s);
			});
			stripRepozitorij.saveAll(stripovi);
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