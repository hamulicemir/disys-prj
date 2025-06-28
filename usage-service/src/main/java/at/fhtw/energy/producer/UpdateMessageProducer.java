// usage-service/src/main/java/at/fhtw/energy/producer/UpdateMessageProducer.java
    package at.fhtw.energy.producer;

    import at.fhtw.energy.config.MQConfig;
    import at.fhtw.energy.model.UsageHour;
    import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
        // usage-service/src/main/java/at/fhtw/energy/producer/UpdateMessageProducer.java
        public void sendUpdateTrigger(UsageHour usageHour) {
            rabbitTemplate.convertAndSend(MQConfig.UPDATE_QUEUE, usageHour);
        }
    }