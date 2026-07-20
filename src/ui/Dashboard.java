package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame {

    JButton addBookButton;
    JButton viewBookButton;
    JButton logoutButton;
    JButton searchBookButton;
    JButton issueBookButton;

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

        searchBookButton = new JButton("Search Book");
        searchBookButton.setBounds(100, 180, 200, 40);
        add(searchBookButton);


        issueBookButton = new JButton("Issue Book");
        issueBookButton.setBounds(320,180,180,40);
        add(issueBookButton);



        addBookButton.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                new AddBookFrame();

            }
        });

        viewBookButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new ViewBooksFrame();

            }
        });

        searchBookButton.addActionListener(e -> {
            new SearchBookFrame();
        });

        issueBookButton.addActionListener(e -> {

            new IssueBookFrame();

        });
        setVisible(true);
    }
}