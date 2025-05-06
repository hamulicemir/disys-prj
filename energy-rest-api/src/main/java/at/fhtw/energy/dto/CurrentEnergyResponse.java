package at.fhtw.energy.dto;

public class CurrentEnergyResponse {
    private double percentage;

    public CurrentEnergyResponse(double percentage) {
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
