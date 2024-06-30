package ua.vkireiev.portaone.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    public List<String> readFile(String filePath) throws IllegalArgumentException {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {

            return stream
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException | InvalidPathException e) {
            throw new IllegalArgumentException("Can't read file: " + filePath, e);
        }
    }

}
