package at.fhtw.energy.repository;

import at.fhtw.energy.entity.HistoricalEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricalEntryRepository extends JpaRepository<HistoricalEntryEntity, Long> {
    List<HistoricalEntryEntity> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}