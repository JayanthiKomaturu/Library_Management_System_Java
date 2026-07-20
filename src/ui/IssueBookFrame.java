package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.BookDAO;
import dao.IssueBookDAO;
import model.IssuedBook;
import java.sql.ResultSet;

public class IssueBookFrame extends JFrame {

    JLabel titleLabel;
    JTextField titleField;
    JButton issueButton;

    JLabel studentNameLabel;
    JLabel studentIdLabel;
    JLabel issueDateLabel;

    JTextField studentNameField;
    JTextField studentIdField;
    JTextField issueDateField;
    JButton searchButton;

    JTable table;
    DefaultTableModel model;

    public IssueBookFrame() {

        setTitle("Issue Book");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(null);

        titleLabel = new JLabel("Enter Book Title");
        titleLabel.setBounds(30,30,120,25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(160,30,200,25);
        add(titleField);

        searchButton = new JButton("Search");
        searchButton.setBounds(370,30,90,25);
        add(searchButton);

        issueButton = new JButton("Issue Book");
        issueButton.setBounds(170,200,130,35);
        add(issueButton);

        studentNameLabel = new JLabel("Student Name");
        studentNameLabel.setBounds(30,70,120,25);
        add(studentNameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(170,70,220,25);
        add(studentNameField);

        studentIdLabel = new JLabel("Student ID");
        studentIdLabel.setBounds(30,110,120,25);
        add(studentIdLabel);

        studentIdField = new JTextField();
        studentIdField.setBounds(170,110,220,25);
        add(studentIdField);

        issueDateLabel = new JLabel("Issue Date");
        issueDateLabel.setBounds(30,150,120,25);
        add(issueDateLabel);

        issueDateField = new JTextField("2026-07-20");
        issueDateField.setBounds(170,150,220,25);
        add(issueDateField);

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
        scrollPane.setBounds(500,300,450,120);

        add(scrollPane);
        loadBooks();

        searchButton.addActionListener(e -> {

            String title = titleField.getText();

            if(title.isEmpty()){

                JOptionPane.showMessageDialog(this, "Enter Book Title");
                return;

            }

            BookDAO dao = new BookDAO();

            ResultSet rs = dao.searchBookByTitle(title);

            model.setRowCount(0);

            try {

                while (rs.next()) {
                    System.out.println("Book Found: " + rs.getString("title"));
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

        issueButton.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(this, "Please select a book.");
                return;
            }

            int bookId = (int) model.getValueAt(selectedRow, 0);

            IssuedBook issuedBook = new IssuedBook();

            issuedBook.setBookId(bookId);
            issuedBook.setStudentName(studentNameField.getText());
            issuedBook.setStudentId(studentIdField.getText());
            issuedBook.setIssueDate(issueDateField.getText());

            issuedBook.setReturnDate("");

            IssueBookDAO dao = new IssueBookDAO();

            boolean status = dao.issueBook(issuedBook);
            if (status) {
                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Issue Failed!");
            }
        });

        setVisible(true);
    }

    private void loadBooks() {

        try {

            BookDAO dao = new BookDAO();

            ResultSet rs = dao.getAllBooks();

            model.setRowCount(0);

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