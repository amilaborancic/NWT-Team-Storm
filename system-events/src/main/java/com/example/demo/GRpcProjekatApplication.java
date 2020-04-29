package com.example.demo;

import com.example.demo.Service.EventService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;

@EnableEurekaClient
@SpringBootApplication
public class GRpcProjekatApplication {

	public static void main(String[] args) throws InterruptedException, IOException {

		//SpringApplication.run(GRpcProjekatApplication.class, args);
		Server server = ServerBuilder.forPort(8084).addService(new EventService()).build();
		server.start();
		System.out.println("GRPC Running on port 8084");
		server.awaitTermination();
	}

}
