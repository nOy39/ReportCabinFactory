package dbhandler;

import helpers.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB extends Config {

    public static Connection connection;



    //TODO переделать метод чтобы он возвращал коннект.
    public static void openConnection() {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            if (!connection.isClosed()) {
                System.out.println("Connection is OK!");
            }

        } catch (SQLException e) {

            e.printStackTrace();
            System.err.println("!!!!!NO CONNECT!!!!!");
        }
    }

}
