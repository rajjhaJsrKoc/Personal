package MultiThreading;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> futureExample = CompletableFuture.supplyAsync(() -> 20,executor);
        futureExample.thenAccept(System.out::println).join();

        CompletableFuture.supplyAsync(() -> 10, executor)
                .thenApply(x -> x * 2)
                .thenAccept(System.out::println)
                .join();
        futureExample.exceptionally(throwable -> {System.out.println("Print Exception");
        return -1;
        }).thenAccept(System.out::println).join();

    }
}
