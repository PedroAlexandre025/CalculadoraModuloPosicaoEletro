import javax.swing.*;
import java.awt.*;

class CalculadoraEletrostatica extends JFrame {

    private JTextField campoQ1, campoQ2, campoQ3, campoA;
    private JTextArea areaResultado;

    public CalculadoraEletrostatica() {
        // Configurações da Janela
        setTitle("Simulador de Campo Elétrico - Ponto P");
        setSize(480, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // Painel de Entradas (Grid para organizar os parâmetros)
        JPanel painelEntradas = new JPanel(new GridLayout(5, 2, 8, 8));
        painelEntradas.setBorder(BorderFactory.createTitledBorder("Parâmetros Físicos do Sistema"));

        painelEntradas.add(new JLabel(" Carga q1 (múltiplo de e):"));
        campoQ1 = new JTextField("1");
        painelEntradas.add(campoQ1);

        painelEntradas.add(new JLabel(" Carga q2 (múltiplo de e):"));
        campoQ2 = new JTextField("1");
        painelEntradas.add(campoQ2);

        painelEntradas.add(new JLabel(" Carga q3 (múltiplo de e):"));
        campoQ3 = new JTextField("2");
        painelEntradas.add(campoQ3);

        painelEntradas.add(new JLabel(" Distância 'a' (em μm):"));
        campoA = new JTextField("6.00");
        painelEntradas.add(campoA);

        JButton botaoCalcular = new JButton("Calcular Vetor Resultante");
        JButton botaoReset = new JButton("Resetar");

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 5, 5));
        painelBotoes.add(botaoCalcular);
        painelBotoes.add(botaoReset);

        painelEntradas.add(new JLabel(""));
        painelEntradas.add(painelBotoes);

        add(painelEntradas, BorderLayout.NORTH);

        // Área de Resultado
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaResultado.setBorder(BorderFactory.createTitledBorder("Análise Vetorial no Ponto P"));
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);


        botaoCalcular.addActionListener(e -> calcularCampoVetorial());
        botaoReset.addActionListener(e -> resetarCampos());
    }

    private void calcularCampoVetorial() {
        try {
            // Coleta de dados
            double q1 = Double.parseDouble(campoQ1.getText());
            double q2 = Double.parseDouble(campoQ2.getText());
            double q3 = Double.parseDouble(campoQ3.getText());
            double aMicro = Double.parseDouble(campoA.getText());

            // 1ª RESTRIÇÃO DE DOMÍNIO: Geometria
            // A distância 'a' define os catetos do triângulo. Não existe triângulo com lado <= 0.
            if (aMicro <= 0) {
                areaResultado.setText("Erro de Geometria:\nA distância 'a' deve ser estritamente maior que zero.");
                return;
            }

            // Constantes Físicas
            double k = 8.99e9; // Constante eletrostática (N*m^2/C^2)
            double eCarga = 1.60e-19; // Carga elementar (C)
            double aMetros = aMicro * 1e-6; // Conversão para metros

            // Fator base do campo gerado por 1 carga elementar a uma distância d = a*sqrt(2)/2
            // E = k * q / d^2  -> d^2 = a^2 / 2
            double E0 = (2 * k * eCarga) / Math.pow(aMetros, 2);

            // 2ª RESTRIÇÃO DA NATUREZA DO PROBLEMA: Decomposição Vetorial
            // considerando a posição exata de cada carga em relação ao ponto P (a/2, a/2).
            double raiz2sobre2 = Math.sqrt(2) / 2.0;

            // Somatório das componentes X e Y
            double Ex = E0 * raiz2sobre2 * (q1 - q2 + q3);
            double Ey = E0 * raiz2sobre2 * (-q1 + q2 + q3);

            // Módulo e Direção do Vetor Resultante
            double moduloResultante = Math.sqrt(Ex * Ex + Ey * Ey);
            double anguloRadianos = Math.atan2(Ey, Ex);
            double anguloGraus = Math.toDegrees(anguloRadianos);

            // Normaliza o ângulo para o ciclo de 0° a 360°
            if (anguloGraus < 0) {
                anguloGraus += 360;
            }

            // Impressão do Relatório Físico
            StringBuilder relatorio = new StringBuilder();
            relatorio.append(String.format("Componente X (Ex): %.2f N/C\n", Ex));
            relatorio.append(String.format("Componente Y (Ey): %.2f N/C\n", Ey));
            relatorio.append("--------------------------------------\n");
            relatorio.append(String.format("MÓDULO RESULTANTE: %.2f N/C\n", moduloResultante));
            relatorio.append(String.format("DIREÇÃO (Ângulo) : %.2f° em relação ao eixo x+\n\n", anguloGraus));

            // 3ª RESTRIÇÃO LÓGICA: Identificação de Simetria
            if (q1 == q2) {
                relatorio.append("Observação Física:\n");
                relatorio.append("Como q1 = q2, os campos vetoriais gerados por\n");
                relatorio.append("elas se anulam perfeitamente na posição do ponto P.\n");
                relatorio.append("O campo resultante medido é causado apenas por q3.");
            }

            areaResultado.setText(relatorio.toString());

        } catch (NumberFormatException ex) {
            // Restrição de Tipo: Impede que letras ou caracteres quebrem a conta
            areaResultado.setText("Erro de Entrada:\nPor favor, insira apenas valores numéricos válidos.\n(Utilize ponto ao invés de vírgula para casas decimais).");
        }
    }

    private void resetarCampos() {
        campoQ1.setText("1");
        campoQ2.setText("1");
        campoQ3.setText("2");
        campoA.setText("6.00");

        areaResultado.setText(
                "Simulador reiniciado.\n" +
                        "Parâmetros restaurados para os valores padrão."
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new CalculadoraEletrostatica().setVisible(true);
        });
    }
}