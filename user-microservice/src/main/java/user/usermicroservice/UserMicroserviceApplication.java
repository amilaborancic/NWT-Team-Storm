package user.usermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import user.usermicroservice.Models.Role;
import user.usermicroservice.Models.User;
import user.usermicroservice.Repository.RoleRepository;
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
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(String... args) throws Exception{
		Role admin=new Role(RoleName.ROLE_ADMIN);
		Role user=new Role(RoleName.ROLE_USER);
		User u1 = new User(admin,"Amila", "Borancic",
				"Amila", "amila.borancic@gmail.com", passwordEncoder.encode("sifraamila"),
				0,5);

		User u2 = new User( user,"Mahira", "Buturovic",
				"Mahira", "mahira.buturovic@gmail.com", passwordEncoder.encode("siframahira"),
				0,5);
		User u3 = new User( user,"Ahmed", "Serdarevic",
				"Ahmo", "ahmo.arsenal@gmail.com", passwordEncoder.encode("sifrahmo"),
				4,5);
		roleRepository.save(admin);
		roleRepository.save(user);
		userRepository.save(u1);
		userRepository.save(u2);
		userRepository.save(u3);


	}

}

