package at.fhtw.energy.controller;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalSummaryResponse;
import at.fhtw.energy.service.EnergyService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

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
public HistoricalSummaryResponse getHistoricalSummary(
        @RequestParam String start,
        @RequestParam String end) {
    LocalDateTime startTime = LocalDateTime.parse(start);
    LocalDateTime endTime = LocalDateTime.parse(end);
    return energyService.getHistoricalEntriesSummary(startTime, endTime);
}

}
