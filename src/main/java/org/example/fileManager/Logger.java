package org.example.fileManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

/**
 * Class responsible for logging errors and exceptions.
 *
 * @Author Biniyam Yemaneberhane
 */
public class Logger {
    private static final Path logFile = Paths.get("resources", "log.txt");

    static {
        try {
            if (!Files.exists(logFile)) {
                Files.createFile(logFile);
            }
        } catch (IOException e) {
            System.err.println("Could not create log file: " + e.getMessage());
        }
    }

    /**
     * Logs an exception to the log file.
     *
     * @param e The exception to log.
     */
    public static void log(Exception e) {
        String message = LocalDateTime.now() + " - " + e.getMessage() + System.lineSeparator();
        try {
            Files.write(logFile, message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            System.err.println("Could not write to log file: " + ex.getMessage());
        }
    }
}


