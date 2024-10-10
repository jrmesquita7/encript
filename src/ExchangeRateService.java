import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class ExchangeRateService {

    public ExchangeRate conversao(String busca){
        var apiKey = "a1d6e8f86b544afe4508214c";

        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey +"/latest/" + busca);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            // Enviar requisição e obter resposta
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Converter JSON para o objeto ExchangeRate
            return new Gson()
                    .fromJson(response.body(), ExchangeRate.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não consegui buscar as taxas de câmbio convertidas.");
        }

    }
}
