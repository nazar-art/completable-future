package completablefuture.j9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DelayedExecutor {

    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> promise = new CompletableFuture<>();

        promise.completeAsync(() -> 12, CompletableFuture.
                delayedExecutor(3, TimeUnit.SECONDS, executor))
                .thenAccept(res -> {
                    System.out.println(res);
                    System.out.println("run in: " + Thread.currentThread().getName());
                });

        promise.get();

        executor.shutdown();
    }
}
