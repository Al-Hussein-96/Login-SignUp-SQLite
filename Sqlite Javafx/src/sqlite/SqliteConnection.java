package sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqliteConnection {
    public static Connection Connector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:userdb.sqlite");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}




















