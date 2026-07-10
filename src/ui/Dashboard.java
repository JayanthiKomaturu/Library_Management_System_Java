package ui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    JButton addBookButton;
    JButton viewBookButton;
    JButton logoutButton;

    public Dashboard() {

        setTitle("Library Management System - Dashboard");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel title = new JLabel("Library Management Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(120,20,350,30);
        add(title);

        addBookButton = new JButton("Add Book");
        addBookButton.setBounds(80,100,180,40);
        add(addBookButton);

        viewBookButton = new JButton("View Books");
        viewBookButton.setBounds(320,100,180,40);
        add(viewBookButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(200,280,180,40);
        add(logoutButton);


        addBookButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                new AddBookFrame();

            }
        });
        setVisible(true);
    }
}