

package at.fhtw.energy.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "current_percentage")
public class CurrentPercentageEntity {
    @Id
    @Column(name = "hour")
    private LocalDateTime hour;
    @Column(name = "community_depleted")
    private double communityUsed;
    @Column(name = "grid_portion")
    private double gridUsed;


    public double getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }
}