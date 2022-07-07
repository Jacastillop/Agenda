import connection.DBConnection;

public class Main {
    public static void main(String[] args) {
        DBConnection myConnection = new DBConnection();
        myConnection.getConnection();
    }
}
