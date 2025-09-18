package MultiThreading;

import java.util.concurrent.*;

public class ExcecuterCallableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Integer> task = () -> {
            Thread.sleep(1000);
            return 42;
        };
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,                       // core pool size
                4,                       // max pool size
                10, TimeUnit.SECONDS,    // keep-alive time
                new ArrayBlockingQueue<>(2),  // work queue
                new ThreadPoolExecutor.CallerRunsPolicy() // rejection policy
        );

        // can be controll alot of things
        Future<Integer> future = executorService.submit(task);
        Runnable task2 = (() ->{
           System.out.println("Inside Runnable Task");
        });
        executor.execute(task2);
        // see if we execute then it is runnable then it will not return a future if
        // if callable because it return the value then we use submit plus future object reference and use get used blow
        System.out.println("Result: " + future.get()); // blocks until task finishes
        executorService.shutdown();
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /*
    | Feature                | Executor                    | ThreadPoolExecutor                           |
| ---------------------- | --------------------------- | -------------------------------------------- |
| Type                   | Interface                   | Class (implements ExecutorService)           |
| Control over threads   | Minimal                     | Full control (core/max threads, queue, etc.) |
| Task Submission        | `execute(Runnable)`         | `execute(Runnable)` / `submit(Callable)`     |
| Thread Pool Management | Delegated to implementation | Configurable directly                        |
| Complexity             | Simple                      | Advanced                                     |

     */
}
