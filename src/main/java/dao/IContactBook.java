package dao;

import model.ContactBook;

import java.sql.SQLException;

public interface IContactBook {

    void createContact (ContactBook contactBook) throws SQLException;
    void listContact () throws SQLException;

    void deleteContact (long id);

    void updateContact(ContactBook contactBook);

    ContactBook getUser(int id);

}
