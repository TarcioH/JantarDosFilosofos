/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jantar;

/**
 *
 * @author Win10
 */
import javax.swing.JPanel;
import java.awt.*;

public class Grade extends JPanel implements Runnable {

    // Cria padrões de comportamento dos filósofos
    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;
    private long tempoTotal = 0;
    private int[] vezesComeu = new int[5];
    private long[] tempoEspera = new long[5];
    private long[] tempoMaxEspera = new long[5];

    // Mensagem para cada um dos estados
    String mensagem = "";

    // Thread principal da aplicação
    Thread animador;

    // Criação dos semáforos da aplicação
    // O semáforo mutex que recebe o valor incial 1 para o contador
    // e é o semáforo principal da nossa aplicação
    public static Semaforo mutex = new Semaforo(1);

    // O vetor semáforos são normais e existe um semáforo para cada filósofo
    // que será criado, esses semafóros não recebem valores de inicialização
    // portanto iniciando o contador em 0
    public static Semaforo semaforos[] = new Semaforo[5];

    // Define um vetor para o estado de cada um dos filósofos presentes
    // na aplicação
    public static int estado[] = new int[5];

    // Cria 5 filósofos em um vetor para a aplicação
    static Filosofo filosofo[] = new Filosofo[5];

    // Método construtor da Grade da aplicação
    public Grade() {
        // Define o foco para este JPanel
        setFocusable(true);

        // Define um tamanho para a tela
        setSize(400, 400);

        // Seta a cor do fundo
        setBackground(Color.white);

        init();
    }

    // Método para inicializar tudo o que é preciso dentro da classe
    public void init() {
        // Inicializa todos estados para zero
        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }

        // Verifica se o Thread de animação é vazio
        if (animador == null) {
            // Então cria um novo Thread
            animador = new Thread(this);
            // Inicia sua execução
            animador.start();
        }

        // Define a prioridade principal para este atual Thread
        Thread.currentThread().setPriority(1);

        // Inicializa todos filósofos
        filosofo[0] = new Filosofo("Platao", 0);
        filosofo[1] = new Filosofo("Socrates", 1);
        filosofo[2] = new Filosofo("Aristoteles", 2);
        filosofo[3] = new Filosofo("Tales", 3);
        filosofo[4] = new Filosofo("Sofocles", 4);

        // Inicializa todos semáforos
        semaforos[0] = new Semaforo(0);
        semaforos[1] = new Semaforo(0);
        semaforos[2] = new Semaforo(0);
        semaforos[3] = new Semaforo(0);
        semaforos[4] = new Semaforo(0);

        // Inicia a execução de todos filósofos
        filosofo[0].start();
        filosofo[1].start();
        filosofo[2].start();
        filosofo[3].start();
        filosofo[4].start();
    }

    // Método para desenhar os objetos na tela da aplicação
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Define a cor azul
        g.setColor(Color.blue);
        g.drawOval(50, 50, 300, 300);

        // Para cada um dos filósofos será feito um desenho
        for (int i = 0; i < 5; i++) {
            // Define a cor para cara tipo de estado
            if (estado[i] == 0) {
                g.setColor(Color.gray);
                mensagem = "PENSANDO";
            }
            if (estado[i] == 1) {
                g.setColor(Color.yellow);
                mensagem = "FAMINTO";
            }
            if (estado[i] == 2) {
                g.setColor(Color.green);
                mensagem = "COMENDO";
            }

            // Desenha o filósofo, sua carinha e seu nome na tela
            // Define os planos (x,y) e posteriormente o tamanho do objeto a ser desenhado
            g.fillOval((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 15, 30, 30);
            g.setColor(Color.black);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 5, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 5, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 5, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 5);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 2, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 3, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 2, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)));
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 2, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)), (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 2, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)));
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 8, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 3, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 3, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 8, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8);
            g.drawString(filosofo[i].getName(), (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 25);
            g.drawString(mensagem, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15, (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 40);
        }

        // ATIVA A SINCRONIA
        Toolkit.getDefaultToolkit().sync();
        // PAUSA
        g.dispose();

    }

    // Método de execução da classe, onde o ambiente será rodado
    public void run() {
      long startTime = System.currentTimeMillis();

    do {
        // Redesenha a tela
        repaint();

        // Dorme durante um tempo para redesenhar novamente
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }

        // Verifica se atingiu 180 segundos
        tempoTotal = System.currentTimeMillis() - startTime;
        if (tempoTotal >= 180000) {
            exibirRelatorio();
            System.exit(0); // Saia após exibir o relatório
        }
    } while (true);
    }
    private void exibirRelatorio() {
    StringBuilder relatorio = new StringBuilder("Relatório:\n");
    for (int i = 0; i < 5; i++) {
        relatorio.append("Filósofo ").append(filosofo[i].getName()).append(" comeu ").append(vezesComeu[i]).append(" vezes.\n");
        relatorio.append("Tempo máximo de espera: ").append(tempoMaxEspera[i]).append(" ms.\n");
        relatorio.append("Média de espera: ").append(tempoEspera[i] / vezesComeu[i]).append(" ms.\n\n");
    }

    // Exiba o relatório em uma janela de diálogo
    javax.swing.JOptionPane.showMessageDialog(this, relatorio.toString(), "Relatório", javax.swing.JOptionPane.INFORMATION_MESSAGE);
}

}
