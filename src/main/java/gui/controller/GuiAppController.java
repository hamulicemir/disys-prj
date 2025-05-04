package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class GuiAppController {
    @FXML
    private TextArea outputArea;

    @FXML
    public void loadCurrentData() {
        outputArea.setText("Aktuelle Prozentdaten werden geladen...");
        // Hier später REST-Call einbauen
    }

    @FXML
    public void loadHistoricalData() {
        outputArea.setText("Historische Daten werden geladen...");
        // Hier später REST-Call einbauen
    }
}
