package Java2.LessonTwo;

public class Main {
    public static void main(String[] args) {
        // Working method
        foo(new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        });

        // Check size exception
        foo(new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7"},
                {"8", "9", "10", "11"},
                {"12", "13", "14", "15"}

        });

        // Check data exception
        foo(new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6a", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        });
    }

    public static boolean foo(String[][] matrix) {

        if (matrix.length != 4 || matrix[0].length != 4) {
            try {
                throw new MyArraySizeException(" My array size exception ");
            } catch (MyArraySizeException exception) {
                System.out.println(exception.getMessage());
                return false;
            }
        } else {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i].length != 4) {
                    try {
                        throw new MyArraySizeException(" My array size exception ");
                    } catch (MyArraySizeException exception) {
                        System.out.println(exception.getMessage());
                        return false;
                    }
                }
            }
        }

        int i = 0;
        while (i < matrix.length) {
            int j = 0;
            while (j < matrix.length) {
                if (!isInt(matrix[i][j])) {
                    try {
                        throw new MyArrayDataException("Data exception with coordinates (" + i + "," + j + ")");
                    } catch (MyArrayDataException exception) {
                        System.out.println(exception.getMessage(i, j));
                        return false;
                    }
                } else {
                    j++;
                }
            }
            i++;
        }
        return true;
    }

    static boolean isInt(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException er) {
            return false;
        }
    }
}
