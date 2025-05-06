package at.fhtw.energy.listener;

import at.fhtw.energy.model.EnergyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EnergyMessageListener {

    @RabbitListener(queues = "energy.queue")
    public void receive(EnergyMessage msg) {
        System.out.println("Consumer received: " + msg.getType() + " - " + msg.getKwh() + " kWh");
        // TODO : DB-Verarbeitung kommt später hier rein
    }
}
