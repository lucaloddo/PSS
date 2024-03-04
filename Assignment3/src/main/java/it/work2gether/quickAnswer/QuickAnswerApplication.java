package it.work2gether.quickAnswer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"it.work2gether.quickAnswer.entity"})
public class QuickAnswerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickAnswerApplication.class, args);
	}

}
