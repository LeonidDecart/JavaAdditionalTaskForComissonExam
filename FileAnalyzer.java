import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileAnalyzer {
    private Map<String, Integer> wordCount = new HashMap<>();

    public void analyzeFiles(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            paths.filter(Files::isRegularFile).forEach(this::processFile);
        } catch (IOException e) {
            System.out.println("Error walking through directory: " + directoryPath);
            System.exit(1);
        }
    }

    private void processFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            lines.flatMap(line -> Arrays.stream(line.toLowerCase().split("\\P{L}+")))
                    .filter(word -> !word.isEmpty())
                    .forEach(word -> wordCount.merge(word, 1, Integer::sum));
        } catch (IOException e) {
            System.out.println("Error reading file: " + filePath);
        }
    }

    public void displayWordFrequencies() {
        List<Map.Entry<String, Integer>> sortedWords = wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        sortedWords.forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}