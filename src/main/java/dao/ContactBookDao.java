package dao;

import connection.DBConnection;
import model.ContactBook;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactBookDao implements IContactBook {

    private static final Connection CONNECTION = DBConnection.getConnection();
    final PrintStream PRINT_STREAM = new PrintStream(System.out);

    @Override
    public void createContact(ContactBook contactBook) throws SQLException {
        String query = "INSERT INTO contactbook(name, email, address, celPhone) VALUES(?,?,?,?)";

        try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setString(1, contactBook.getName().toLowerCase().trim());
            ps.setString(2, contactBook.getEmail().toLowerCase().trim());
            ps.setString(3, contactBook.getAddress().toLowerCase().trim());
            ps.setLong(4, contactBook.getCelPhone());
            ps.executeUpdate();
            PRINT_STREAM.println("CONTACT CREATED SUCCESSFULLY");
        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }

    }

    @Override
    public void listContact() throws SQLException {
        String query = "SELECT * FROM contactbook";
        ResultSet rs = null;

        try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {

            PRINT_STREAM.printf("%-6s%-20s%-20s%-20s%-10s\n", "ID", "NAME", "EMAIL", "ADDRESS", "CELPHONE");
            rs = ps.executeQuery();
            while (rs.next()) {
                PRINT_STREAM.printf("%-6s%-20s%-20s%-20s%-10s\n", rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getLong("celPhone"));
            }

        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }

    }

    @Override
    public void deleteContact(long id) {
        String query = "DELETE FROM contactbook WHERE id =?";

        try(PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setLong(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }
    }
}
