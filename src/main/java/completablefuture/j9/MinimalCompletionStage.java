package completablefuture.j9;

import util.QuoteUtil;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class MinimalCompletionStage {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture<String> original = CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor);

        CompletionStage<String> copy =
                original.minimalCompletionStage();

        CompletableFuture<String> convertedBack
                = copy.toCompletableFuture();

        Consumer<String> consoleConsumer = res -> {
            System.out.println(res);
            System.out.println("run in: " + Thread.currentThread().getName());
        };

        original.thenAccept(consoleConsumer);
        copy.thenAccept(consoleConsumer);
        convertedBack.thenAccept(consoleConsumer);

        /*original.thenAcceptAsync(consoleConsumer, executor);
        copy.thenAcceptAsync(consoleConsumer, executor);
        convertedBack.thenAcceptAsync(consoleConsumer, executor);*/



        executor.shutdown();
    }
}
