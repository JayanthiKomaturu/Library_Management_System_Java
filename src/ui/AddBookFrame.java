package ui;

import javax.swing.*;
import java.awt.*;
import model.Book;
import dao.BookDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBookFrame extends JFrame {

    JLabel titleLabel, authorLabel, categoryLabel, quantityLabel;

    JTextField titleField, authorField, categoryField, quantityField;

    JButton saveButton;

    public AddBookFrame() {

        setTitle("Add Book");

        setSize(500,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(50,50,100,25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(170,50,200,25);
        add(titleField);

        authorLabel = new JLabel("Author");
        authorLabel.setBounds(50,100,100,25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(170,100,200,25);
        add(authorField);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50,150,100,25);
        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(170,150,200,25);
        add(categoryField);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(50,200,100,25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(170,200,200,25);
        add(quantityField);

        saveButton = new JButton("Save Book");
        saveButton.setBounds(170,270,130,35);
        add(saveButton);



        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String category = categoryField.getText();
                int quantity = Integer.parseInt(quantityField.getText());

                Book book = new Book();

                book.setTitle(title);
                book.setAuthor(author);
                book.setCategory(category);
                book.setQuantity(quantity);

                BookDAO dao = new BookDAO();

                boolean status = dao.addBook(book);

                if (status) {

                    JOptionPane.showMessageDialog(null, "Book Added Successfully!");

                } else {

                    JOptionPane.showMessageDialog(null, "Failed to Add Book!");

                }
            }
        });
        setVisible(true);

    }

}