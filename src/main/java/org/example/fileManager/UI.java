package org.example.fileManager;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Class responsible for the user interface.
 *
 * @Author Biniyam Yemaneberhane
 */
public class UI {
    private FileManager fileManager;
    private Scanner scanner;

    /**
     * Constructor for UI.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager(System.getProperty("user.dir"));
    }

    /**
     * Starts the user interface.
     */
    public void start() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();
            handleChoice(choice);
        }
    }

    /**
     * Displays the menu.
     */
    private void showMenu() {
        System.out.println("File Manager");
        System.out.println("1. Display Directory Contents");
        System.out.println("2. Copy File");
        System.out.println("3. Move File");
        System.out.println("4. Delete File");
        System.out.println("5. Create Directory");
        System.out.println("6. Delete Directory");
        System.out.println("7. Search Files");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Handles the user's choice.
     *
     * @param choice The user's choice.
     */
    private void handleChoice(String choice) {
        try {
            switch (choice) {
                case "1":
                    displayDirectoryContents();
                    break;
                case "2":
                    copyFile();
                    break;
                case "3":
                    moveFile();
                    break;
                case "4":
                    deleteFile();
                    break;
                case "5":
                    createDirectory();
                    break;
                case "6":
                    deleteDirectory();
                    break;
                case "7":
                    searchFiles();
                    break;
                case "8":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            Logger.log(e);
        }
    }

    /**
     * Displays the contents of the directory.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void displayDirectoryContents() throws IOException {
        List<String> files = fileManager.listFiles();
        files.forEach(System.out::println);
    }

    /**
     * Copies a file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void copyFile() throws IOException {
        System.out.print("Enter source file: ");
        String source = scanner.nextLine();
        System.out.print("Enter target file: ");
        String target = scanner.nextLine();
        fileManager.copyFile(source, target);
        System.out.println("File copied successfully.");
    }

    /**
     * Moves a file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void moveFile() throws IOException {
        System.out.print("Enter source file: ");
        String source = scanner.nextLine();
        System.out.print("Enter target file: ");
        String target = scanner.nextLine();
        fileManager.moveFile(source, target);
        System.out.println("File moved successfully.");
    }

    /**
     * Deletes a file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void deleteFile() throws IOException {
        System.out.print("Enter file to delete: ");
        String filename = scanner.nextLine();
        fileManager.deleteFile(filename);
        System.out.println("File deleted successfully.");
    }

    /**
     * Creates a directory.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void createDirectory() throws IOException {
        System.out.print("Enter directory name: ");
        String dirname = scanner.nextLine();
        fileManager.createDirectory(dirname);
        System.out.println("Directory created successfully.");
    }

    /**
     * Deletes a directory.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void deleteDirectory() throws IOException {
        System.out.print("Enter directory name: ");
        String dirname = scanner.nextLine();
        fileManager.deleteDirectory(dirname);
        System.out.println("Directory deleted successfully.");
    }

    /**
     * Searches for files.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void searchFiles() throws IOException {
        System.out.print("Enter search pattern: ");
        String pattern = scanner.nextLine();
        List<String> results = fileManager.searchFiles(pattern);
        results.forEach(System.out::println);
    }
}
