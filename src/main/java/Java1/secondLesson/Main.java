package Java1.secondLesson;

public class Main {
    public static void main(String[] args) {
        // Exercise 1
        short[] arr = new short[]{1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }

        // Exercise 2
        int[] arr2 = new int[8];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i * 3;
        }

        // Exercise 3
        int[] arr3 = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) {
                arr3[i] = arr3[i] * 2;
            }
        }

        // Exercise 4
        int[][] matrix = new int[9][9];
        for (int i = 0, j = matrix.length - 1; i < matrix.length; i++, j--) {
            matrix[i][i] = 1;
            matrix[i][j] = 1;
        }

        // Exercise 5
        findMaxAndMin(new int[]{10, 20, 30, 50, 60});

        // Exercise 6
        System.out.println(checkBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1}));

        // Exercise 7
        transportArrayItem(new int[]{1, 2, 3, 4}, -2);
    }

    // Exercise 5
    static void findMaxAndMin(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            } else if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println("Max is " + max + " and min is " + min);
    }

    // Exercise 6
    static boolean checkBalance(int[] arr) {
        int left = arr[0]; // init first item
        int right = arr[arr.length - 1]; // init last item

        int start = 1; // flag for left array side
        int end = arr.length - 2; // flag for right array side

        while (start <= end) {
            if (right < left) {
                right += arr[end];
                end--;
            } else {
                left += arr[start];
                start++;
            }
        }
        return left == right;
    }

    // Exercise 7
    static void transportArrayItem(int[] array, int n) {
        int intermediate = array[0];
        int flag = 0;
        boolean odd = false;
        int arrLength = array.length;

        if (n % 2 == 0 && arrLength % 2 == 0) {
            odd = true;
        }

        for (int i = 0; i < arrLength; i++) {
            int position = recastTransportNumber(arrLength, flag + n);
            int newValue = array[position];
            array[position] = intermediate;
            if (odd && i % 2 != 0) {
                flag = position + 1;
                intermediate = array[flag];
            } else {
                flag = position;
                intermediate = newValue;
            }
        }
    }

    private static int recastTransportNumber(int arrLength, int number) {
        if (number < 0) {
            while (number <= 0) {
                number += arrLength;
            }
        } else {
            while (number >= arrLength) {
                number -= arrLength;
            }
        }
        return number;
    }

}
