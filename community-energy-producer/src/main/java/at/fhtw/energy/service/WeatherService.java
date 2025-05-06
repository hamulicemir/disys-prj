package at.fhtw.energy.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_KEY = "588f27bd971b41d6b7e194810250405";
    private static final String URL = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=Vienna&aqi=no";

    public double fetchProductionKwh() {
        try {
            String response = restTemplate.getForObject(URL, String.class);
            JSONObject json = new JSONObject(response);

            boolean isDay = json.getJSONObject("current").getInt("is_day") == 1;
            String condition = json.getJSONObject("current").getJSONObject("condition").getString("text");
            int cloud = json.getJSONObject("current").getInt("cloud");

            return calculateProduction(isDay, condition, cloud);

        } catch (Exception e) {
            System.err.println("⚠️ Fehler beim Wetterabruf: " + e.getMessage());
            return 0.001; // fallback Wert
        }
    }

    private double calculateProduction(boolean isDay, String condition, int cloudPercentage) {
        if (!isDay) {
            return 0.0;
        }

        double base = 0.002;

        if (condition.toLowerCase().contains("Clear")) {
            return base * 1.8;
        } else if (condition.toLowerCase().contains("Partly cloudy")) {
            return base * 1.2;
        } else if (condition.toLowerCase().contains("Cloudy")) {
            return base * 0.7;
        } else if (condition.toLowerCase().contains("Rainy") || cloudPercentage > 90) {
            return base * 0.3;
        } else {
            return base;
        }
    }
}
