package Java3.Lesson4;

public class Main {
    public static void main(String[] args) {
        new Thread(new PrintCorrect()).start();
        new Thread(new PrintCorrect(1)).start();
        new Thread(new PrintCorrect(2)).start();
        new Thread(new PrintCorrect(3)).start();
    }
}

