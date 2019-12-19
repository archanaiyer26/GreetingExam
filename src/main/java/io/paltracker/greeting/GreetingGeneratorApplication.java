package io.paltracker.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class GreetingGeneratorApplication {

    public static void main(String args[]){

        SpringApplication.run(GreetingGeneratorApplication.class,args);

    }

    @Bean
    JdbcTimeEntryRepository getJDBCTimeEntryRepository(DataSource dataSource){


        return  new JdbcTimeEntryRepository(dataSource);

    }
}
