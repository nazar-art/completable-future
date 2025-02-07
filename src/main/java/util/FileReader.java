package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

final class FileReader {

    private static final Logger LOGGER = LogManager.getLogger(FileReader.class);

    String readFile(String path) {

        List<String> content = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            content = stream
                    .map(String::toUpperCase)
                    .toList();
        } catch (IOException e) {
            LOGGER.error("The following error has occurred" +
                    " during processing the file: ", e);
        }

        return content.toString();
    }
}
