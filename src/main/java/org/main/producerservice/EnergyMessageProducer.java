package org.main.producerservice;

import org.main.model.EnergyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EnergyMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public EnergyMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        EnergyMessage msg = new EnergyMessage();
        msg.setType("PRODUCER");
        msg.setAssociation("COMMUNITY");
        msg.setKwh(0.002 + random.nextDouble() * 0.003);
        msg.setDatetime(LocalDateTime.now());

        rabbitTemplate.convertAndSend("energy.queue", msg);
        System.out.println("Producer sent: " + msg.getKwh() + " kWh");
    }
}
