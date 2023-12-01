package com.notify.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@EnableAutoConfiguration
@RestController
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
	String httpUri = uriConfiguration.getHttpbin();
	return builder.routes()
		.route(p -> p
		.path("/api/*")
		.uri(httpUri))
		.route(p -> p
		.path("/")
		.uri(httpUri))
		.route(p -> p
		.path("")
		.uri("http://10.24.16.6:80"))
		.build();
	}

}

@ConfigurationProperties
class UriConfiguration {
  
  private String httpbin = "http://10.24.16.3:80";

  public String getHttpbin() {
    return httpbin;
  }

  public void setHttpbin(String httpbin) {
    this.httpbin = httpbin;
  }
}