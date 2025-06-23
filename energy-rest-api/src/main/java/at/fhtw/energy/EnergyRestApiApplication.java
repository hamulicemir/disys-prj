// energy-rest-api/src/main/java/at/fhtw/energy/EnergyRestApiApplication.java
package at.fhtw.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "at.fhtw.energy.repository")
public class EnergyRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EnergyRestApiApplication.class, args);
    }
}