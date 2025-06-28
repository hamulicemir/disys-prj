package at.fhtw.energy.service;

import at.fhtw.energy.model.UsageHour;

public class PercentageCalculator {
    public static double calculateCommunityDepleted(UsageHour usageHour) {
        return usageHour.getCommunityUsed();
    }

    public static double calculateGridPortion(UsageHour usageHour) {
        return usageHour.getGridUsed();
    }
}