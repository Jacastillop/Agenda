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

        try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }
    }

    @Override
    public void updateContact(ContactBook contactBook) {
        String query = "UPDATE contactbook.contactbook SET name = ?, email = ?, address = ?, celPhone = ? WHERE (id = ?)";
        try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setString(1, contactBook.getName());
            ps.setString(2, contactBook.getEmail());
            ps.setString(3, contactBook.getAddress());
            ps.setLong(4, contactBook.getCelPhone());
            ps.setInt(5, contactBook.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }
    }

    @Override
    public ContactBook getUser(int id) {
        String query = "SELECT * FROM contactbook.contactbook WHERE id = ?";
        ResultSet rs = null;
        ContactBook contactBook = null;
        try (PreparedStatement ps = CONNECTION.prepareStatement(query)) {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                contactBook = new ContactBook();
                contactBook.setId(rs.getInt("id"));
                contactBook.setAddress(rs.getString("address"));
                contactBook.setCelPhone(rs.getLong("celPhone"));
                contactBook.setEmail(rs.getString("email"));
                contactBook.setName(rs.getString("name"));

            }

        } catch (SQLException e) {
            PRINT_STREAM.println(e);
        }
        return contactBook;
    }
}
