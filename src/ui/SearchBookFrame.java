package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import dao.BookDAO;

public class SearchBookFrame extends JFrame {

    JLabel titleLabel;
    JTextField searchField;
    JButton searchButton;
    JTable table;
    DefaultTableModel model;

    public SearchBookFrame() {

        setTitle("Search Book");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        titleLabel = new JLabel("Enter Book Title");
        titleLabel.setBounds(40,50,120,25);
        add(titleLabel);

        searchField = new JTextField();
        searchField.setBounds(170,50,220,25);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(170,120,100,35);
        add(searchButton);

        String[] columns = {
                "Book ID",
                "Title",
                "Author",
                "Category",
                "Quantity"
        };

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,170,450,80);

        add(scrollPane);

        searchButton.addActionListener(e -> {

            model.setRowCount(0);

            String title = searchField.getText();

            BookDAO dao = new BookDAO();

            try {

                ResultSet rs = dao.searchBook(title);

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

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        });

        setVisible(true);
    }
}