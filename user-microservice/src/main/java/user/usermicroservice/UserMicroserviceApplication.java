package user.usermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.UserRepository;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class UserMicroserviceApplication {
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

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


