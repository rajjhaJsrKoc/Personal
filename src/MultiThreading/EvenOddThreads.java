package MultiThreading;

public class EvenOddThreads {

    private int number = 1;
    private final int MAX = 10;

    public synchronized void printOdd() {
        while (number <= MAX) {
            try {
                while (number % 2 == 0 && number <= MAX) {
                    wait();
                }
                if (number <= MAX) {
                    //System.out.println("Odd: " + number);
                    System.out.println(Thread.currentThread().getName() + " is printing odd number: " + number);
                    number++;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public synchronized void printEven() {
        while (number <= MAX) {
            try {
                while (number % 2 != 0 && number <= MAX) {
                    wait();
                }
                if (number <= MAX) {
                    //System.out.println("Even: " + number);
                    System.out.println(Thread.currentThread().getName() + " is printing even number: " + number);
                    number++;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(String[] args) {
        EvenOddThreads obj = new EvenOddThreads();

        Thread t1 = new Thread(obj::printOdd);
        Thread t2 = new Thread(obj::printEven);

        t1.start();
        t2.start();
    }
}