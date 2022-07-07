package dao;

import model.ContactBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IContactBook {

    void createContact (ContactBook contactBook) throws SQLException;
    ResultSet listContact () throws SQLException;

}
