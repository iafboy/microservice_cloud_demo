package io.pivotal.microservices;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import io.pivotal.microservices.common.CommonParams;
@EnableDiscoveryClient
@EnableAutoConfiguration
@ComponentScan(basePackages = CommonParams.BASEACCOUNTPATH)
public class AccountsServer {
	protected static Logger logger = Logger.getLogger(AccountsServer.class.getName());
	
	public static void main(String[] args) {
		System.setProperty("spring.mvc.dispatch-options-request","true");
		SpringApplication.run(AccountsServer.class, args);
	
	}
}
