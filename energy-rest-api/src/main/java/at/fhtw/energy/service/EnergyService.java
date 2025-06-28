package at.fhtw.energy.service;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalEntry;
import at.fhtw.energy.dto.HistoricalSummaryResponse;
import at.fhtw.energy.entity.CurrentPercentageEntity;
import at.fhtw.energy.entity.HistoricalEntryEntity;
import at.fhtw.energy.repository.CurrentPercentageRepository;
import at.fhtw.energy.repository.HistoricalEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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


//    public HistoricalEntry getLatestHistoricalEntry(String start, String end) {
//        LocalDateTime startTime = LocalDateTime.parse(start);
//        LocalDateTime endTime = LocalDateTime.parse(end);
//        return historicalEntryRepository.findAll().stream()
//                .filter(e -> !e.getHour().isBefore(startTime) && !e.getHour().isAfter(endTime))
//                .max((e1, e2) -> e1.getHour().compareTo(e2.getHour()))
//                .map(entity -> {
//                    HistoricalEntry dto = new HistoricalEntry();
//                    dto.setTimestamp(entity.getHour());
//                    dto.setCommunityUsed(entity.getCommunityUsed());
//                    dto.setGridUsed(entity.getGridUsed());
//                    // dto.setCommunityProduced(entity.getCommunityProduced());
//                    return dto;
//                })
//                .orElse(null);
//    }


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