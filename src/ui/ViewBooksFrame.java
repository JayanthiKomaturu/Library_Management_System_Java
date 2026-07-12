package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.BookDAO;
import java.sql.ResultSet;

public class ViewBooksFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

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
        loadBooks();
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