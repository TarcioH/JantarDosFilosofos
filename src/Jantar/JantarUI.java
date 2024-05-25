package Jantar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JantarUI extends JFrame {

    private static final int NUM_FILOSOFOS = 5;
    private Filosofo[] filosofos;
    private JLabel[] statusLabels;
    private Estados estados;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JantarUI jantarUI = new JantarUI();
            jantarUI.setVisible(true);
        });
    }
      public void atualizarStatusFilosofo(int i, String novoStatus) {
        SwingUtilities.invokeLater(() -> {
            statusLabels[i].setText("Filósofo " + i + ": " + novoStatus);
        });
    }

    public JantarUI() {
        super("Jantar dos Filósofos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        filosofos = new Filosofo[NUM_FILOSOFOS];
        statusLabels = new JLabel[NUM_FILOSOFOS];
        estados = new Estados();

        setLayout(new GridLayout(NUM_FILOSOFOS, 1));

        for (int i = 0; i < NUM_FILOSOFOS; i++) {
            filosofos[i] = new Filosofo(i, estados);
            statusLabels[i] = new JLabel("Filósofo " + i + ": Pensando");
            add(statusLabels[i]);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Filosofo filosofo : filosofos) {
                    filosofo.parar();
                }
                gerarRelatorio();
                System.exit(0);
            }
        });

        setSize(300, 200);
        setLocationRelativeTo(null);

        iniciarSimulacao();
    }

    private void iniciarSimulacao() {
        for (Filosofo filosofo : filosofos) {
            filosofo.start();
        }

        new Thread(() -> {
            try {
                Thread.sleep(180000); // Simulação rodará por 180 segundos
                SwingUtilities.invokeLater(this::gerarRelatorio);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

private void gerarRelatorio() {
    StringBuilder relatorio = new StringBuilder("Relatório Final:\n");

    for (int i = 0; i < NUM_FILOSOFOS; i++) {
        relatorio.append("Filósofo ").append(i).append(":\n");
        relatorio.append("  Vezes que Comeu: ").append(estados.getContador(i)).append("\n");
        relatorio.append("  Tempo Máximo sem Comer: ").append(estados.getTempoMaximoSemComer(i)).append("\n");
        int contador = estados.getContador(i);
        int mediaEspera = (contador == 0) ? 0 : estados.getMediaDeEspera(i) / contador;
        relatorio.append("  Média de Espera: ").append(mediaEspera).append("\n\n");
        statusLabels[i].setText("Filósofo " + i + ": Média de Espera: " + mediaEspera);
    }

    JTextArea textArea = new JTextArea(relatorio.toString());
    textArea.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(textArea);

    JOptionPane.showMessageDialog(this, scrollPane, "Relatório Final", JOptionPane.INFORMATION_MESSAGE);
}


}
