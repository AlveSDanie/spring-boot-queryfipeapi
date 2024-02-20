package com.fipeapi.queryfipeapi;

import com.fipeapi.queryfipeapi.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryfipeapiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(QueryfipeapiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Main main = new Main();
        main.showMenu();

    }
}
