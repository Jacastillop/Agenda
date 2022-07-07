import connection.DBConnection;
import model.ContactBook;
import service.ContactBookservice;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final PrintStream PRINT_STREAM = new PrintStream(System.out);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int response = 0;

        try {
            DBConnection.getConnection();
        } catch (Exception e) {
            PRINT_STREAM.println(e);
        }


        PRINT_STREAM.println("Welcome to my Contact book");
        PRINT_STREAM.println("select an option");
        do {
            PRINT_STREAM.println("\n1. Create contact");
            PRINT_STREAM.println("2. List contacts");
            PRINT_STREAM.println("3. Delete contact");
            PRINT_STREAM.println("4. Update contact");
            PRINT_STREAM.println("0. Exit\n");

            do {
                response = validateNumericValueMenu(scanner.nextLine());
                if (response < 0 || response > 4) {
                    PRINT_STREAM.println("Insert valid option");
                }

            } while (response < 0 || response > 4);
            if (response !=0) {
                response = actions(response);
            }

        } while (response != 0);

        PRINT_STREAM.println("Thanks for your visit");
    }

    private static int validateNumericValueMenu(String response) {
        try {
            return Integer.parseInt(response);
        } catch (Exception e) {
            PRINT_STREAM.println("Enter numeric value");
            return -99;
        }
    }

    private static int actions(int response) throws SQLException {
        switch (response) {
            case 1:
                ContactBookservice.createContact();
                break;
            case 2:
                ContactBookservice.listContact();
                break;
            case 3:
                ContactBookservice.deleteContact();
                break;

            default:
                ContactBook contactBook = null;
                PRINT_STREAM.println("Insert id of contact");
                do {
                    int id;
                    do {
                        id = validateNumericValueMenu(scanner.nextLine());
                    } while (id == -99);
                    contactBook = ContactBookservice.getContac(id);
                    if (contactBook == null) {
                        PRINT_STREAM.println("\nContact not found");
                        ContactBookservice.listContact();
                        PRINT_STREAM.println("\nInsert valid contact id");
                    }
                } while (contactBook == null);
                ContactBookservice.updateContact(contactBook);
                break;
        }
        return 1;
    }

}
