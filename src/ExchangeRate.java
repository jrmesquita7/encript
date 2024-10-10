import java.util.Map;

public class ExchangeRate {
    private String base_code;
    private Map<String, Double> conversion_rates;

    // Getters e Setters
    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getConversion_rates() {
        return conversion_rates;
    }

    public void setConversion_rates(Map<String, Double> conversion_rates) {
        this.conversion_rates = conversion_rates;
    }

    // Método para exibir as taxas de câmbio filtradas
    public void exibirFiltradas(String base_code) {
        System.out.println("Taxas de câmbio filtradas para: " + base_code);
        System.out.println(base_code + " para ARS (Peso argentino): " + conversion_rates.get("ARS"));
        System.out.println(base_code + " para BOB (Boliviano boliviano): " + conversion_rates.get("BOB"));
        System.out.println(base_code + " para BRL (Real brasileiro): " + conversion_rates.get("BRL"));
        System.out.println(base_code + " para CLP (Peso chileno): " + conversion_rates.get("CLP"));
        System.out.println(base_code + " para COP (Peso colombiano): " + conversion_rates.get("COP"));
        System.out.println(base_code + " para USD (Dólar americano): " + conversion_rates.get("USD"));

    }
}
