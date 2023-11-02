package f219514_lab_11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class addPoem {
    public static void main(String[] args) {
        String poemText = "WAR DISASTER ISRAEL VS PALESTINE WWII";
        appendToFile("poem.txt", poemText);
        System.out.println("Added Poem' Text :\n" + poemText);
    }

    private static void appendToFile(String fileName, String content) {
        try {
            Path filePath = Paths.get(fileName);
            Files.write(filePath, (content + "\n").getBytes(), StandardOpenOption.APPEND);
            System.out.println("Poem appended to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
