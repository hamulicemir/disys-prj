package at.fhtw.energy.service;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalSummaryResponse;
import at.fhtw.energy.entity.CurrentPercentageEntity;
import at.fhtw.energy.entity.HistoricalEntryEntity;
import at.fhtw.energy.repository.CurrentPercentageRepository;
import at.fhtw.energy.repository.HistoricalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnergyService {

    private final CurrentPercentageRepository currentPercentageRepository;
    private final HistoricalEntryRepository historicalEntryRepository;

    public EnergyService(CurrentPercentageRepository currentPercentageRepository, HistoricalEntryRepository historicalEntryRepository) {
        this.currentPercentageRepository = currentPercentageRepository;
        this.historicalEntryRepository = historicalEntryRepository;
    }

    public CurrentEnergyResponse getCurrentPercentage() {
        CurrentPercentageEntity last = currentPercentageRepository.findTopByOrderByHourDesc();
        if (last == null) {
            return new CurrentEnergyResponse(0, 0);
        }
        return new CurrentEnergyResponse(last.getCommunityUsed(), last.getGridUsed());
    }


  public HistoricalSummaryResponse getHistoricalEntriesSummary(LocalDateTime start, LocalDateTime end) {
      List<HistoricalEntryEntity> entries = historicalEntryRepository.findByHourBetween(start, end);
      double sumProduced = 0;
      double sumUsed = 0;
      double sumGrid = 0;

      for (HistoricalEntryEntity entry : entries) {
          sumProduced += entry.getCommunityProduced();
          sumUsed += entry.getCommunityUsed();
          sumGrid += entry.getGridUsed();
      }

      return new HistoricalSummaryResponse(sumProduced, sumUsed, sumGrid);
  }

}