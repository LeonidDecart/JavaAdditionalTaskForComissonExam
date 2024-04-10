import javax.swing.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        Path directoryPath = selectDirectoryPath();
        if (directoryPath == null) {
            System.out.println("Не получилось выбрать директорию");
            System.exit(0);
        }

        FileAnalyzer analyzer = new FileAnalyzer();
        analyzer.analyzeFiles(directoryPath);
        analyzer.displayWordFrequencies();
    }

    public static Path selectDirectoryPath() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Выберите директорию с текстовыми файлами");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().toPath();
        } else {
            return null;
        }
    }
}
