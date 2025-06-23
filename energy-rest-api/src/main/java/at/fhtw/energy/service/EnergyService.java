package at.fhtw.energy.service;

            import at.fhtw.energy.dto.HistoricalEntry;
            import at.fhtw.energy.dto.CurrentEnergyResponse;
            import at.fhtw.energy.entity.HistoricalEntryEntity;
            import at.fhtw.energy.repository.HistoricalEntryRepository;
            import org.springframework.stereotype.Service;

            import java.time.LocalDateTime;
            import java.util.List;
            import java.util.stream.Collectors;

            @Service
            public class EnergyService {

                private final HistoricalEntryRepository repository;

                public EnergyService(HistoricalEntryRepository repository) {
                    this.repository = repository;
                }

                public CurrentEnergyResponse getCurrentPercentage() {
                    // Beispiel: Letzter Eintrag der aktuellen Stunde
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime start = now.withMinute(0).withSecond(0).withNano(0);
                    LocalDateTime end = start.plusHours(1);
                    List<HistoricalEntryEntity> entries = repository.findByTimestampBetween(start, end);
                    if (entries.isEmpty()) {
                        return new CurrentEnergyResponse(0, 0);
                    }
                    HistoricalEntryEntity last = entries.get(entries.size() - 1);
                    return new CurrentEnergyResponse(last.getCommunityUsed(), last.getGridUsed());
                }

                public List<HistoricalEntry> getHistoricalData(LocalDateTime start, LocalDateTime end) {
                    return repository.findByTimestampBetween(start, end)
                            .stream()
                            .map(e -> {
                                HistoricalEntry dto = new HistoricalEntry();
                                dto.setTimestamp(e.getTimestamp());
                                dto.setCommunityProduced(e.getCommunityProduced());
                                dto.setCommunityUsed(e.getCommunityUsed());
                                dto.setGridUsed(e.getGridUsed());
                                return dto;
                            })
                            .collect(Collectors.toList());
                }
            }