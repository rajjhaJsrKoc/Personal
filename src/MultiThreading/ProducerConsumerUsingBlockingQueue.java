package MultiThreading;

import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerUsingBlockingQueue {
    public static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

    public static  void main(String[] args){
        Thread t1 = new Thread(()->{
            try {
                for (int i = 0; i<7;i++){
                    queue.put(i);
                    System.out.println(i);
                }
                queue.put(-1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread t2 = new Thread(()->{
            try {
                while(true){
                    int val = queue.take();
                    if(val==-1){
                        break;
                    }
                    System.out.println(val);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        t2.start();
    }



}
