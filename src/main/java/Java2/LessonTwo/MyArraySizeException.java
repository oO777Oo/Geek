package Java2.LessonTwo;

public class MyArraySizeException extends Exception {

    public MyArraySizeException(String message) {
        super(message);
    }

    public String getMessage() {
        return "My array size exception";
    }
}
