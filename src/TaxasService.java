import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import com.google.gson.Gson;

public class TaxasService {

    private String apiKey;
    private Properties properties = new Properties();
    private List<String> historico = new ArrayList<>();
    private Log log = new Log(); // Mover Log para ser um atributo

    // Construtor que carrega API_KEY
    public TaxasService() {
        carregarApiKey();
    }

    private void carregarApiKey() {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            apiKey = properties.getProperty("API_KEY");
            if (apiKey == null || apiKey.isEmpty()) {
                throw new RuntimeException("API_KEY não encontrada no arquivo config.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para buscar as taxas de câmbio
    public Taxas conversao(String busca) {
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + busca);
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse
                            .BodyHandlers
                            .ofString());
            return new Gson()
                    .fromJson(response
                            .body(), Taxas.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não consegui buscar as taxas de câmbio convertidas.", e);
        }
    }

    // Método para realizar a conversão de moedas
    public double converter(String base, String target, double valor, Taxas rates) {
        double taxaConversao = 0;

        try{
            validarMoeda(target, rates);
        }catch (IllegalArgumentException | InputMismatchException e){
            System.out.println("Moeda não disponivel no momento ou valor inválido digitado " + e);
        }

        try {
            taxaConversao = rates.getConversion_rates().get(target);
            String registro = criarRegistroConversao(valor, base, target, taxaConversao);
            historico.add(registro);
            registrarLog(registro);

        }catch (NullPointerException e){
            System.out.println("Houve um erro ao digitar o valor");
        }

        return valor * taxaConversao;
    }

    private void validarMoeda(String target, Taxas rates) {

        if (rates == null || rates.getConversion_rates() == null) {
            throw new IllegalArgumentException("Moeda de conversão não disponível.");
        }
    }

    private String criarRegistroConversao(double valor, String base, String target, double taxaConversao) {
        return String.format("Valor: %.2f em %s convertido para %.2f em %s", valor, base, valor * taxaConversao, target);
    }

    private void registrarLog(String registro) {
        try {
            log.gerarLog(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para ver histórico
    public String historico() {
        StringBuilder sb = new StringBuilder();
        for (String registro : historico) {
            sb.append(registro).append("\n");
        }
        String logGerado = "Histórico gerado";
        registrarLog(logGerado);
        return sb.toString();
    }
}
