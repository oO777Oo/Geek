package Java3.Lesson2;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Chat implements Chatable, Callable {
    private static final Logger LOGGER = LogManager.getLogger(Chat.class.getName());
    private final MySqlClass database = new MySqlClass(true);
    private final Connection connection = database.getConnect();
    private final BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    private String step = null;
    private String message;
    private User user = null;

    static {
        PropertyConfigurator.configure("C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\resources\\log4j.properties");
    }

    public Chat() {
    }

    private void start() {
        /* The most important part of the program!
         * It's start the program and you need to choose LogIn or LogOut
         * after you are redirected to menu(); */
        this.test();
        LOGGER.info("User LogIn or Registration");
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
                LOGGER.error(e);
            }
        }
        try {
            System.out.println("Menu: (out/checkMessages/writeMessage/menu/nick)");
            this.step = this.consoleInput.readLine();
        } catch (IOException e) {
            LOGGER.error(e);
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
            LOGGER.info("Check if data base exist!");
            while ((line = fileReader.readLine()) != null) {
                dbNameQuery.append(line);
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        try {
            Statement statement = this.connection.createStatement();
            statement.execute(dbNameQuery.toString());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }


    private void menu() {
        /* Is Menu of the program always after some actions you are heir again!
         * If you want to exit you need to type logout! */
        LOGGER.info("User is in Menu");
        while (!this.step.equalsIgnoreCase("out")) {
            switch (this.step.toLowerCase()) {
                case "checkmessages":
                    System.out.println("Heir are your last messages:");
                    System.out.println(checkMessages(this.user.getName()).toString());
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
                LOGGER.error(exception);
            }
        }
    }

    @Override
    public void changeNick() {
        LOGGER.info("User change nickname");
        System.out.println("Do you want to change your nick name? (y/n)");
        try {
            String query = "UPDATE users SET nick_name ";
            this.step = this.consoleInput.readLine();
            if (this.step.equalsIgnoreCase("y")) {
                System.out.println("Insert new nick name: ");
                this.step = this.consoleInput.readLine();
                query += this.step + "WHERE user =" + this.user.getName();
                PreparedStatement statement = this.connection.prepareStatement(query);
                statement.execute();
            }
        } catch (IOException | SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void logIn() {
        // You come from start().
        // LogIn this method will check if you name and password are correct from db!
        LOGGER.info("User Login.");
        String name;
        String password;
        try {
            System.out.println("Write your name: ");
            name = this.consoleInput.readLine();
            System.out.println("Insert your password: ");
            password = this.consoleInput.readLine();
            String query = "SELECT user, password, nick_name FROM users where user = " + "'" + name + "'" + ";";
            LOGGER.info("User insert his data.");
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
                LOGGER.info("User LogIn fails.");
                System.out.println("Your LogIn Fail");
            }
        } catch (IOException | SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void registerUser() {
        /* Registration field:
         * You come from start()
         */
        LOGGER.info("User registration.");
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
            LOGGER.error(e);
        }
    }

    /* TO DO next homework */
    @Override
    public StringBuilder checkMessages(String name) {
        LOGGER.info("User get his messages.");
        String path = "C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\java\\Java3\\Lesson2\\ConversationHistory\\" + name + ".txt";
        StringBuilder history = new StringBuilder();
        try {
            File file = new File(path);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String message;
                int counter = 0;
                while ((message = reader.readLine()) != null && counter < 100) {
                    history.append(message);
                    counter++;
                }
            } else {
                return new StringBuilder("");
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return history;
    }

    @Override
    public void writeMessage() {
        LOGGER.info("User write message.");
        System.out.println("Insert user name whom you want to send a message?");
        StringBuilder query = new StringBuilder("SELECT user FROM users where user ='");
        String name = null;
        Writer writer = null;
        try {
            this.step = this.consoleInput.readLine();
            query.append(this.step).append("'");
            ResultSet dataSet = this.connection.createStatement().executeQuery(query.toString());
            while (dataSet.next()) {
                name = dataSet.getString("user");
            }
            if (this.step.equalsIgnoreCase(name)) {
                String path = "C:\\Users\\gaude\\Documents\\Git\\Geek\\src\\main\\java\\Java3\\Lesson2\\ConversationHistory\\" + name + ".txt";
                System.out.println("Insert your message:");
                StringBuilder history = this.checkMessages(name);
                this.message = this.consoleInput.readLine();
                String finallyMessage = this.user.getName() + ": " + this.message + "\n" + history.toString() + "\n";
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
                writer.append(finallyMessage);
            } else {
                System.out.println("This person with nick " + name + "doesn't exist!");
            }
        } catch (IOException | SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException exception) {
                LOGGER.error(exception);
            }

        }
    }

    @Override
    public Object call() {
        start();
        return null;
    }
}
