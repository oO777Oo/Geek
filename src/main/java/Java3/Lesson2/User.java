package Java3.Lesson2;

public class User {
    private final String name;
    private final String password;
    private final String nickName;

    public  User(String name, String password, String nickName) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }
}
