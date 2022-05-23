package sample.oop;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecction {
    public Connection conn;

    public Connection getConnection(){


        String username = "root";
        String password = "";
        String dbUrl = "jdbc:mysql://localhost/databaseconnect";

        try{
            conn = (Connection) DriverManager.getConnection(dbUrl, username, password);
        } catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return conn;
    }
}
