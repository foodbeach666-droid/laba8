import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
    public class ex2 {
        public static void main(String[] args) {
            String inputFile  = "input.txt";String outputFile = "output.txt";
            if (createInputFile(inputFile)) {
                System.out.println("Исходный файл создан: " + inputFile);
            } else {
                System.err.println("Ошибка при создании исходного файла.");
                return;
            }
            if (processAndWrite(inputFile, outputFile)) {
                System.out.println("Результат записан в: " + outputFile);System.out.println("\nСодержимое выходного файла:");
                printFileContent(outputFile);
            } else {
                System.err.println("Ошибка при обработке данных.");
            }
        }
        private static boolean createInputFile(String filename) {
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filename),
                            StandardCharsets.UTF_8))) {
                writer.write("Первая строка (игнорируется)\n");
                writer.write("Вторая строка (копируется)\n");
                writer.write("3.14\n");
                writer.write("-2.5\n");
                writer.write("0.0\n");
                writer.write("42.0\n");
                writer.write("-100.1\n");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        private static boolean processAndWrite(String inFile, String outFile) {
            List<String> lines = new ArrayList<>();
            List<Double> numbers = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inFile),
                            StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        double num = Double.parseDouble(line);
                        numbers.add(num);
                    } catch (NumberFormatException e) {
                        lines.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            String secondLine = lines.size() >= 2 ? lines.get(1) : "Нет второй строки";
            List<Double> positiveNumbers = new ArrayList<>();
            for (Double num : numbers) {
                if (num > 0) positiveNumbers.add(num);
            }
            try (BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(outFile),
                            StandardCharsets.UTF_8))) {
                writer.write(secondLine + "\n");
                for (Double num : positiveNumbers) {
                    writer.write(num + "\n");
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        private static void printFileContent(String filename) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filename),
                            StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

