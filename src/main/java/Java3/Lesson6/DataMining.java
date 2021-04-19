package Java3.Lesson6;

import java.util.*;
import java.util.concurrent.Callable;

public class DataMining {

    public DataMining(){}

    public List<Integer> extractNumbersAfterFour(List<Integer> list) {
        Deque<Integer> deque = new LinkedList<>();
        boolean findFour = false;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i) == 4) {
                findFour = true;
                break;
            }
            deque.add(list.get(i));
        }
        if (!findFour) {
           throw  new RuntimeException();
        }
        List<Integer> arr = new ArrayList<>(deque);
        Collections.reverse(arr);
        return  arr;
    }

    public Boolean checkForOneAndFour(List<Integer> list) {
        Collections.sort(list);
        DoubleSearch doubleSearchOne = new DoubleSearch(1, list);
        DoubleSearch doubleSearchFour = new DoubleSearch(4, list);
        return doubleSearchFour.call() && doubleSearchOne.call();
    }
    private Boolean binarySearch(int number, List<Integer> list) {
        // Implement binarySearch with repetitions.
        int start = 0;
        int end = list.size();
        while (start + 1 < end) {
            int mid = (end + start) / 2;
            if (number == list.get(mid)) {
                return true;
            }
            if (number > list.get(mid)) {
                while (list.get(mid).equals(list.get(mid + 1))) {
                    mid++;
                }
                start = mid;
            } else {
                while (list.get(mid).equals(list.get(mid - 1))) {
                    mid--;
                }
                end = mid;
            }
        }
        return false;
    }

    // Inter class helper for threading
    private class DoubleSearch implements Callable<Boolean> {
        int searchedNumber;
        List<Integer> list;

        public DoubleSearch(int number, List<Integer> list) {
            this.searchedNumber = number;
            this.list = list;
        }

        @Override
        public Boolean call() {
            return binarySearch(this.searchedNumber, this.list);
        }
    }
}
