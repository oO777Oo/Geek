package Java3.Lesson2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlClass extends SQL {
    private static final Logger LOGGER = LogManager.getLogger(MySqlClass.class.getName());
    private final String URL = "jdbc:mysql://localhost:3306/chat";

    static {
        PropertyConfigurator.configure("C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\resources\\log4j.properties");
    }

    public MySqlClass() {
        super();
    }

    public MySqlClass(boolean status) {
        super(status);
    }

    public Connection getConnect() {
        LOGGER.info("Get connection with Data Base.");
        try {
            String unicode="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            return DriverManager.getConnection(this.URL + unicode, this.getRoot(), this.getPassword());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        System.out.println("Connection Fails");
        return null;
    }

    public Connection getConnectionToDataBase() {
        LOGGER.info("Get connection with Data Base.");
        try (Connection connection = DriverManager.getConnection(this.URL, this.getRoot(), this.getPassword())) {
            return connection;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        System.out.println("Connection Fails");
        return null;
    }

}
