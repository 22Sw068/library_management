package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class RemoveBook extends JFrame {
    private JTextField txtISBN;
    public RemoveBook() {
        setTitle("Remove Book");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JLabel lblISBN = new JLabel("ISBN:");
        txtISBN = new JTextField();
        txtISBN.setPreferredSize(new Dimension(150, 25));

        JButton btnRemove = new JButton("Remove");
        btnRemove.setPreferredSize(new Dimension(100, 30));

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(lblISBN, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(txtISBN, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnRemove, gbc);

        add(panel);

        setVisible(true);
    }

    private void removeBook() {
        String isbn = txtISBN.getText();

        ArrayList<Book> books = readBooksFromFile();
        boolean removed = books.removeIf(book -> book.getISBN().equalsIgnoreCase(isbn));
        writeBooksToFile(books);

        if (removed) {
            JOptionPane.showMessageDialog(this, "Book removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Book not found!");
        }
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
                new RemoveBook();
            }
        });
    }
}
