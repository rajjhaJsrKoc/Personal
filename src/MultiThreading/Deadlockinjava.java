package MultiThreading;
// NEW → RUNNABLE → RUNNING → (WAITING / TIMED_WAITING / BLOCKED) → RUNNABLE → TERMINATED
public class Deadlockinjava {
    public final Object lock = new Object();
    public final Object lock2 = new Object();
    public static void main(String[] args) {
        Deadlockinjava d = new Deadlockinjava();
        Thread t1 = new Thread(d::deadlock1);
        Thread t2 = new Thread(d::deadlock2);
        t1.start();
        t2.start();

       /* start() method

        Creates a new thread.

                JVM calls the run() method on that new thread.

                Runs concurrently with the main thread (or other threads).

                run() method (called directly)

        It’s just a normal method call.

        Executes in the same thread that called it.

        No new thread is created.*/
    }
    public void deadlock1() {
        synchronized (lock) {
            System.out.println("Inside deadlock1");
            synchronized (lock2) {
             System.out.println(Thread.currentThread().getName() + " is deadlocked");
            }
        }
    }
    public void deadlock2() {
        synchronized (lock2) {
            System.out.println("Inside deadlock2");
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " is deadlocked Thread 2");
            }
        }
    }
}
