package catalogue.microsservice.cataloguemicroservice;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "catalogue.microsservice.cataloguemicroservice")
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

	@Override
	public void run(String... args) throws Exception {
		Korisnik k1 = new Korisnik();
		Korisnik k2 = new Korisnik();
		korisnikRepozitorij.save(k1);
		korisnikRepozitorij.save(k2);

		Korisnik korisnik = korisnikRepozitorij.findAll().get(0);
		Katalog kat1 = new Katalog("Prvi katalog", korisnik.getId());

		katalogRepositorij.save(kat1);
	}

}