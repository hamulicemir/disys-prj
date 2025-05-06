package at.fhtw.energy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentEnergyResponse {

    private double communityDepleted;
    private double gridPortion;

    public CurrentEnergyResponse() {
        // WICHTIG: für Jackson!
    }

    public CurrentEnergyResponse(double communityDepleted, double gridPortion) {
        this.communityDepleted = communityDepleted;
        this.gridPortion = gridPortion;
    }

    public double getCommunityDepleted() {
        return communityDepleted;
    }

    public void setCommunityDepleted(double communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}
