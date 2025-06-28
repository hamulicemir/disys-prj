package at.fhtw.energy.service;

import at.fhtw.energy.model.CurrentPercentageResponse;
import at.fhtw.energy.model.HistoricalSummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnergyApiService {

    private static final String BASE_URL = "http://localhost:8081/energy";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public CurrentPercentageResponse getCurrentData() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/current"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), CurrentPercentageResponse.class);
    }

    public HistoricalSummaryResponse getHistoricalData(LocalDateTime start, LocalDateTime end) throws Exception {
        String format = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(start);
        String formatEnd = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(end);

        String url = BASE_URL + "/historical?start=" + format + "&end=" + formatEnd;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), HistoricalSummaryResponse.class);
    }
}