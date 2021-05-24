package Repository;

import Domain.Car;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class RepositoryFile<T> extends Repository<T> {

    private final String fileName;

    public RepositoryFile(String fileName) throws IOException, NumberFormatException {
        this.fileName = fileName;
        readFromFile();
    }

    private String getFileType() {
        return fileName.split("\\.")[1].toLowerCase();
    }

    private void readFromFile() throws IOException, NumberFormatException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        Car car = null;
        while (scanner.hasNextLine()) {
            if (getFileType().equals("csv")) {
                car = Car.readFromFile(scanner.nextLine());
            } else if (getFileType().equals("html")) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < 7; i++) {
                    result.append(scanner.nextLine()).append('\n');
                }
                car = Car.readFromHtmlFile(result.toString());
            }
            super.add((T) car);
        }
        scanner.close();
    }

    private void writeToFile() throws IOException {
        FileWriter file = new FileWriter(fileName);
        for (T entity : getEntities()) {
            if (getFileType().equals("csv")) {
                Car.writeToCsvFile(file, (Car) entity);
            } else if (getFileType().equals("html")) {
                Car.writeToHtmlFile(file, (Car) entity);
            }
        }
        file.close();
    }

    private void appendToFile(T entity) throws IOException {
        FileWriter file = new FileWriter(fileName, true);
        if (getFileType().equals("csv")) {
            Car.writeToCsvFile(file, (Car) entity);
        } else if (getFileType().equals("html")) {
            Car.writeToHtmlFile(file, (Car) entity);
        }
        file.close();
    }

    public void clear() throws IOException {
        new FileWriter(fileName, false).close();
    }

    public void open() throws IOException {
        File file = new File(fileName);
        if (getFileType().equals("csv")) {
            String execString = "notepad.exe " + file;
            Runtime run = Runtime.getRuntime();
            run.exec(execString);
        } else if (getFileType().equals("html")) {
            Desktop.getDesktop().browse(file.toURI());
        }
    }

    @Override
    public void add(T entity) throws UnsupportedOperationException, IOException {
        super.add(entity);
        appendToFile(entity);
    }

    @Override
    public void delete(T entity) throws UnsupportedOperationException, IOException {
        super.delete(entity);
        writeToFile();
    }

    @Override
    public void update(T entity) throws UnsupportedOperationException, IOException {
        super.update(entity);
        writeToFile();
    }
}
