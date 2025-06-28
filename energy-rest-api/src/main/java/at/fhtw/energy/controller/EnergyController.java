package at.fhtw.energy.controller;

import at.fhtw.energy.dto.CurrentEnergyResponse;
import at.fhtw.energy.dto.HistoricalEntry;
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
    public HistoricalEntry getHistorical(
            @RequestParam String start,
            @RequestParam String end) {
        HistoricalEntry entry = energyService.getLatestHistoricalEntry(start, end);
        if (entry == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No historical entry found for given range");
        }
        return entry;
    }
}
