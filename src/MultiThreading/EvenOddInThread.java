package MultiThreading;

public class EvenOddInThread {
    public int intialValue= 1;
    public int limit = 10;
    private final Object lock = new Object();
    public static void main(String[] args) {
        EvenOddInThread evenOddInThread = new EvenOddInThread();
        Thread threads = new Thread(evenOddInThread::evenNumber, "even Thread");
        Thread threads2 = new Thread(evenOddInThread::oddNumber, "odd Thread");
        threads.start();
        threads2.start();
    }
    public void oddNumber() {
        while (intialValue < limit) {
            synchronized (lock) {
                while (intialValue % 2 == 0) { // wait if it's not odd
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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
                while (intialValue % 2 != 0) { // wait if it's not even
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(intialValue + " is even - " + Thread.currentThread().getName());
                intialValue++;
                lock.notify();
            }
        }
    }
}
