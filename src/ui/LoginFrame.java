package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    JLabel titleLabel;
    JLabel userLabel;
    JLabel passwordLabel;

    JTextField usernameField;
    JPasswordField passwordField;

    JButton loginButton;

    public LoginFrame() {

        setTitle("Library Management System");

        setSize(500,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        titleLabel = new JLabel("Library Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(90,20,350,40);
        add(titleLabel);

        userLabel = new JLabel("Username");
        userLabel.setBounds(70,90,100,25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(180,90,180,25);
        add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70,140,100,25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(180,140,180,25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(180,200,100,35);
        add(loginButton);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("admin123")) {

                    JOptionPane.showMessageDialog(null, "Login Successful!");

                    new Dashboard();

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");

                }
            }
        });

        setVisible(true);
    }
}