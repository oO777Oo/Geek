package Java2.LessonThree;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<String>(
                Arrays.asList(
                        "cat", "dog", "house", "table",
                        "cat", "dog", "house", "table",
                        "cat", "dog", "house", "table",
                        "cat", "dog", "house", "table",
                        "cat", "dog", "house", "table")
        );
        printArraysValuesWithNoRepetition(strings);

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Vlad", 1234L);
        phoneBook.add("Vlad", "12345");
        phoneBook.add("Nastea", 1234567);

        phoneBook.get("Vlad");
    }

    public static void printArraysValuesWithNoRepetition(List<String> arr) {
        Map<String, Integer> data = new HashMap<String, Integer>();
        for (String str : arr) {
            if (data.containsKey(str)) {
                data.put(str, data.get(str) + 1);
            } else {
                data.put(str, 1);
            }
        }

        Iterator<Map.Entry<String, Integer>> it = data.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }
}
