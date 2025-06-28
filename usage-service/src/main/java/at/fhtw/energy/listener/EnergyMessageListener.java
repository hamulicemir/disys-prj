// usage-service/src/main/java/at/fhtw/energy/listener/EnergyMessageListener.java
    package at.fhtw.energy.listener;

    import at.fhtw.energy.model.EnergyMessage;
    import at.fhtw.energy.model.UsageHour;
    import at.fhtw.energy.repository.UsageHourRepository;
    import at.fhtw.energy.producer.UpdateMessageProducer;
    import org.springframework.amqp.rabbit.annotation.RabbitListener;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.time.LocalDateTime;

    @Service
    public class EnergyMessageListener {

        private final UsageHourRepository usageHourRepository;
        private final UpdateMessageProducer updateMessageProducer;

        public EnergyMessageListener(UsageHourRepository usageHourRepository, UpdateMessageProducer updateMessageProducer) {
            this.usageHourRepository = usageHourRepository;
            this.updateMessageProducer = updateMessageProducer;
        }

        @Transactional
        @RabbitListener(queues = "energy.queue")
        public void receive(EnergyMessage msg) {
            LocalDateTime hour = msg.getDatetime().withMinute(0).withSecond(0).withNano(0);
            UsageHour usageHour = usageHourRepository.findById(hour).orElseGet(() -> {
                UsageHour uh = new UsageHour();
                uh.setHour(hour);
                return uh;
            });

            if ("PRODUCER".equals(msg.getType())) {
                usageHour.setCommunityProduced(usageHour.getCommunityProduced() + msg.getKwh());
            } else if ("USER".equals(msg.getType())) {
                usageHour.setCommunityUsed(usageHour.getCommunityUsed() + msg.getKwh());
                double diff = usageHour.getCommunityUsed() - usageHour.getCommunityProduced();
                if (diff > 0) {
                    usageHour.setGridUsed(usageHour.getGridUsed() + diff);
                    usageHour.setCommunityProduced(usageHour.getCommunityProduced() + diff);
                }
            }
            usageHourRepository.save(usageHour);

            // Update-Trigger senden, wenn Producer/User Usage Service benutzt haben
            updateMessageProducer.sendUpdateTrigger(usageHour);
        }
    }