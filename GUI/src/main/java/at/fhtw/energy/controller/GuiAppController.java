package at.fhtw.energy.controller;

import at.fhtw.energy.model.CurrentPercentageResponse;
import at.fhtw.energy.model.HistoricalEntry;
import at.fhtw.energy.model.HistoricalSummaryResponse;
import at.fhtw.energy.service.EnergyApiService;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.List;

public class GuiAppController {

    @FXML private Label communityPoolLabel;
    @FXML private Label gridPortionLabel;

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private Label communityProducedLabel;
    @FXML private Label communityUsedLabel;
    @FXML private Label gridUsedLabel;

    private final EnergyApiService apiService = new EnergyApiService();

    @FXML
    public void handleRefresh() {
        try {
            CurrentPercentageResponse res = apiService.getCurrentData();
            communityPoolLabel.setText(String.format("%.2f%% used", res.getCommunityDepleted()));
            gridPortionLabel.setText(String.format("%.2f%%", res.getGridPortion()));
        } catch (Exception e) {
            showError("Fehler beim Laden der aktuellen Daten: " + e.getMessage());
        }
    }

    @FXML
    public void handleShowData() {
        try {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();

            LocalDateTime start = LocalDateTime.of(startDate, LocalTime.MIN);
            LocalDateTime end = LocalDateTime.of(endDate, LocalTime.MAX);

            List<HistoricalEntry> entries = apiService.getHistoricalData(start, end);

            double totalProduced = entries.stream().mapToDouble(HistoricalEntry::getCommunityProduced).sum();
            double totalUsed = entries.stream().mapToDouble(HistoricalEntry::getCommunityUsed).sum();
            double totalGrid = entries.stream().mapToDouble(HistoricalEntry::getGridUsed).sum();

            communityProducedLabel.setText(String.format("%.3f kWh", totalProduced));
            communityUsedLabel.setText(String.format("%.3f kWh", totalUsed));
            gridUsedLabel.setText(String.format("%.2f kWh", totalGrid));

        } catch (Exception e) {
            e.printStackTrace();
            showError("Invalid date/time or REST error: " + e.getMessage());
        }
    }


    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
