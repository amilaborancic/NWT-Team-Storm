package catalogue.microsservice.cataloguemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "catalogue.microsservice.cataloguemicroservice")
public class CatalogueMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueMicroserviceApplication.class, args);
	}

}
