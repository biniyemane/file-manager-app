package org.example.fileManager;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class responsible for file operations.
 *
 * @Author Biniyam Yemaneberhane
 */
public class FileManager {
    private Path currentDirectory;

    /**
     * Constructor for FileManager.
     *
     * @param directory The directory to manage.
     */
    public FileManager(String directory) {
        this.currentDirectory = Paths.get(directory);
    }

    /**
     * Lists files in the current directory with details.
     *
     * @return List of file details.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> listFiles() throws IOException {
        return Files.list(currentDirectory)
                .map(path -> {
                    try {
                        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
                        return String.format("%s (Size: %d bytes, Last Modified: %s)",
                                path.getFileName(),
                                attr.size(),
                                attr.lastModifiedTime());
                    } catch (IOException e) {
                        return path.getFileName().toString();
                    }
                })
                .collect(Collectors.toList());
    }

    /**
     * Copies a file.
     *
     * @param source The source file name.
     * @param target The target file name.
     * @throws IOException If an I/O error occurs.
     */
    public void copyFile(String source, String target) throws IOException {
        Files.copy(currentDirectory.resolve(source), currentDirectory.resolve(target), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Moves a file.
     *
     * @param source The source file name.
     * @param target The target file name.
     * @throws IOException If an I/O error occurs.
     */
    public void moveFile(String source, String target) throws IOException {
        Files.move(currentDirectory.resolve(source), currentDirectory.resolve(target), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Deletes a file.
     *
     * @param filename The file name to delete.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteFile(String filename) throws IOException {
        Files.delete(currentDirectory.resolve(filename));
    }

    /**
     * Creates a directory.
     *
     * @param dirname The directory name.
     * @throws IOException If an I/O error occurs.
     */
    public void createDirectory(String dirname) throws IOException {
        Files.createDirectory(currentDirectory.resolve(dirname));
    }

    /**
     * Deletes a directory.
     *
     * @param dirname The directory name.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteDirectory(String dirname) throws IOException {
        Files.delete(currentDirectory.resolve(dirname));
    }

    /**
     * Searches for files in the current directory based on a pattern.
     *
     * @param pattern The search pattern.
     * @return List of matching file paths.
     * @throws IOException If an I/O error occurs.
     */
    public List<String> searchFiles(String pattern) throws IOException {
        return Files.walk(currentDirectory)
                .filter(path -> path.getFileName().toString().contains(pattern))
                .map(Path::toString)
                .collect(Collectors.toList());
    }
}


