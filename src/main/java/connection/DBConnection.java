package connection;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private static String DRIVER    ="com.mysql.jdbc.Driver";
    private static String USUARIO   ="root";
    private static String PASSWORD  ="1234";
    private static String URL       ="jdbc:mysql://localhost:3306/questionchallenge?useUnicode=true&characterEncoding=UTF-8";

    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error en el driver" + e);
        }
    }
    public Connection getConnection(){
        Connection con = null;
        try {
            con= DriverManager.getConnection(URL,USUARIO,PASSWORD);
            System.out.println("Conexion Exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida" + e);
        }
        return con;
    }

}
