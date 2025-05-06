package at.fhtw.energy.service;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import  at.fhtw.energy.dto.HistoricalEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnergyService {

    public CurrentEnergyResponse getCurrentPercentage() {
        return new CurrentEnergyResponse(62.7); // Dummy-Prozentwert
    }

    public List<HistoricalEntry> getHistoricalData(LocalDate start, LocalDate end) {
        List<HistoricalEntry> list = new ArrayList<>();
        LocalDate date = start;
        while (!date.isAfter(end)) {
            list.add(new HistoricalEntry(date, Math.random() * 5, Math.random() * 4));
            date = date.plusDays(1);
        }
        return list;
    }
}
