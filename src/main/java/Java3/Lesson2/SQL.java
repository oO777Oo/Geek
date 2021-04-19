package Java3.Lesson2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQL {
    private static final Logger LOGGER = LogManager.getLogger(SQL.class.getName());
    private String root;
    private String password;

    static {
        PropertyConfigurator.configure("C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\resources\\log4j.properties");
    }

    public SQL() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Insert your Database root name:");

        try {
            this.root = reader.readLine();
        } catch (IOException e) {
            LOGGER.error(e);
        }

        System.out.println("Insert your DataBase password:");

        try {
            this.password = reader.readLine();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    public SQL(boolean type) {
        LOGGER.info("DataBase default LogIn");
        if (type) {
            this.root = "root";
            this.password = "bin";
        }
    }
    public String getRoot() {
        return this.root;
    }
    public String getPassword() {
        return this.password;
    }
}
