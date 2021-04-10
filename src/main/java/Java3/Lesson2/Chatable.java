package Java3.Lesson2;

public interface Chatable extends Runnable {
    void logIn();
    void registerUser();
    StringBuilder checkMessages(String name);
    void writeMessage();
    void changeNick();
    void test();
}
