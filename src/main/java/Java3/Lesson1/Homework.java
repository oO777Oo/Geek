package Java3.Lesson1;

import java.util.ArrayList;
import java.util.List;

public class Homework {
    public static void main(String[] args) {
        // First Exercise:
        String[] x = new String[]{"a","b","c","d"};
        printArray(x);
        // Correct swap:
        swapElementsWithIndex(x, 0,2);
        printArray(x);

        // case where y is bigger as arr.length
        swapElementsWithIndex(x, 0, 6);
        printArray(x);
        /* ================================================================ */

        // Second Exercise:
        Integer[] intArr = new Integer[20];
        List<String> arr = transformArrToArrayList(x);
        List<Integer> arrInt = transformArrToArrayList(intArr);
        System.out.println(arr.getClass());
        System.out.println(arrInt.getClass());
    }

    public static <T> void printArray(T[] arr) {
        for (T item: arr) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    public static <T> void swapElementsWithIndex(T[] arr, int x, int y ) {
        if (arr.length <= y || x < 0) {
            return;
        }
        T item = arr[x];
        arr[x] = arr[y];
        arr[y] = item;
    }

    public static <T> ArrayList<T> transformArrToArrayList(T[] arr) {
        ArrayList<T> newArr = new ArrayList<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            newArr.add(i, arr[i]);
        }
        return newArr;
    }

}
