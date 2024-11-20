package com.zamecki.Podcastly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
public class PodcastlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PodcastlyApplication.class, args);
	}

}
