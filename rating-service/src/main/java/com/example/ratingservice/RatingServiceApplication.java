package com.example.ratingservice;

import com.example.ratingservice.modeli.Korisnik;
import com.example.ratingservice.modeli.Rating;
import com.example.ratingservice.modeli.Strip;
import com.example.ratingservice.repozitorij.KorisnikRepozitorij;
import com.example.ratingservice.repozitorij.RatingRepozitorij;
import com.example.ratingservice.repozitorij.StripRepozitorij;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableEurekaClient
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}
}

@Component
class DemoCommandLineRunner implements CommandLineRunner{
	
	@Autowired
	private RatingRepozitorij ratingRepozitorij;
	@Autowired
	private KorisnikRepozitorij korisnikRepozitorij;
	@Autowired
	private StripRepozitorij stripRepozitorij;
	
	@Override
	public void run(String... args) throws Exception {
		
		//unosi u tabelu
		Korisnik k1=new Korisnik();
		
		Strip s1=new Strip("skriveno lice",0,0);
		Strip s2=new Strip("magicni vjetar",0,0);
		
		k1.setBroj_losih_reviewa(0);
		k1.setUkupno_reviewa(1);
		
		s1.setUkupno_komentara(1);
		
		stripRepozitorij.save(s1);
		stripRepozitorij.save(s2);
		korisnikRepozitorij.save(k1);
		ratingRepozitorij.save(new Rating(k1,s1,4, "super strip"));
		//ratingRepozitorij.save(new Rating(k1,s2,1, "nisam odusevljen"));
	
		
	}
	
}


	

