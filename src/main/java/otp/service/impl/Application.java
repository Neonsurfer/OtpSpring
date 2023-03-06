package otp.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import otp.dao.DBConnection;

import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}, scanBasePackages = {"otp.dao", "otp.service"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            DBConnection dbConnection = new DBConnection();
            Connection conn = dbConnection.getConnection();
            Statement statement = conn.createStatement();
            statement.execute("SELECT * FROM person");
        };
    }
}
