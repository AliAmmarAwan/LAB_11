package lab;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class tokenizeVerse {
    public static void main(String[] args) {
        String poemText = readFromFile("poem.txt");
        List<String> tokens = tokenizeVerse (poemText);
        System.out.println("Tokenized Verse: " + tokens);
    }
    private static List<String>tokenizeVerse (String verse) {
        //let split by spaces
        String[] tokens = verse.split("\\s+");
        return List.of(tokens);
    }
    private static String readFromFile(String fileName) {
        try {
            Path filePath = Paths.get(fileName);
            return Files.readString(filePath);
        } 
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
