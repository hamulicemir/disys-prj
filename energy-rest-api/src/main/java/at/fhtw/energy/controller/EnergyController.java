package at.fhtw.energy.controller;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalEntry;
import at.fhtw.energy.dto.HistoricalSummaryResponse;
import at.fhtw.energy.service.EnergyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public HistoricalSummaryResponse getHistoricalSummary() {
        return energyService.getHistoricalEntriesSummary();
    }


}
