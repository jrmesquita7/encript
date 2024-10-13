package br.com.jr.conversor.ui;

import javax.swing.*;
import java.awt.*;
import br.com.jr.conversor.service.TaxasService;
import br.com.jr.conversor.service.Taxas;

public class ConversorPainel {

    private JComboBox<String> comboBoxMoedaOrigem;
    private JComboBox<String> comboBoxMoedaDestino;
    private JTextField textFieldValor;
    private JLabel labelResultado;
    private JButton buttonConverter;
    private JButton buttonHistorico;  // Botão de Histórico
    private JPanel painelPrincipal;
    private TaxasService taxasService;

    public ConversorPainel() {
        taxasService = new TaxasService();
        Taxas taxas = taxasService.conversao("USD");  // Moeda base

        // Configuração da interface
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Inicializando os componentes
        inicializarComponentes(taxas);

        // Adicionando os painéis à interface
        adicionarComponentes();

        // Configurar ações dos botões
        configurarAcoes();
    }

    private void inicializarComponentes(Taxas taxas) {
        String[] moedas = taxas.getConversion_rates().keySet().toArray(new String[0]);

        comboBoxMoedaOrigem = new JComboBox<>(moedas);
        comboBoxMoedaDestino = new JComboBox<>(moedas);
        textFieldValor = new JTextField(10);
        labelResultado = new JLabel("Resultado: ");
        buttonConverter = new JButton("Converter");
        buttonHistorico = new JButton("Histórico");  // Inicializar botão de Histórico
    }

    private void adicionarComponentes() {
        // Componentes organizados em painéis separados
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

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(buttonConverter);
        painelBotao.add(buttonHistorico);  // Adicionando botão de Histórico
        painelPrincipal.add(painelBotao);

        JPanel painelResultado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelResultado.add(labelResultado);
        painelPrincipal.add(painelResultado);
    }

    private void configurarAcoes() {
        buttonConverter.addActionListener(e -> {
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
        });

        // Ação do botão Histórico
        buttonHistorico.addActionListener(e -> {
            abrirJanelaHistorico();  // Método para abrir a nova janela de histórico
        });
    }

    private void abrirJanelaHistorico() {
        JFrame janelaHistorico = new JFrame("Histórico de Conversões");
        janelaHistorico.setSize(400, 300);
        janelaHistorico.setLocationRelativeTo(null);

        String historicoConversoes = taxasService.historico();
        JTextArea areaTextoHistorico = new JTextArea(historicoConversoes);
        areaTextoHistorico.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTextoHistorico);
        janelaHistorico.add(scrollPane);

        janelaHistorico.setVisible(true);
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }
}
