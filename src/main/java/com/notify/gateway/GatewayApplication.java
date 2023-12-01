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
	String httpUriAgenda = uriConfiguration.getHttpagenda();	
	String httpUriNotes = uriConfiguration.getHttpnotes();	
	String httpUriGui = uriConfiguration.getHttpgui();


	return builder.routes()
		.route(p -> p
		.path("/agenda")
		.uri(httpUriAgenda))
		.route(p -> p
		.path("/notes")
		.uri(httpUriNotes))
		.route(p -> p
		.path("*")
		.uri(httpUriGui))
		.build();
	}

}

@ConfigurationProperties
class UriConfiguration {
  
  private String httpagenda = "http://10.24.16.9:80";  
  private String httpnotes = "http://10.24.16.10:80";  
  private String httpgui = "http://10.24.16.6:80";



  public String getHttpagenda() {
    return httpagenda;
  }
  public String getHttpnotes() {
    return httpnotes;
  }
  public String getHttpgui() {
    return httpgui;
  }

  public void setHttpagenda(String httpbin) {
    this.httpagenda = httpbin;
  }

  public void setHttpnotes(String httpbin) {
    this.httpnotes = httpbin;
  }

  public void setHttpgui(String httpbin) {
    this.httpgui = httpbin;
  }
}