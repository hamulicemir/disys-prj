package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GuiAppController {

    @FXML private Label communityPoolLabel;
    @FXML private Label gridPortionLabel;

    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;

    @FXML private Label communityProducedLabel;
    @FXML private Label communityUsedLabel;
    @FXML private Label gridUsedLabel;

    @FXML
    public void handleRefresh() {
        // TODO: REST GET /energy/current aufrufen
        communityPoolLabel.setText("78.54% used");  // Platzhalter
        gridPortionLabel.setText("7.23%");         // Platzhalter
    }
    @FXML
    public void handleShowData() {
        try {
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            LocalTime startTime = LocalTime.parse(startTimeField.getText());
            LocalTime endTime = LocalTime.parse(endTimeField.getText());

            LocalDateTime start = LocalDateTime.of(startDate, startTime);
            LocalDateTime end = LocalDateTime.of(endDate, endTime);

            // TODO: REST GET /energy/historical?start=...&end=... verwenden
            communityProducedLabel.setText("143.024 kWh");
            communityUsedLabel.setText("130.101 kWh");
            gridUsedLabel.setText("14.75 kWh");
        } catch (Exception e) {
            showError("Invalid date or time format.");
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
