package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableConfigServer
public class Application {
	private static final String InternalCertPath="C:\\tools\\Java\\jdk1.8.0_111\\jre\\lib\\security\\jssecacerts";
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", InternalCertPath);
		SpringApplication.run(Application.class, args);
	}
}
