package Java3.Lesson2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConsoleChat implements Runnable {
    private final String DB = "chat";
    private final MySqlClass sqlClass = new MySqlClass(true);
    private Connection connection = null;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private User user = null;

    public void start() {
        this.run();
    }

    private void checkDB() {
        StringBuilder dbNameQuery = null;
        BufferedReader fileReader;

        try {
            String line;
            dbNameQuery = new StringBuilder();
            fileReader = new BufferedReader(new FileReader("SqlData/init.sql"));
            while ((line = fileReader.readLine()) != null) {
                dbNameQuery.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Statement statement = this.connection.createStatement();
            statement.execute("create schema if not exists " + this.DB + ";");
            connection.close(); //Close old connection
            this.connection = this.sqlClass.getConnectionToDataBase(this.DB); // Open new connection.
            statement.execute(dbNameQuery.toString());
        } catch (SQLException exception) {
            System.out.println("Problem in data base initialization");
            exception.printStackTrace();
        }
    }

    private void executionLogic() {
        System.out.println("Hi User, do you want to Log-in or you want to do a new registration?(login/registration/quit)");
        String message;
        try {
            while (!(message = this.reader.readLine()).equalsIgnoreCase("quit")) {
                if (message.equalsIgnoreCase("login")) {
                    while (true) {
                        logIn();
                        quit();
                        if (this.reader.readLine().equalsIgnoreCase("quit")) {
                            System.out.println("Goodbye");
                            break;
                        } else if (this.reader.readLine().equalsIgnoreCase("registration")) {
                            registration();
                        }
                    }
                } else if (message.equalsIgnoreCase("registration")) {
                    while (true) {
                        registration();
                        quit();
                        if (this.reader.readLine().equalsIgnoreCase("quit")) {
                            System.out.println("Goodbye");
                            break;
                        } else if (this.reader.readLine().equalsIgnoreCase("login")) {
                            logIn();
                        }
                    }
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void logIn() {
        String name;
        String password;
        System.out.println("Insert your name:\n");
        try {
            boolean logging = true;
            while (logging) {
                name = this.reader.readLine();
                System.out.println("Insert your password:\n");
                password = this.reader.readLine();
                checkNamePass(name, password);
                if (this.user != null) {
                    System.out.println("Welcome " + name);
                    logging = false;
                } else {
                    System.out.println("Your name or password doesn't exist!");
                    quit();
                    if (this.reader.readLine().equals("quit")) {
                        return;
                    }
                }
            }
        } catch (IOException ignored) {
        }
        chatOption();
    }

    private void checkNamePass(String name, String password) {
        String query = "SELECT user, password, nick_name FROM users where user = " + name + ";";
        try {
            ResultSet dataSet = this.connection.createStatement().executeQuery(query); // Getting data from db
            while (dataSet.next()) {
                String dbName = dataSet.getString("user");
                String dbPassword = dataSet.getString("password");
                String nickName = dataSet.getString("nick_name");
                if (dbName.equals(name) && dbPassword.equals(password)) {
                    this.user = new User(dbName, dbPassword, nickName);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void registration() {

    }

    private void chatOption() {
        System.out.println("Hi " + this.user.getNickName() + " You are in your chat menu:");
        System.out.println("1. Home, 2. UnreadMessages, 3. Write a Message, 4. Settings, 5. Quit");


    }

    private void settings() {

    }

    private void quit() {
        System.out.println("Do you want to quit?(quit)\n");
    }

    @Override
    public void run() {
        this.connection = sqlClass.getConnect();
        this.checkDB();
        executionLogic();
    }

}
