package by.epam.shape.reader.impl;

import by.epam.shape.exception.RectangleException;
import by.epam.shape.reader.RectangleReader;
import by.epam.shape.validator.RectangleLineValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RectangleReaderImpl implements RectangleReader {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<String> readStringFromFile(String pathToFile) throws RectangleException {
        Path path = Paths.get(pathToFile);
        List<String> correctLines;
        try (Stream<String> fileLines = Files.lines(path)) {
            correctLines = fileLines
                    .filter(RectangleLineValidator::validateParameters)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "File can't be open");
            throw new RectangleException("File can't be open");
        }
        logger.log(Level.INFO, "Collection of strings from file " + correctLines);
        return correctLines;
    }
}
