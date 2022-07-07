package service;

import dao.ContactBookDao;
import model.ContactBook;

import java.io.PrintStream;
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
        boolean isValidEmail = false;

        PRINT_STREAM.println("Insert name of contact");
        name = SCANNER.nextLine();
        PRINT_STREAM.println("Insert email of contact");
        do {
            email = SCANNER.nextLine();
            isValidEmail = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
            if (!isValidEmail) {
                PRINT_STREAM.println("Insert valid email [example@example.com]");
            }
        } while (!isValidEmail);
        PRINT_STREAM.println("Insert address of contact");
        address = SCANNER.nextLine();
        PRINT_STREAM.println("Insert cel-Phone of contact");
        do {
            celPhone = validateNumericCelPhone(SCANNER.nextLine());
        } while (celPhone == -99);
        ContactBook contactBook = new ContactBook(name, email, address, celPhone);

        contactBookDao.createContact(contactBook);

    }

    public static void listContact() throws SQLException {
        contactBookDao.listContact();

    }

    public static void deleteContact() {
        long id;
        PRINT_STREAM.println("Insert id of contact");

        do {
            id = validateNumericCelPhone(SCANNER.nextLine());
        } while (id == -99);
        contactBookDao.deleteContact(id);
        PRINT_STREAM.println("Contact was delete successfully");
    }

    public static void updateContact(ContactBook contactBook) {
        int response = 0;
        PRINT_STREAM.println("\n1. Select one option");
        do {
            PRINT_STREAM.println("\n1. Update name");
            PRINT_STREAM.println("2. Update email");
            PRINT_STREAM.println("3. Update cel-phone");
            PRINT_STREAM.println("4. Update address");
            PRINT_STREAM.println("5. Update all\n");

            do {
                response = validateNumericValue(SCANNER.nextLine());
                if (response < 0 || response > 5) {
                    PRINT_STREAM.println("Insert valid option");
                }

            } while (response < 0 || response > 5);


        } while (response == -99);

        contactBookDao.updateContact(menuUpdate(response,contactBook));
        PRINT_STREAM.println("Contact was update successfully");

    }

    public static ContactBook getContac(int id) {
        return contactBookDao.getUser(id);
    }


    private static long validateNumericCelPhone(String response) {
        try {
            return Long.parseLong(response);
        } catch (Exception e) {
            PRINT_STREAM.println("Enter numeric value");
            return -99;
        }
    }

    private static int validateNumericValue(String response) {
        try {
            return Integer.parseInt(response);
        } catch (Exception e) {
            PRINT_STREAM.println("Enter numeric value");
            return -99;
        }
    }

    private static ContactBook menuUpdate(int response,ContactBook contactBook){
        String name;
        String email;
        String address;
        long celPhone;
        boolean isValidEmail = false;
        switch (response) {
            case 1:
                PRINT_STREAM.println("Insert name of contact");
                contactBook.setName(SCANNER.nextLine());
                break;
            case 2:
                PRINT_STREAM.println("Insert email of contact");
                do {
                    email = SCANNER.nextLine();
                    isValidEmail = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
                    if (!isValidEmail) {
                        PRINT_STREAM.println("Insert valid email [example@example.com]");
                    }
                } while (!isValidEmail);
                contactBook.setEmail(email);
                break;
            case 3:
                PRINT_STREAM.println("Insert cel-Phone of contact");
                do {
                    celPhone = validateNumericCelPhone(SCANNER.nextLine());
                } while (celPhone == -99);
                contactBook.setCelPhone(celPhone);
                break;
            case 4:
                PRINT_STREAM.println("Insert address of contact");
                address = SCANNER.nextLine();
                contactBook.setAddress(address);break;
            default:

                PRINT_STREAM.println("Insert name of contact");
                name = SCANNER.nextLine();
                contactBook.setName(name);
                PRINT_STREAM.println("Insert email of contact");
                do {
                    email = SCANNER.nextLine();
                    isValidEmail = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
                    if (!isValidEmail) {
                        PRINT_STREAM.println("Insert valid email [example@example.com]");
                    }
                } while (!isValidEmail);
                contactBook.setEmail(email);
                PRINT_STREAM.println("Insert address of contact");
                address = SCANNER.nextLine();
                contactBook.setAddress(address);
                PRINT_STREAM.println("Insert cel-Phone of contact");
                do {
                    celPhone = validateNumericCelPhone(SCANNER.nextLine());
                } while (celPhone == -99);
                contactBook.setCelPhone(celPhone);
                break;
        }

        return contactBook;
    }
}
