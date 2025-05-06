package at.fhtw.energy.dto;
import java.time.LocalDate;

public class HistoricalEntry {
    private LocalDate date;
    private double production;
    private double usage;

    public HistoricalEntry(LocalDate date, double production, double usage) {
        this.date = date;
        this.production = production;
        this.usage = usage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }
}

