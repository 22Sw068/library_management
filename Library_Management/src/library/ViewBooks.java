package library;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
public class ViewBooks extends JFrame {
    public ViewBooks() {
        setTitle("View Books");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<Book> books = readBooksFromFile();
        StringBuilder bookList = new StringBuilder();

        for (Book book : books) {
            bookList.append("Title: ").append(book.getTitle())
                    .append(", Author: ").append(book.getAuthor())
                    .append(", ISBN: ").append(book.getISBN())
                    .append("\n");
        }
        textArea.setText(bookList.toString());
        add(new JScrollPane(textArea));
        setVisible(true);
    }
    private ArrayList<Book> readBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            return (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
