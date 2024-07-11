package library;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ViewUsers extends JFrame {

    public ViewUsers() {
        setTitle("View Users");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        ArrayList<User> users = readUsersFromFile();
        StringBuilder userList = new StringBuilder();

        for (User user : users) {
            userList.append("Name: ").append(user.getName())
                    .append(", User ID: ").append(user.getUserId())
                    .append("\n");
        }

        textArea.setText(userList.toString());
        add(new JScrollPane(textArea));

        setVisible(true);
    }

    private ArrayList<User> readUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
