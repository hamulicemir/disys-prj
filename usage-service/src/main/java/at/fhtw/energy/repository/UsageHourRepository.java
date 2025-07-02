
package at.fhtw.energy.repository;

import at.fhtw.energy.model.UsageHour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface UsageHourRepository extends JpaRepository<UsageHour, LocalDateTime> {
}