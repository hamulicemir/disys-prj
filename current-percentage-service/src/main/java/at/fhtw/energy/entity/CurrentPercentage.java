package at.fhtw.energy.entity;

    import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

    import java.time.LocalDateTime;

    @Entity
    public class CurrentPercentage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private double percentage;
        private LocalDateTime hour;
        private double communityDepleted;
        private double gridPortion;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public LocalDateTime getHour() { return hour; }
        public void setHour(LocalDateTime hour) { this.hour = hour; }

        public double getCommunityDepleted() { return communityDepleted; }
        public void setCommunityDepleted(double communityDepleted) { this.communityDepleted = communityDepleted; }

        public double getGridPortion() { return gridPortion; }
        public void setGridPortion(double gridPortion) { this.gridPortion = gridPortion; }

        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }
        public double getPercentage() {
            return percentage;
        }
    }