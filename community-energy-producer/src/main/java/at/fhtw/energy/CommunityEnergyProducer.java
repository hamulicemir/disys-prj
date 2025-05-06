package at.fhtw.energy;

import org.springframework.boot.SpringApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CommunityEnergyProducer
{
    public static void main( String[] args )
    {
        SpringApplication.run(CommunityEnergyProducer.class, args);
    }
}
