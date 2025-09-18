package MultiThreading;

public class Interrupt implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Interrupt());
        t.start();
        Thread.sleep(100);
        t.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Working...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted! Exiting...");
                Thread.currentThread().interrupt(); // preserve interrupt status
            }
        }
    }

    }
