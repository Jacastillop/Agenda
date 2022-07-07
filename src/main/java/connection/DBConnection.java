package connection;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static String DRIVER    ="com.mysql.jdbc.Driver";
    private static String USUARIO   ="root";
    private static String PASSWORD  ="M0r4l3s1027";
    private static String URL       ="jdbc:mysql://localhost:3306/contactbook?useUnicode=true&characterEncoding=UTF-8";

    private static Connection connection;
    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error en el driver" + e);
        }
    }

    private DBConnection() {
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection= DriverManager.getConnection(URL,USUARIO,PASSWORD);
            System.out.println("Conexion Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida" + e);
        }
        return connection;
    }

}
