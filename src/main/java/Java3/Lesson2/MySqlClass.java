package Java3.Lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlClass extends SQL {

    private final String DRIVERS = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/";

    public MySqlClass() {
        super();
    }

    public MySqlClass(boolean type) {
        super(type);
    }

    public Connection getConnect() {
        try {
            Class.forName(this.DRIVERS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error 404 on class com.mysql.jdbc not found");
        }

        try (Connection connection = DriverManager.getConnection(this.URL, this.getRoot(), this.getPassword())) {
            return connection;
        } catch (SQLException ignored) {
            System.out.println("Connection to Database failed.");
        }

        System.out.println("Connection Fails");
        return null;
    }

    public Connection getConnectionToDataBase(String database) {
        try {
            Class.forName(this.DRIVERS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error 404 on class com.mysql.jdbc not found");
        }

        try (Connection connection = DriverManager.getConnection(this.URL + database, this.getRoot(), this.getPassword())) {
            return connection;
        } catch (SQLException ignored) {
            System.out.println("Connection to Database failed.");
        }

        System.out.println("Connection Fails");
        return null;
    }

}
