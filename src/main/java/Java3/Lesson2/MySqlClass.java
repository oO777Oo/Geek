package Java3.Lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlClass extends SQL {
    private final String URL = "jdbc:mysql://localhost:3306/chat?";

    public MySqlClass() {
        super();
    }

    public MySqlClass(boolean status) {
        super(status);
    }
    public Connection getConnect() {
        try {
            String unicode="useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            Connection connection = DriverManager.getConnection(this.URL + unicode, this.getRoot(), this.getPassword());
            System.out.println("Connection to DataBase successful");
            return connection;
        } catch (SQLException ignored) {
            System.out.println("Connection to Database failed.");
        }
        System.out.println("Connection Fails");
        return null;
    }

    public Connection getConnectionToDataBase() {

        try (Connection connection = DriverManager.getConnection(this.URL, this.getRoot(), this.getPassword())) {
            return connection;
        } catch (SQLException ignored) {
            System.out.println("Connection to Database failed.");
        }
        System.out.println("Connection Fails");
        return null;
    }

}
