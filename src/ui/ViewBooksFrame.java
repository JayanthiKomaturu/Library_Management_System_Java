package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.BookDAO;
import java.sql.ResultSet;

public class ViewBooksFrame extends JFrame {

    JTable table;
    DefaultTableModel model;
    JButton updateButton;
    JButton deleteButton;

    public ViewBooksFrame() {

        setTitle("View Books");

        setSize(700,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Quantity"
        };

        model = new DefaultTableModel(columns,0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        updateButton = new JButton("Update Book");
        deleteButton = new JButton("Delete Book");

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        loadBooks();

        deleteButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(this, "Please select a book.");
                return;
            }
            int bookId = (int) model.getValueAt(selectedRow, 0);

            System.out.println("Selected Book ID = " + bookId);

            BookDAO dao = new BookDAO();

            boolean status = dao.deleteBook(bookId);

            if (status) {

                JOptionPane.showMessageDialog(this, "Book Deleted Successfully!");
                model.setRowCount(0);

                loadBooks();
            } else {

                JOptionPane.showMessageDialog(this, "Failed to Delete Book!");

            }
        });

        updateButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(this, "Please select a book.");
                return;
            }

            int bookId = (int) model.getValueAt(selectedRow, 0);
            String title = (String) model.getValueAt(selectedRow, 1);
            String author = (String) model.getValueAt(selectedRow, 2);
            String category = (String) model.getValueAt(selectedRow, 3);
            int quantity = (int) model.getValueAt(selectedRow, 4);

            new UpdateBookFrame(bookId, title, author, category, quantity);

        });

        setVisible(true);
    }

    private void loadBooks() {

        try {

            BookDAO dao = new BookDAO();

            ResultSet rs = dao.getAllBooks();
            //System.out.println(rs.getString("title"));

            while (rs.next()) {

                Object[] row = {

                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("quantity")

                };

                model.addRow(row);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}