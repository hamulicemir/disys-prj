package at.fhtw.energy;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
public class UsageService
{
    public static void main( String[] args )
    {
        SpringApplication.run(UsageService.class, args);
    }
}
