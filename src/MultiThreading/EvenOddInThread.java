package MultiThreading;

public class EvenOddInThread {
    public int intialValue= 1;
    public int limit = 10;
    private final Object lock = new Object();
    private static volatile boolean running = true;
    public static void main(String[] args) throws InterruptedException {
        EvenOddInThread evenOddInThread = new EvenOddInThread();
        Thread threads = new Thread(evenOddInThread::evenNumber, "even Thread");
        Thread threads2 = new Thread(evenOddInThread::oddNumber, "odd Thread");
        threads.start();
        threads2.start();
        Thread.sleep(2000);

        // Interrupt one thread to simulate external interruption
        threads.interrupt();
        threads.join();
        threads2.join();

    }
    public void oddNumber() {
        while (intialValue < limit) {
            synchronized (lock) {
                while (running && intialValue % 2 == 0) { // wait if it's not odd
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.currentThread().getName() + " interrupted.");
                        running = false;
                        lock.notifyAll(); // wake up the other thread before exit
                        Thread.currentThread().interrupt(); // restore interrupt status
                        return;
                    }
                }
                System.out.println(intialValue + " is odd - " + Thread.currentThread().getName());
                intialValue++;
                lock.notify();
            }
        }
    }

    public void evenNumber() {
        while (intialValue < limit) {
            synchronized (lock) {
                while (running && intialValue % 2 != 0) { // wait if it's not even
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + " interrupted.");
                        running = false;
                        lock.notifyAll(); // wake up the other thread
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if (!running) return;
                System.out.println(intialValue + " is even - " + Thread.currentThread().getName());
                intialValue++;
                lock.notify();
            }
        }
    }
}
