import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import com.google.gson.Gson;

public class TaxasService {

    private String apiKey;
    Properties properties = new Properties();

    //Construtor que Carrega API_KEY
    TaxasService(){
        try(FileInputStream input = new FileInputStream("config.properties")){
            properties.load(input);
            apiKey = properties.getProperty("API_KEY");
            if(apiKey == null || apiKey.isEmpty()){
                throw new RuntimeException("API_KEY não encontrada no arquivo config.properties");
            }
        }catch (IOException e){
            throw new RuntimeException();
        }
    }

    public Taxas conversao(String busca){

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
                    .fromJson(response.body(), Taxas.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não consegui buscar as taxas de câmbio convertidas.");
        }

    }

    // Método para realizar a conversão de moedas
    public double converter(String base, String target, double valor, Taxas rates) {
        if (!rates.getConversion_rates().containsKey(target)) {
            throw new IllegalArgumentException("Moeda de conversão não disponível.");
        }
        double taxaConversao = rates.getConversion_rates().get(target); // Taxa de conversão
        return valor * taxaConversao; // Retorna o valor convertido
    }
}
