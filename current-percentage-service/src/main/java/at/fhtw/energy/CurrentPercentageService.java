package at.fhtw.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CurrentPercentageService
{
    public static void main( String[] args )
    {
        SpringApplication.run(CurrentPercentageService.class, args);
    }
}
