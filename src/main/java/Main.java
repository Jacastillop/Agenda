import connection.DBConnection;

import java.io.PrintStream;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        final PrintStream PRINT_STREAM = new PrintStream(System.out);
        try {
            DBConnection.getConnection();
        }catch (Exception e){
            PRINT_STREAM.println(e);
        }



    }
}
