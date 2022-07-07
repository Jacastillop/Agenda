package service;

import dao.ContactBookDao;
import dao.IContactBook;
import model.ContactBook;

import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ContactBookservice {
    static final Scanner SCANNER = new Scanner(System.in);
    static final PrintStream PRINT_STREAM = new PrintStream(System.out);

    private static ContactBookDao contactBookDao = new ContactBookDao();
    public static void createContact() throws SQLException {
        String name;
        String email;
        String address;
        long celPhone;
        boolean isValidEmail=false;

        PRINT_STREAM.println("Insert name of contact");
        name = SCANNER.nextLine();
        PRINT_STREAM.println("Insert email of contact");
        do {
            email = SCANNER.nextLine();
            isValidEmail = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
            if (!isValidEmail) {
                PRINT_STREAM.println("Insert valid email [example@example.com]");
            }
        }while (!isValidEmail);
        PRINT_STREAM.println("Insert address of contact");
        address = SCANNER.nextLine();
        PRINT_STREAM.println("Insert cel-Phone of contact");
        do {
            celPhone = validateNumericValueMenu(SCANNER.nextLine());
        }while (celPhone ==-99);
        ContactBook contactBook = new ContactBook(name, email, address, celPhone);

        contactBookDao.createContact(contactBook);

    }

    public static void listContact() throws SQLException{
        ResultSet rs = contactBookDao.listContact();
        PRINT_STREAM.printf("%-6s%-20s%-20s%-20s%-10s\n","ID","NAME","EMAIL","ADDRESS","CELPHONE");
        while(rs.next()){
            PRINT_STREAM.printf("%-6s%-20s%-20s%-20s%-10s\n",rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("address"),rs.getLong("celPhone"));
        }
        PRINT_STREAM.println("CONTACT LIST SUCCESSFULLY");
    }



    private static long validateNumericValueMenu(String response) {
        try {
            return Long.parseLong(response);
        } catch (Exception e) {
            PRINT_STREAM.println("Enter numeric value");
            return -99;
        }
    }
}
