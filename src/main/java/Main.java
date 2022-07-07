import connection.DBConnection;
import service.ContactBookservice;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static final PrintStream PRINT_STREAM = new PrintStream(System.out);

    public static void main(String[] args) throws SQLException {
        int response = 0;
        Scanner scanner = new Scanner(System.in);
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
            PRINT_STREAM.println("0. Exit");

            do {
                response = validateNumericValueMenu(scanner.nextLine());
                if (response < 0 || response > 4) {
                    PRINT_STREAM.println("Insert valid option");
                }
            } while (response < 0 || response > 4);
            actions(response);
        } while (response != 0);


    }

    private static int validateNumericValueMenu(String response) {
        try {
            return Integer.parseInt(response);
        } catch (Exception e) {
            PRINT_STREAM.println("Enter numeric value");
            return -99;
        }
    }

    private static void actions(int response) throws SQLException {
        switch (response) {
            case 1:
                ContactBookservice.createContact();
                break;
        }
    }

}
