package br.com.jr.conversor.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.com.jr.conversor.service.Taxas;
import br.com.jr.conversor.service.TaxasService;
import br.com.jr.conversor.util.Log;

public class ConversorMoedasUI extends JFrame {

    private JComboBox<String> comboBoxMoedaOrigem;
    private JComboBox<String> comboBoxMoedaDestino;
    private JTextField textFieldValor;
    private JLabel labelResultado;
    private TaxasService taxasService;
    Log log = new Log();

    public ConversorMoedasUI() {
        // Configuração inicial do frame
        setTitle("Conversor de Moedas");
        setSize(400, 300);  // Aumentei um pouco a altura para acomodar melhor os componentes
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializando o serviço de taxas de câmbio
        taxasService = new TaxasService();
        Taxas taxas = taxasService.conversao("USD");  // Você pode alterar a moeda base conforme necessário

        // Obtendo as chaves do mapa de taxas
        String[] moedas = taxas.getConversion_rates().keySet().toArray(new String[0]);

        // Componentes da interface gráfica
        comboBoxMoedaOrigem = new JComboBox<>(moedas);
        comboBoxMoedaDestino = new JComboBox<>(moedas);
        textFieldValor = new JTextField(10);  // 10 é o tamanho preferencial da caixa de texto
        labelResultado = new JLabel("Resultado: ");

        JButton buttonConverter = new JButton("Converter");

        // Usando BorderLayout para a janela principal
        setLayout(new BorderLayout());

        // Painel principal para conter todos os componentes
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Margem ao redor do painel principal

        // Adicionando componentes com espaçamento entre eles
        JPanel painelMoedaOrigem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelMoedaOrigem.add(new JLabel("Moeda de Origem:"));
        painelMoedaOrigem.add(comboBoxMoedaOrigem);
        painelPrincipal.add(painelMoedaOrigem);

        JPanel painelMoedaDestino = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelMoedaDestino.add(new JLabel("Moeda de Destino:"));
        painelMoedaDestino.add(comboBoxMoedaDestino);
        painelPrincipal.add(painelMoedaDestino);

        JPanel painelValor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelValor.add(new JLabel("Valor a ser convertido:"));
        painelValor.add(textFieldValor);
        painelPrincipal.add(painelValor);

        // Painel para o botão de conversão
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(buttonConverter);
        painelPrincipal.add(painelBotao);

        // Painel para o resultado
        JPanel painelResultado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelResultado.add(labelResultado);
        painelPrincipal.add(painelResultado);

        // Adicionando o painel principal ao centro da janela
        add(painelPrincipal, BorderLayout.CENTER);

        // Ação do botão
        buttonConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String moedaOrigem = comboBoxMoedaOrigem.getSelectedItem().toString();
                    String moedaDestino = comboBoxMoedaDestino.getSelectedItem().toString();
                    double valor = Double.parseDouble(textFieldValor.getText());

                    Taxas taxasMenu = taxasService.conversao(moedaOrigem);
                    double valorConvertido = taxasService.converter(moedaOrigem, moedaDestino, valor, taxasMenu);
                    labelResultado.setText("Resultado: " + String.format("%.2f", valorConvertido) + " " + moedaDestino);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter: " + ex.getMessage());
                }
            }
        });

        setVisible(true); // Exibe a interface gráfica
    }

    public static void main(String[] args) {
        new ConversorMoedasUI();
    }
}
