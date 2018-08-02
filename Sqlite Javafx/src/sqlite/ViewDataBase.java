/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Al-Hussein
 */
public class ViewDataBase {

    Connection connection;

    public ViewDataBase() {
        connection = SqliteConnection.Connector();
        if (connection == null) {
            System.out.println("Error null,Connection Not Successful");
            System.exit(1);
        }
    }

    public List<User> getDataBase() throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "select * from user";
        List<User> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String pass = resultSet.getString(3);
                
                System.out.println(id + " : " + name + " : " + pass);

                list.add(new User(id, name, pass));

            }
            return list;

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            preparedStatement.close();
            resultSet.close();
        }
        return null;

    }

}
