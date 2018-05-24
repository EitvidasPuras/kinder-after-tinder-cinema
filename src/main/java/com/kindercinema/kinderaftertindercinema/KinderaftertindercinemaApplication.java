package com.kindercinema.kinderaftertindercinema;

import com.kindercinema.kinderaftertindercinema.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KinderaftertindercinemaApplication implements CommandLineRunner {

    private final SeedService seedService;

    @Autowired
    public KinderaftertindercinemaApplication(SeedService seedService) {
        this.seedService = seedService;
    }

    public static void main(String[] args) {
        SpringApplication.run(KinderaftertindercinemaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        seedService.seed();
    }


}

