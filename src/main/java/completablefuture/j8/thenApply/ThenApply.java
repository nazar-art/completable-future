package completablefuture.j8.thenApply;

import util.QuoteUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThenApply {

    private static final String MOVIE_NAME = "\nBack to the future";

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        QuoteUtil quoteUtil = new QuoteUtil();

        CompletableFuture
                .supplyAsync(quoteUtil::getQuote, executor)
                .thenApply(result -> result.concat(MOVIE_NAME))
                .thenApply(String::length)
                .thenAccept(System.out::println);

        executor.shutdown();
    }
}
