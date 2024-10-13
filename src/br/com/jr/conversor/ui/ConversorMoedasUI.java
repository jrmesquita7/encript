package br.com.jr.conversor.ui;

import javax.swing.*;
import java.awt.*;

public class ConversorMoedasUI extends JFrame {

    private ConversorPainel conversorPainel;

    public ConversorMoedasUI() {
        // Configuração inicial do frame
        setTitle("Conversor de Moedas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializando o painel de conversão
        conversorPainel = new ConversorPainel();

        // Adicionando o painel ao frame
        add(conversorPainel.getPainelPrincipal(), BorderLayout.CENTER);

        setVisible(true); // Exibe a interface gráfica
    }

    public static void main(String[] args) {
        new ConversorMoedasUI();
    }
}
