package catalogue.microsservice.cataloguemicroservice;

import catalogue.microsservice.cataloguemicroservice.model.Katalog;
import catalogue.microsservice.cataloguemicroservice.model.Korisnik;
import catalogue.microsservice.cataloguemicroservice.model.Strip;
import catalogue.microsservice.cataloguemicroservice.repository.KatalogRepository;
import catalogue.microsservice.cataloguemicroservice.repository.KorisnikRepository;
import catalogue.microsservice.cataloguemicroservice.repository.StripRepository;
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
	@Autowired
	private StripRepository stripRepozitorij;

	@Override
	public void run(String... args) throws Exception {
		Korisnik k1 = new Korisnik();
		Korisnik k2 = new Korisnik();
		korisnikRepozitorij.save(k1);
		korisnikRepozitorij.save(k2);

		Strip s1 = new Strip();
		Strip s2 = new Strip();

		stripRepozitorij.save(s1);
		stripRepozitorij.save(s2);

		Korisnik korisnik = korisnikRepozitorij.findAll().get(0);
		Korisnik korisnik_2 = korisnikRepozitorij.findAll().get(1);
		Katalog kat1 = new Katalog("Prvi katalog", korisnik.getId());
		Katalog kat2 = new Katalog("Drugi katalog", korisnik.getId());
		Katalog kat3 = new Katalog("Dummy za brisanje 1", korisnik_2.getId());
		Katalog kat4 = new Katalog("Test", korisnik.getId());

		katalogRepositorij.save(kat1);
		katalogRepositorij.save(kat2);
		katalogRepositorij.save(kat3);
		katalogRepositorij.save(kat4);

	}

}