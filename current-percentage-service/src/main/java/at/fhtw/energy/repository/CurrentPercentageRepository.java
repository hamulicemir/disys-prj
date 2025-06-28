package at.fhtw.energy.repository;

import at.fhtw.energy.entity.CurrentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentage, Long> {
}