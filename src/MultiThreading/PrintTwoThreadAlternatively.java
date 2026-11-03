package MultiThreading;

public class PrintTwoThreadAlternatively {

    private static int count = 1;
    private static final int MAX = 10;

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    while (count <= MAX && count % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (count <= MAX) {
                        System.out.println("Thread 1");
                        count++;
                        lock.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (count <= MAX) {
                synchronized (lock) {
                    while (count <= MAX && count % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (count <= MAX) {
                        System.out.println("Thread 2");
                        count++;
                        lock.notifyAll();
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}