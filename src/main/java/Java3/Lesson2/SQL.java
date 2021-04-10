package Java3.Lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SQL {
    private String root;
    private String password;

    public SQL() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Insert your Database root name:");

        try {
            this.root = reader.readLine();
        } catch (IOException ignored) {
            System.out.println("Error by inserting root name!");
        }

        System.out.println("Insert your DataBase password:");

        try {
            this.password = reader.readLine();
        } catch (IOException ignored) {
            System.out.println("Error by inserting password!");
        }
    }

    public SQL(boolean type) {
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
