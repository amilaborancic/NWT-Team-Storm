package user.usermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;

@EnableAutoConfiguration
@SpringBootApplication
@EnableSwagger2
public class UserMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception{

		User u1 = new User( "Amila", "Borancic",
				"Amila", "amila.borancic@gmail.com", "sifraamila",
				0,5);

		User u2 = new User( "Mahira", "Buturovic",
				"Mahira", "mahira.buturovic@gmail.com", "siframahira",
				0,5);
		User u3 = new User( "Ahmed", "Serdarevic",
				"Ahmo", "ahmo.arsenal@gmail.com", "sifrahmo",
				4,5);

		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);


	}

}


