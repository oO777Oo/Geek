package Java2.LessonTwo;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(String message) {
        super(message);
    }

    public String getMessage(int x, int y) {
        return "Convert data exception with coordinates (" + x + "," + y + ") ";
    }
}
