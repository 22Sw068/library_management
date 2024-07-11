package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class AddBook extends JFrame {

    private JTextField txtTitle, txtAuthor, txtISBN;

    public AddBook() {
        setTitle("Add Book");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("Title:");
        JLabel lblAuthor = new JLabel("Author:");
        JLabel lblISBN = new JLabel("ISBN:");

        txtTitle = new JTextField();
        txtTitle.setPreferredSize(new Dimension(150, 25));
        txtAuthor = new JTextField();
        txtAuthor.setPreferredSize(new Dimension(150, 25));
        txtISBN = new JTextField();
        txtISBN.setPreferredSize(new Dimension(150, 25));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(80, 30));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBook();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblTitle, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(txtTitle, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblAuthor, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(txtAuthor, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblISBN, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(txtISBN, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSave, gbc);
        add(panel);
        setVisible(true);
    }

    private void saveBook() {
        String title = txtTitle.getText();
        String author = txtAuthor.getText();
        String isbn = txtISBN.getText();

        Book book = new Book(title, author, isbn);

        ArrayList<Book> books = readBooksFromFile();
        books.add(book);
        writeBooksToFile(books);

        JOptionPane.showMessageDialog(this, "Book added successfully!");
    }

    private ArrayList<Book> readBooksFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            return (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void writeBooksToFile(ArrayList<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddBook();
            }
        });
    }
}
