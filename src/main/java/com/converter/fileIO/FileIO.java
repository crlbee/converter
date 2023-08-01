package com.converter.fileIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

//Надо было сделать свои исключения, но у меня еще 2 задачи которые нужно сделать
public class FileIO {

    private FileIO() {

    }

    public static void writeToFile(Path file, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toString()))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в файл " + file.toString());
        }
    }


    public static File createTempFile() {
        try {
            return File.createTempFile("temp", null);
        } catch (IOException e) {
            throw new RuntimeException("не удалось создать временный файл");
        }
    }

    public static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать из файла " + path.toString());
        }
    }

    public static void move(Path from, Path to) {
        try {
            Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось перезаписать файл " + from.toString());
        }

    }
}
