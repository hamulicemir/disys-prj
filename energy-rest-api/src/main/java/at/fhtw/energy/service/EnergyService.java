package at.fhtw.energy.service;

import at.fhtw.energy.dto.HistoricalEntry;
import at.fhtw.energy.dto.CurrentEnergyResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnergyService {

    private final ObjectMapper mapper;

    public EnergyService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    public CurrentEnergyResponse getCurrentPercentage() {
        return new CurrentEnergyResponse(78.54, 7.23); // Platzhalter, alternativ: aus JSON laden
    }

    public List<HistoricalEntry> getHistoricalData(LocalDateTime start, LocalDateTime end) {
        System.out.println("Lade JSON aus Pfad: data/historical.json");
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("data/historical.json");
            if (is == null) {
                throw new RuntimeException("Datei data/historical.json nicht gefunden!");
            }
            List<HistoricalEntry> allEntries = mapper.readValue(is, new TypeReference<>() {});

            return allEntries.stream()
                    .filter(entry -> entry.getTimestamp() != null &&
                            !entry.getTimestamp().isBefore(start) &&
                            !entry.getTimestamp().isAfter(end))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fehler beim Lesen von historical.json");
        }
    }
}


