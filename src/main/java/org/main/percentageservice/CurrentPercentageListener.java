package org.main.percentageservice;

import org.main.config.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CurrentPercentageListener {

    @RabbitListener(queues = MQConfig.UPDATE_QUEUE)
    public void receiveUpdateSignal(String msg) {
        System.out.println("PercentageService empfängt Trigger: " + msg);
        // TODO : Hier kommt deine Prozent-Berechnung + DB-Update
    }
}