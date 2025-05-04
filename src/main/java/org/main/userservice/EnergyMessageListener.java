package org.main.userservice;

import org.main.model.EnergyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EnergyMessageListener {

    @RabbitListener(queues = "energy.queue")
    public void receive(EnergyMessage msg) {
        System.out.println("Consumer received: " + msg.getType() + " - " + msg.getKwh() + " kWh");
        // DB-Verarbeitung kommt später hier rein
    }
}
