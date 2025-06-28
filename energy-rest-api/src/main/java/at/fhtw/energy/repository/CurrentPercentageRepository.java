package at.fhtw.energy.repository;

import at.fhtw.energy.entity.CurrentPercentageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentageEntity, Long> {
    CurrentPercentageEntity findTopByOrderByHourDesc();
}