package work.worker.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import work.worker.server.Repositories.BookRepository;


@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class ServerApplication {

	/**autowire book repo. */
	@Autowired
	BookRepository bookRepo;

	/**
	 * main code.
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
