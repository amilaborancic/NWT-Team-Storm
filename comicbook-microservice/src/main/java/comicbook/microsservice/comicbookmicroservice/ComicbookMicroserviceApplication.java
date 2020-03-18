package comicbook.microsservice.comicbookmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "comicbook.microsservice.comicbookmicroservice.repository")
@SpringBootApplication
public class ComicbookMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComicbookMicroserviceApplication.class, args);
	}

}
