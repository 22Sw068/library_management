import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] btn_numbers = new JButton[10];
    private JButton[] fun_button = new JButton[9];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, delButton, clrButton, negButton;
    private JPanel panel;

    private Font myFont = new Font("Arial", Font.BOLD, 30);

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(420, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");

        fun_button[0] = addButton;
        fun_button[1] = subButton;
        fun_button[2] = mulButton;
        fun_button[3] = divButton;
        fun_button[4] = decButton;
        fun_button[5] = equButton;
        fun_button[6] = delButton;
        fun_button[7] = clrButton;
        fun_button[8] = negButton;

        for (int i = 0; i < 9; i++) {
            fun_button[i].addActionListener(this);
            fun_button[i].setFont(myFont);
            fun_button[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            btn_numbers[i] = new JButton(String.valueOf(i));
            btn_numbers[i].addActionListener(this);
            btn_numbers[i].setFont(myFont);
            btn_numbers[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(btn_numbers[1]);
        panel.add(btn_numbers[2]);
        panel.add(btn_numbers[3]);
        panel.add(addButton);
        panel.add(btn_numbers[4]);
        panel.add(btn_numbers[5]);
        panel.add(btn_numbers[6]);
        panel.add(subButton);
        panel.add(btn_numbers[7]);
        panel.add(btn_numbers[8]);
        panel.add(btn_numbers[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(btn_numbers[0]);
        panel.add(equButton);
        panel.add(divButton);

        add(panel);
        add(negButton);
        add(delButton);
        add(clrButton);
        add(textField);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == btn_numbers[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
