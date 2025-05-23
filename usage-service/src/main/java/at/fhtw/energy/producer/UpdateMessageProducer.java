package at.fhtw.energy.producer;

import at.fhtw.energy.config.MQConfig;
import at.fhtw.energy.model.EnergyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UpdateMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public UpdateMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUpdateTrigger() {
        String message = "UPDATE_READY";
        rabbitTemplate.convertAndSend(MQConfig.UPDATE_QUEUE, message);
        System.out.println("UpdateMessageProducer hat 'UPDATE_READY' gesendet.");
    }
    //TODO Umschreiben, dass eine update Message geschickt wird wenn: producer/user Usage Service benutzen
    @Scheduled(fixedDelay = 5000)
    public void sendMessage() {
        sendUpdateTrigger();
    }
}