package dk.easv.exceptionexamples.dal;

import dk.easv.exceptionexamples.exceptions.MyOwnException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextDAO {
    public String readFile(String file) throws MyOwnException {
        Path path = Paths.get(file);
        String fileContents = null;
        try {
            fileContents = Files.readString(path);
        } catch (IOException e) {
            throw new MyOwnException("Could not read file: " + path.toString());
        }
        return fileContents;

    }

}
