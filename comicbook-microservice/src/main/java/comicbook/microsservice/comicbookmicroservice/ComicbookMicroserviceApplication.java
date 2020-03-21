package comicbook.microsservice.comicbookmicroservice;

import comicbook.microsservice.comicbookmicroservice.model.Autor;
import comicbook.microsservice.comicbookmicroservice.model.Izdavac;
import comicbook.microsservice.comicbookmicroservice.model.Strip;
import comicbook.microsservice.comicbookmicroservice.model.Zanr;
import comicbook.microsservice.comicbookmicroservice.repository.AutorRepository;
import comicbook.microsservice.comicbookmicroservice.repository.IzdavacRepository;
import comicbook.microsservice.comicbookmicroservice.repository.StripRepository;
import comicbook.microsservice.comicbookmicroservice.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories(basePackages = "comicbook.microsservice.comicbookmicroservice.repository")
@SpringBootApplication
public class ComicbookMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicbookMicroserviceApplication.class, args);
	}

}

@Component
class InitialCommandLineRunner implements CommandLineRunner {
	@Autowired
	StripRepository stripRepository;
	@Autowired
	IzdavacRepository izdavacRepository;
	@Autowired
	AutorRepository autorRepository;
	@Autowired
	ZanrRepository zanrRepository;

	@Override
	public void run(String... args) throws Exception {
		//unosi u bazu
		//autori
		Autor a1 = new Autor("Stan", "Lee");
		Autor a2 = new Autor("Scott", "Snyder");
		//izdavaci
		Izdavac i1 = new Izdavac("Marvel");
		Izdavac i2 = new Izdavac("DC");
		//zanrovi
		Zanr z1 = new Zanr("akcija");
		Zanr z2 = new Zanr("horor");
		Zanr z3 = new Zanr("avantura");

		//spasavanje
		autorRepository.save(a1);
		autorRepository.save(a2);
		zanrRepository.save(z1);
		zanrRepository.save(z2);
		zanrRepository.save(z3);
		izdavacRepository.save(i1);
		izdavacRepository.save(i2);

		//stripovi
		String opis_1 = "Batman sets his sights on ending a case that started when he first started wearing the cape and cowl. Over the years he has found various clues and has been able to cross people of a list of who is behind this mysterious case.";
		String slika_1 = "https://drive.google.com/open?id=14I5xbWytYdeC7oBFkAu01AQZMEmQ01zE";
		List<Autor> autori_1 = new ArrayList<Autor>();
		autori_1.add(a1);
		autori_1.add(a2);
		Integer id_izdavac = izdavacRepository.findAll().get(0).getId();
		Integer id_zanr = zanrRepository.findAll().get(0).getId();
		Strip s1 = new Strip("Batman Detective Comics", opis_1, slika_1, 0, 0, 1000, autori_1, id_izdavac, id_zanr);
		stripRepository.save(s1);

	}
}