package at.fhtw.energy.model;

    import java.time.LocalDateTime;

    public class UsageHour {
        private LocalDateTime hour;
        private double communityProduced;
        private double communityUsed;
        private double gridUsed;

        public LocalDateTime getHour() { return hour; }
        public void setHour(LocalDateTime hour) { this.hour = hour; }
        public double getCommunityProduced() { return communityProduced; }
        public void setCommunityProduced(double communityProduced) { this.communityProduced = communityProduced; }
        public double getCommunityUsed() { return communityUsed; }
        public void setCommunityUsed(double communityUsed) { this.communityUsed = communityUsed; }
        public double getGridUsed() { return gridUsed; }
        public void setGridUsed(double gridUsed) { this.gridUsed = gridUsed; }
    }