package at.fhtw.energy.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String ENERGY_QUEUE = "energy.queue";
    public static final String UPDATE_QUEUE = "update.queue";

    // Queue automatisch erstellen
    @Bean
    public Queue energyQueue() {
        return new Queue(ENERGY_QUEUE, true); // durable = true
    }

    // Queue automatisch erstellen
    @Bean
    public Queue updateQueue() {
        return new Queue(UPDATE_QUEUE, true);
    }

    // RabbitTemplate
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
