package eu.wecanconsulting.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@SpringBootApplication
public class Config {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(Config.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper()
				.registerModule(new Jdk8Module())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}
