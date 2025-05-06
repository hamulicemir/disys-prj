package at.fhtw.energy;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class UsageService
{
    public static void main( String[] args )
    {
        SpringApplication.run(UsageService.class, args);
    }
}
