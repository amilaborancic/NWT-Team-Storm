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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories(basePackages = "comicbook.microsservice.comicbookmicroservice.repository")
@SpringBootApplication
@EnableSwagger2
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
		Autor a3 = new Autor("David", "Michelinie");
		Autor a4 = new Autor("Marguerite", "Bennett");
		Autor a5 = new Autor("Duane", "Swierczynski");
		Autor a6 = new Autor("Kevin", "Eastman");
		Autor a7 = new Autor("Ed", "Brisson");

		//izdavaci
		Izdavac i1 = new Izdavac("Marvel");
		Izdavac i2 = new Izdavac("DC");
		Izdavac i3 = new Izdavac("Mirage Studios");

		//zanrovi
		Zanr z1 = new Zanr("akcija");
		Zanr z2 = new Zanr("horor");
		Zanr z3 = new Zanr("avantura");

		//spasavanje
		autorRepository.save(a1);
		autorRepository.save(a2);
		autorRepository.save(a3);
		autorRepository.save(a4);
		autorRepository.save(a5);
		autorRepository.save(a6);
		autorRepository.save(a7);

		zanrRepository.save(z1);
		zanrRepository.save(z2);
		zanrRepository.save(z3);

		izdavacRepository.save(i1);
		izdavacRepository.save(i2);
		izdavacRepository.save(i3);

		//stripovi
		String opis_1 = "Batman sets his sights on ending a case that started when he first started wearing the cape and cowl. Over the years he has found various clues and has been able to cross people of a list of who is behind this mysterious case.";
		String slika_1 = "https://drive.google.com/open?id=14I5xbWytYdeC7oBFkAu01AQZMEmQ01zE";
		List<Autor> autori_1 = new ArrayList<>();
		autori_1.add(a1);
		autori_1.add(a2);

		String opis_2 = "Stark's final major project is the \"Big Jump\" a space elevator that will greatly reduce the cost of travel; however, failing health and sabotage by Roxxon in the form of the Ultra-Dynamo prove too much for him to handle. A young scientist named Nick Travis becomes Stark's successor as lead tech and soon after, Iron Man.";
		String slika_2 = "https://drive.google.com/open?id=16-T_6YxvuJBMTG4ySoDVEU9uRPXz3BtS";
		List<Autor> autori_2 = new ArrayList<>();
		autori_2.add(a3);
		autori_2.add(a1);
		autori_2.add(a2);

		String opis_3 = "It's the climactic conclusion to \"American Soil.\" Donna Troy and Cassie Sandsmark are left to defend their hometown from persecution. In the shadow of their foes, and with their friends at their backs, we have the rise of the Wonder Girls!";
		String slika_3 = "https://drive.google.com/open?id=1NoXo3oXwwSrcybpAwRoJdFQFeCowUyLA";
		List<Autor> autori_3 = new ArrayList<>();
		autori_3.add(a4);

		String opis_4 = "One is wanted for a murder she didn't commit. The other is on the run because she knows too much. They are Dinah Laurel Lance and Ev Crawford - a.k.a. Black Canary and Starling - and joining them are the villainous Poison Ivy and the heroic Batgirl and together, as Gotham City's covert ops team, they're taking down the villains other heroes can't touch. They are the Birds of Prey.\n";
		String slika_4 = "https://drive.google.com/open?id=1fM4pT2okoN0fXuNPmvDRcWmc5UCaPdVi";
		List<Autor> autori_4 = new ArrayList<>();
		autori_4.add(a5);

		String opis_5 = "Raphael and Alopex are trapped in Null Industries and face brand-new enemies that force them to question their future! ";
		String slika_5 = "https://drive.google.com/open?id=102_5i4gvxsvADaF6o2nS1i2m8AnncU27";
		List<Autor> autori_5 = new ArrayList<>();
		autori_5.add(a6);

		String opis_6 = "Enter the Age of X-Man, with the perfect heroes for a perfect world! The X-Men have helped make the planet into a utopia where living in fear and hatred is a thing of the past. All people are united under the banner of mutantkind, and all of mutantkind idolizes the X-Men. ";
		String slika_6 = "https://drive.google.com/open?id=12ZdhhN8LfYH75SgXkVYvhhi1AiC94kQU";
		List<Autor> autori_6 = new ArrayList<>();
		autori_6.add(a7);

		Long id_marvel = izdavacRepository.findAll().get(0).getId(); //marvel
		Long id_dc = izdavacRepository.findAll().get(1).getId(); //dc
		Long id_mirage = izdavacRepository.findAll().get(2).getId();
		Long id_zanr = zanrRepository.findAll().get(0).getId();

		Strip s1 = new Strip("Batman Detective Comics", opis_1, slika_1, 0.0, 0, 1000, id_marvel, id_zanr, autori_1);
		Strip s2 = new Strip("Iron Man The End", opis_2, slika_2, 0.0, 0, 1, id_marvel, id_zanr, autori_2);
		Strip s3 = new Strip("Bombshells United", opis_3, slika_3, 0.0, 0, 6, id_dc, id_zanr, autori_3);
		Strip s4 = new Strip("Birds of Prey Trouble in Mind", opis_4, slika_4, 0.0, 0, 1, id_dc, id_zanr, autori_4);
		Strip s5 = new Strip("Teenage Mutant Ninja Turtles Universe", opis_5, slika_5, 0.0, 0, 20, id_mirage, id_zanr, autori_5);
		Strip s6 = new Strip("Uncanny X-Men (2018-2019) Annual", opis_6, slika_6, 0.0, 0, 1, id_marvel, id_zanr, autori_6);

		stripRepository.save(s1);
		stripRepository.save(s2);
		stripRepository.save(s3);
		stripRepository.save(s4);
		stripRepository.save(s5);
		stripRepository.save(s6);

		/*s1.setAutori(autori_1);
		s2.setAutori(autori_2);
		s3.setAutori(autori_3);
		s4.setAutori(autori_4);
		s5.setAutori(autori_5);
		s6.setAutori(autori_6);

		stripRepository.save(s1);
		stripRepository.save(s2);
		stripRepository.save(s3);
		stripRepository.save(s4);
		stripRepository.save(s5);
		stripRepository.save(s6);*/
	}
}