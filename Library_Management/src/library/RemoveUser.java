package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class RemoveUser extends JFrame {

    private JTextField txtUserId;

    public RemoveUser() {
        setTitle("Remove User");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel lblUserId = new JLabel("User ID:");
        lblUserId.setFont(new Font("Arial", Font.PLAIN, 18));
        lblUserId.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtUserId = new JTextField(10);
        txtUserId.setFont(new Font("Arial", Font.PLAIN, 16));
        txtUserId.setMaximumSize(txtUserId.getPreferredSize());

        JButton btnRemove = new JButton("Remove");
        btnRemove.setPreferredSize(new Dimension(120, 40));
        btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblUserId);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(txtUserId);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnRemove);

        add(panel);
        setVisible(true);
    }

    private void removeUser() {
        String userId = txtUserId.getText();

        ArrayList<User> users = readUsersFromFile();
        boolean removed = users.removeIf(user -> user.getUserId().equalsIgnoreCase(userId));
        writeUsersToFile(users);

        if (removed) {
            JOptionPane.showMessageDialog(this, "User removed successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "User not found!");
        }
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
                new RemoveUser();
            }
        });
    }
}