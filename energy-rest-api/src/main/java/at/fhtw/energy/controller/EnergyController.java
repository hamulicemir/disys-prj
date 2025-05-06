package at.fhtw.energy.controller;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalEntry;
import at.fhtw.energy.service.EnergyService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyController {


private final EnergyService energyService;

public EnergyController(EnergyService energyService) {
    this.energyService = energyService;
}

@GetMapping("/current")
public CurrentEnergyResponse getCurrent() {
    return energyService.getCurrentPercentage();
}

@GetMapping("/historical")
public List<HistoricalEntry> getHistorical(
        @RequestParam String start,
        @RequestParam String end) {

    LocalDateTime startDate = LocalDateTime.parse(start);
    LocalDateTime endDate = LocalDateTime.parse(end);
    return energyService.getHistoricalData(startDate, endDate);
}
}