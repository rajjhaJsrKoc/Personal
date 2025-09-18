package MultiThreading;

public class RaceConditionExamples {
    public static  int count= 1;
    public synchronized void increment(){
        if (count < 1000000000) {
            count++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        RaceConditionExamples race = new RaceConditionExamples();

        Runnable runnable = (() ->{
            while (count<1000000000){
                race.increment();
            }
        });

        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread.sleep(10);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        // suppose there are 3 threads where 2 are given
        // highest priority the another might not run due to less OS consumtion that is called starvation

        System.out.println(count);

    }
}
