package ru.staymix.currency.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import ru.staymix.currency.config.CurrencyClientCfg;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CbrCurrencyRateClient implements HttpCurrencyDateRateClient {

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private final CurrencyClientCfg clientCfg;

    @Override
    public String requestByDate(LocalDate date) {
        String baseUrl = clientCfg.getUrl();
        HttpClient client = HttpClient.newHttpClient();
        String url = buildUriRequest(baseUrl, date);
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUriRequest(String baseUrl, LocalDate date) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl).queryParam("date_req", DATE_TIME_FORMATTER.format(date))
                .build().toString();
    }
}
