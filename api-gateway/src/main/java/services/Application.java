package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class Application {
	private static final String InternalCertPath="C:\\tools\\Java\\jdk1.8.0_111\\jre\\lib\\security\\jssecacerts";
	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", InternalCertPath);
		SpringApplication.run(Application.class, args);
	}
}
