package Jantar;

import java.util.ArrayList;
/**
 *
 * @author Tárcio
 */
public class Main {

    public boolean[] garfos = new boolean[5];

    public static void main(String[] args) throws InterruptedException {
        Estados estados = new Estados();
        ArrayList<Filosofo> filosofos = new ArrayList();
        for(int i = 0; i < 5; i++){
            Filosofo f = new Filosofo(i, estados);
            filosofos.add(f);
            f.setName("Filosofo" + i);
        }
        for(Filosofo f: filosofos){
            f.start();
        }
        Thread.sleep(10000);
        for(Filosofo f: filosofos){
            f.parar();
        }
    }
}
