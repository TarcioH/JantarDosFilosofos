/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jantar;

/**
 *
 * @author Win10
 */
import javax.swing.JFrame;

public class JantarDosFilosofos extends JFrame
{

    public JantarDosFilosofos ()
    {
        // CRIA UMA NOVA GRADE NA TELA
        add(new Grade());

        // DEFINE O TITULO
        setTitle("Jantar dos Filósofos");
        // INFORMA O MÉTODO DE SAÍDA
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // SETA O TAMANHO
        setSize(410, 410);
        // SETA A LOCALIZAÇÃO
        setLocationRelativeTo(null);
        // SETA A VISIBILIDADE
        setVisible(true);
        // SETA SE É REDIMENSIONAVEL
        setResizable(false);
    }

    public static void main(String[] args)
    {
        new JantarDosFilosofos();
    }

}
