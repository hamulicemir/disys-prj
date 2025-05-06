package at.fhtw.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CommunityEnergyUser
{
    public static void main( String[] args )
    {
        SpringApplication.run(CommunityEnergyUser.class, args);
    }
}
