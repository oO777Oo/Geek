package Java2.LessonThree;

import java.util.*;

public class PhoneBook {
    Map<String, List<String>> data = new HashMap<>();


    public void add(String name, Integer phoneNumber) {
        this.add(name, String.valueOf(phoneNumber));
    }

    public void add(String name, Long phoneNumber) {
        this.add(name, String.valueOf(phoneNumber));
    }

    public void add(String name, String phoneNumber) {
        if (this.data.containsKey(name)) {
            List<String> data = this.data.get(name);
            data.add(phoneNumber);
            this.data.put(name, data);
        } else {
            this.data.put(name, new ArrayList<>(Collections.singletonList(phoneNumber)));
        }
    }

    public void get(String name) {
        if (this.data.containsKey(name)) {
            List<String> data = this.data.get(name);
            if (data.size() == 1) {
                System.out.println(data.get(0));
            } else {
                System.out.print("{");
                for(String x : data) {
                    System.out.print(" " + x);
                }
                System.out.print(" }");
            }
        } else {
            System.out.println("This name doesn't exist!");
        }
    }

}
