package code.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("code.*")
@SpringBootApplication
public class Covid19Tracker {

	public static void main(String[] args) {
		SpringApplication.run(Covid19Tracker.class, args);
	}

}
