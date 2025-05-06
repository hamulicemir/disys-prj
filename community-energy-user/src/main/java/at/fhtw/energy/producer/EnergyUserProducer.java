package at.fhtw.energy.producer;

import at.fhtw.energy.config.MQConfig;
import at.fhtw.energy.model.EnergyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Service
public class EnergyUserProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public EnergyUserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000) // alle 5 Sekunden
    public void sendUsage() {
        double base = 0.001;
        double multiplier = getTimeBasedMultiplier();
        double kwh = base * multiplier * (0.8 + random.nextDouble() * 0.4); // ±20%

        EnergyMessage msg = new EnergyMessage();
        msg.setType("USER");
        msg.setAssociation("COMMUNITY");
        msg.setKwh(kwh);
        msg.setDatetime(LocalDateTime.now());

        rabbitTemplate.convertAndSend(MQConfig.ENERGY_QUEUE, msg);
        System.out.printf("USER sent: %.5f kWh (multiplier: %.2f)%n", kwh, multiplier);
    }

    private double getTimeBasedMultiplier() {
        int hour = LocalTime.now().getHour();
        if (hour >= 6 && hour <= 9) return 2.5;     // morgens
        if (hour >= 17 && hour <= 22) return 2.0;   // abends
        if (hour >= 0 && hour <= 5) return 0.6;     // nachts
        return 1.0;                                 // tagsüber
    }
}
