package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class AddUser extends JFrame {

    private JTextField txtName, txtUserId;

    public AddUser() {
        setTitle("Add User");
        setSize(300, 150);
        setLocationRelativeTo(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setPreferredSize(new Dimension(80, 25));
        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setPreferredSize(new Dimension(80, 25));

        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(150, 25));
        txtUserId = new JTextField();
        txtUserId.setPreferredSize(new Dimension(150, 25));

        JButton btnSave = new JButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUserId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(txtUserId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnSave, gbc);

        add(panel);

        setVisible(true);
    }

    private void saveUser() {
        String name = txtName.getText();
        String userId = txtUserId.getText();

        User user = new User(name, userId);

        ArrayList<User> users = readUsersFromFile();
        users.add(user);
        writeUsersToFile(users);

        JOptionPane.showMessageDialog(this, "User added successfully!");
    }

    private ArrayList<User> readUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            return (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void writeUsersToFile(ArrayList<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddUser();
            }
        });
    }
}
