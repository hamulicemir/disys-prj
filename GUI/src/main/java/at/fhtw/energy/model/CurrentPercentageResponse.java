package at.fhtw.energy.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentPercentageResponse {
    private double communityDepleted;
    private double gridPortion;

    public double getCommunityDepleted() { return communityDepleted; }
    public double getGridPortion() { return gridPortion; }

    public void setCommunityDepleted(double communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}