import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class ex3 {
    private static final Set<Character> CONSONANTS = new HashSet<>(Arrays.asList(
            'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м',
            'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ'
    ));
    private static final String POEM_TEXT =
            "Мороз и солнце; день чудесный!\n" +
            "Еще ты дремлешь, друг прелестный —\n" +
            "Пора, красавица, проснись:\n" +
            "Открой сомкнуты негой взоры.";
    public static void main(String[] args) {
        String inputFile  = "source_poem.txt";
        String outputFile = "filtered_consonant_words.txt";
        if (createSourceFile(inputFile)) {
            System.out.println("Исходный файл создан: " + inputFile);
        } else {
            System.err.println("Ошибка при создании исходного файла.");
            return;
        }
        if (processAndWrite(inputFile, outputFile)) {
            System.out.println("Результат записан в: " + outputFile);
            System.out.println("\nСодержимое выходного файла:");
            printOutputFile(outputFile);
        } else {
            System.err.println("Ошибка при обработке данных.");
        }
    }
    private static boolean createSourceFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(POEM_TEXT);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean processAndWrite(String inFile, String outFile) {
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader
                        (new FileInputStream(inFile), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter
                     (new OutputStreamWriter
                             (new FileOutputStream(outFile), StandardCharsets.UTF_8))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                List<String> consonantWords = extractConsonantWords(line);
                String wordsStr = String.join(" ", consonantWords);
                writer.write(String.format(
                        "Строка %d (%d слов): %s%n",
                        lineNumber, consonantWords.size(), wordsStr
                ));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static List<String> extractConsonantWords(String line) {
        List<String> result = new ArrayList<>();
        String[] words = line.split("[\\p{Punct}\\s]+");
        for (String word : words) {
            if (word.isEmpty()) continue;
            char firstChar = Character.toLowerCase(word.charAt(0));
            if (Character.isLetter(firstChar) && CONSONANTS.contains(firstChar)) {
                result.add(word);
            }
        }
        return result;
    }
    private static void printOutputFile(String filename) {
        try (BufferedReader reader = new BufferedReader
                (new InputStreamReader (new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
