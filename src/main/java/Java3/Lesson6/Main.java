package Java3.Lesson6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
        public static void main(String[] args) {
                DataMining dataMining = new DataMining();
                Integer[] arr = {-45, -100, -1, 100, 2, 1, 8, 5, 5, 5, 2, 2, 2, -45, -45, -45, -45};
                List<Integer> list = new ArrayList<>(Arrays.asList(arr));
                System.out.println(dataMining.extractNumbersAfterFour(list));
                System.out.println(dataMining.checkForOneAndFour(list));
        }
}
