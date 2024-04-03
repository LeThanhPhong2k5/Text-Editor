import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.io.*;
import java.util.ArrayList;

public class Model {

    ArrayList<String> Dictionary = new ArrayList<>();

    Model() throws IOException {
        loadDictionary();
    }

    private void loadDictionary() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Dictionary.txt"))) {
            String st;
            while ((st = br.readLine()) != null) {
                Dictionary.add(st);
            }
            System.out.println(Dictionary.size());
        }
    }

    void Write_To_File(File file, JTextPane pane) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(pane.getText());
        }
    }

    void Add_word(String word) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Dictionary.txt", true))) {
            writer.write(word + "\n");
            System.out.println("Từ mà bạn thêm vào là: " + word);
        }
        Dictionary.clear();
        loadDictionary();
    }

    void Read_File(File file, JTextPane pane) throws IOException, BadLocationException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StyledDocument document = (StyledDocument) pane.getDocument();
            String st;
            while ((st = br.readLine()) != null) {
                document.insertString(document.getLength(), st + "\n", null);
            }
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
