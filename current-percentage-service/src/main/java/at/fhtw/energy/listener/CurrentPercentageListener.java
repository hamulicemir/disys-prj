package at.fhtw.energy.listener;

import at.fhtw.energy.model.UsageHour;
import at.fhtw.energy.entity.CurrentPercentage;
import at.fhtw.energy.repository.CurrentPercentageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrentPercentageListener {

    private CurrentPercentageRepository repository;

    public CurrentPercentageListener(CurrentPercentageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @RabbitListener(queues = "update.queue")
    public void receive(UsageHour usageHour) {
        double percentage = 0;
        if (usageHour.getCommunityUsed() > 0) {
            percentage = (usageHour.getCommunityProduced() / usageHour.getCommunityUsed()) * 100;
        }
        CurrentPercentage cp = new CurrentPercentage();
        cp.setHour(usageHour.getHour());

        repository.save(cp);

    }
}
