
package at.fhtw.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableScheduling
public class EnergyRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnergyRestApiApplication.class, args);
    }
}