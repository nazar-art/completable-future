package util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

public final class QuoteUtil {

    private final String folderPath;
    private final FileReader fileReader;
    private final String quote;

    public QuoteUtil() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("quotes");
        folderPath = Objects.requireNonNull(resource, "path must not be null").getPath();
        fileReader = new FileReader();
        String path = initPath();
        quote = fileReader.readFile(path);
    }

    public String getQuote() {
        return quote;
    }

    public String appendQuote(String resultQuote) {
        String path = initPath();
        return resultQuote
                .concat(fileReader.readFile(path));
    }

    public List<String> getQuotes() {
        List<String> quotes = new ArrayList<>();
        quotes.add(quote);
        String path = initPath();
        quotes.add(fileReader.readFile(path));
        return quotes;
    }

    public String emptyQuote() {
        return null;
    }

    private String initPath() {
        int quoteNumber = new SplittableRandom()
                .ints(1, 5)
                .findFirst()
                .orElse(1);

        return folderPath.concat(File.separator + quoteNumber);
    }
}
