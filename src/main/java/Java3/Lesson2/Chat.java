package Java3.Lesson2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Chat implements Chatable, Runnable {
    private final MySqlClass database = new MySqlClass(true);
    private final Connection connection = this.database.getConnect();
    private final BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
    private String step = null;
    private User user = null;

    public Chat() { }

    private void start() {
        /* The most important part of the program!
        * It's start the program and you need to choose LogIn or LogOut
        * after you are redirected to menu(); */
        this.test();
        System.out.println("Hi user!(login/register)");
        while (this.user == null) {
            try {
                this.step = this.consoleInput.readLine();
                if (this.step.equalsIgnoreCase("login")) {
                    logIn();
                } else if (this.step.equalsIgnoreCase("register")) {
                    registerUser();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("Menu: (out/checkMessages/writeMessage/menu/nick)");
            this.step = this.consoleInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menu();
    }

    @Override
    public void test() {
        StringBuilder dbNameQuery = null;
        BufferedReader fileReader;
        try {
            String line;
            dbNameQuery = new StringBuilder();
            fileReader = new BufferedReader(new FileReader("C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\java\\Java3\\Lesson2\\SqlData\\init.sql"));
            while ((line = fileReader.readLine()) != null) {
                dbNameQuery.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(dbNameQuery.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void menu() {
        /* Is Menu of the program always after some actions you are heir again!
        * If you want to exit you need to type logout! */
        while(!this.step.equalsIgnoreCase("out")) {
            switch (this.step.toLowerCase()) {
                case "checkmessages":
                    checkMessages();
                    break;
                case "writemessage":
                    writeMessage();
                    break;
                case "nick":
                    changeNick();
                    break;
            }
            System.out.println("Menu: (out/checkMessages/writeMessage/menu/nick)");
            try {
                this.step = this.consoleInput.readLine();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void changeNick() {

    }

    @Override
    public void logIn() {
        // You come from start().
        // LogIn this method will check if you name and password are correct from db!
        String name;
        String password;
        try {
            System.out.println("Write your name: ");
            name = this.consoleInput.readLine();
            System.out.println("Insert your password: ");
            password = this.consoleInput.readLine();
            String query = "SELECT user, password, nick_name FROM users where user = " + "'" + name + "'" + ";";
            ResultSet dataSet = this.connection.createStatement().executeQuery(query);
            String dbName = "";
            String dbPassword = "";
            String nickName = "";
            while (dataSet.next()) {
                dbName = dataSet.getString("user");
                dbPassword = dataSet.getString("password");
                nickName = dataSet.getString("nick_name");
            }
            if (dbName.equals(name) && dbPassword.equals(password)) {
                this.user = new User(dbName, dbPassword, nickName);
            } else {
                System.out.println("Your LogIn Fail");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerUser() {
        /* Registration field:
         * You come from start()
         */
        System.out.println("Welcome to registration!\n");
        String query = "SELECT user FROM users where user = ";
        String registrationQuery = "INSERT INTO users (user, password, nick_name) VALUES (?,?,?)";
        // Init possible (name, pass, nickname);
        String newName;
        String newPass;
        String repNewPass;
        String nickName;
        try {
            while (true) { // repeat till logging name doesn't exist in db
                System.out.println("Insert your logging name: ");
                newName = this.consoleInput.readLine();
                ResultSet dataSet = this.connection.createStatement().executeQuery(query + "'" + newName + "'" + ";");
                String dbName = "";
                while (dataSet.next()) {
                    dbName = dataSet.getString("user");
                }
                if (dbName.equalsIgnoreCase(newName)) {
                    System.out.println("This logging name exist");
                } else {
                    break;
                }
            }

            do { // repeat enter your password till first and second passwords are not the same!
                System.out.println("Insert your password: ");
                newPass = this.consoleInput.readLine();
                System.out.println("\n Repeat your password again: ");
                repNewPass = this.consoleInput.readLine();
            } while (!newPass.equals(repNewPass));
            System.out.println("Insert a nick name: ");
            nickName = this.consoleInput.readLine();

            /* Insert new user in data base */
            PreparedStatement statement = this.connection.prepareStatement(registrationQuery);
            statement.setString(1, newName);
            statement.setString(2, newPass);
            statement.setString(3, nickName);
            statement.execute();
            this.user = new User(newName, newPass, nickName);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkMessages() {

    }

    @Override
    public void writeMessage() {

    }


    @Override
    public void run() {
        start();
    }
}
