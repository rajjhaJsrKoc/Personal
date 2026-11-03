package MultiThreading;

public class PrintThreadAlternativelyCountSyncronized {
    private boolean thread1Turn = true;

    synchronized void print(String message, boolean isThread1) {
        for (int i = 0; i < 5; i++) {
            while (thread1Turn != isThread1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println(message);
            thread1Turn = !thread1Turn;
            notifyAll();
        }
    }


    public static void main(String[] args) {
        PrintThreadAlternativelyCountSyncronized printer = new PrintThreadAlternativelyCountSyncronized();

        Thread t1 = new Thread(() -> printer.print("Thread 1", true));
        Thread t2 = new Thread(() -> printer.print("Thread 2", false));

        t1.start();
        t2.start();
    }
}
