package work.worker.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import work.worker.server.Repositories.BookRepository;


@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class ServerApplication {

	/**autowire book repo. */
	@Autowired
	BookRepository bookRepo;

	/**
	 *  Makes a rest template that can read images.
	 * @param messageConverters
	 * @return asdf
	 */
	@Bean
	public RestTemplate restTemplate(
		final List<HttpMessageConverter<?>> messageConverters) {
		return new RestTemplate(messageConverters);
	}

	/**
	 * makes byte array converter.
	 * @return converter
	 */
	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		return new ByteArrayHttpMessageConverter();
	}

	/**
	 * main code.
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
