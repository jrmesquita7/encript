import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    private static final String LOGS_DIR = "logs/";

    public void gerarLog(String mensagem) throws IOException {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String dataFormatada = data.format(formatter);
        String nomeArquivo = LOGS_DIR + dataFormatada.split(" ")[0] + "-logs.txt";

        try (FileWriter file = new FileWriter(nomeArquivo, true)) {
            file.write(mensagem + " " + dataFormatada + "\n");
        }
    }
}
