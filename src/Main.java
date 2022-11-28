import java.util.ArrayList;
import java.util.List;

public class Main implements Runnable {


    public static List<Libro> libroList = new ArrayList<>();

    public boolean leido;
    public boolean leido2;

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            libroList.add(new Libro("libro" + i));
        }
        for (int i = 0; i < 4; i++) {
            Main ewn = new Main();

            Thread hilo = new Thread(ewn);
            hilo.setName("Alumno " + i);
            hilo.start();
        }

    }


    @Override
    public void run() {
        try {

            Libro libro1 = libroList.get((int) (Math.random() * 9));
            Libro libro2 = libroList.get((int) (Math.random() * 9));
            synchronized (libro1) {
                synchronized (libro2) {

                    while (!leido2 && libro2.isEnPosesion()) {
                        libro2.wait();
                    }
                    while (!leido && libro1.isEnPosesion()) {
                        libro1.wait();
                    }
                    libro1.setEnPosesion(true);
                    libro2.setEnPosesion(true);

                    System.out.println(Thread.currentThread().getName() + " ha codigo el " + libro1.name);
                    System.out.println(Thread.currentThread().getName() + " ha codigo el " + libro2.name);
                    System.out.println(Thread.currentThread().getName() + " ha soltado el " + libro1.name);
                    System.out.println(Thread.currentThread().getName() + " ha soltado el " + libro2.name);

                    leido = true;
                    leido2 = true;
                    libro1.setEnPosesion(false);
                    libro2.setEnPosesion(false);

                    libro2.notifyAll();
                    libro1.notifyAll();
                }


            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}




