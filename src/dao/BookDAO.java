package dao;

import database.DBConnection;
import model.Book;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class BookDAO {

    public boolean addBook(Book book) {

        String query = "INSERT INTO books(title, author, category, quantity) VALUES (?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());

            int rows = ps.executeUpdate();
            System.out.println("Rows inserted = " + rows);

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error while adding book:");
            e.printStackTrace();
            return false;
        }
    }
    public void viewBooks() {

        String query = "SELECT * FROM books";

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== BOOK LIST =====");

            while (rs.next()) {

                System.out.println("Book ID   : " + rs.getInt("book_id"));
                System.out.println("Title     : " + rs.getString("title"));
                System.out.println("Author    : " + rs.getString("author"));
                System.out.println("Category  : " + rs.getString("category"));
                System.out.println("Quantity  : " + rs.getInt("quantity"));
                System.out.println("-----------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllBooks() {

        String query = "SELECT * FROM books";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteBook(int bookId) {

        String query = "DELETE FROM books WHERE book_id = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
    public boolean updateBook(Book book) {

        String query = "UPDATE books SET title=?, author=?, category=?, quantity=? WHERE book_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getCategory());
            ps.setInt(4, book.getQuantity());
            ps.setInt(5, book.getBookId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}