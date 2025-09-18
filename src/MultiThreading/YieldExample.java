package MultiThreading;

public class YieldExample implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "Count" + i);
            Thread.yield();
        }
    }
    public static void main(String[] args) {
        Thread thread1 = new Thread(new YieldExample());
        Thread thread2 = new Thread(new YieldExample());
        thread2.start();
        thread1.start();
    }
}
/*
Current thread moves from RUNNING → RUNNABLE state.
The thread may immediately continue running, or other threads of the same priority may get CPU time.
No guarantee which thread will run next.
yield() = suggest to scheduler: “I’m done for now, let other threads run.”
 */