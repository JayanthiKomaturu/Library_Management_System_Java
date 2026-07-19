package ui;

import javax.swing.*;
import java.awt.*;
import model.Book;
import dao.BookDAO;
public class UpdateBookFrame extends JFrame {

    private int bookId;
    JLabel titleLabel;
    JLabel authorLabel;
    JLabel categoryLabel;
    JLabel quantityLabel;

    JTextField titleField;
    JTextField authorField;
    JTextField categoryField;
    JTextField quantityField;

    JButton updateButton;

    public UpdateBookFrame(int bookId,
                           String title,
                           String author,
                           String category,
                           int quantity) {
        this.bookId = bookId;
        setTitle("Update Book");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        titleLabel = new JLabel("Title");
        titleLabel.setBounds(50, 50, 100, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(170, 50, 200, 25);
        titleField.setText(title);
        add(titleField);

        authorLabel = new JLabel("Author");
        authorLabel.setBounds(50, 100, 100, 25);
        add(authorLabel);

        authorField = new JTextField();
        authorField.setBounds(170, 100, 200, 25);
        authorField.setText(author);
        add(authorField);

        categoryLabel = new JLabel("Category");
        categoryLabel.setBounds(50, 150, 100, 25);

        add(categoryLabel);

        categoryField = new JTextField();
        categoryField.setBounds(170, 150, 200, 25);
        categoryField.setText(category);
        add(categoryField);

        quantityLabel = new JLabel("Quantity");
        quantityLabel.setBounds(50, 200, 100, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(170, 200, 200, 25);
        quantityField.setText(String.valueOf(quantity));
        add(quantityField);

        updateButton = new JButton("Update Book");
        updateButton.setBounds(170, 270, 140, 35);
        add(updateButton);


        updateButton.addActionListener(e -> {

            String newTitle = titleField.getText();
            String newAuthor = authorField.getText();
            String newCategory = categoryField.getText();
            int newQuantity = Integer.parseInt(quantityField.getText());

            Book book = new Book();

            book.setBookId(bookId);
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            book.setCategory(newCategory);
            book.setQuantity(newQuantity);

            BookDAO dao = new BookDAO();

            boolean status = dao.updateBook(book);

            if (status) {

                JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
                dispose();

            } else {

                JOptionPane.showMessageDialog(this, "Failed to Update Book!");
            }

        });
        setVisible(true);
    }



}