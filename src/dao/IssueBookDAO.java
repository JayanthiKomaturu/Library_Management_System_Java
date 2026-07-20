package dao;

import database.DBConnection;
import model.IssuedBook;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class IssueBookDAO {

    public boolean issueBook(IssuedBook book) {

        String query = "INSERT INTO issued_books(book_id, student_name, student_id, issue_date, return_date) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getStudentName());
            ps.setString(3, book.getStudentId());
            ps.setString(4, book.getIssueDate());
            ps.setString(5, book.getReturnDate());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }
}