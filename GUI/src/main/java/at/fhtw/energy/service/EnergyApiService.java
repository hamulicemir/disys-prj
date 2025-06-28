package at.fhtw.energy.service;

import at.fhtw.energy.model.CurrentPercentageResponse;
import at.fhtw.energy.model.HistoricalEntry;
import at.fhtw.energy.model.HistoricalSummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EnergyApiService {

    private static final String BASE_URL = "http://localhost:8080/energy";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }


    public CurrentPercentageResponse getCurrentData() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/current"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Server returned status " + response.statusCode() + ": " + response.body());
        }

        return mapper.readValue(response.body(), CurrentPercentageResponse.class);
    }


    public List<HistoricalEntry> getHistoricalData(LocalDateTime start, LocalDateTime end) throws Exception {
        String startStr = start.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String endStr = end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        String url = String.format("%s/historical?start=%s&end=%s", BASE_URL, startStr, endStr);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), new com.fasterxml.jackson.core.type.TypeReference<List<HistoricalEntry>>() {});
    }
}
