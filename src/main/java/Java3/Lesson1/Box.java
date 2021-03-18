package Java3.Lesson1;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    private final List<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public void addFruit(T fruit) {
        this.fruits.add(fruit);
    }

    private List<T> getFruitsList() {
        return this.fruits;
    }

    public void addFruit(Box<T> box) {
        for (T fruit : box.getFruitsList()) {
            this.addFruit(fruit);
        }
    }

    private double getFullWeight() {
        if (this.fruits.size() == 0) {
            return 0.0;
        }
        return fruits.get(0).getWeight() * fruits.size();
    }

    public boolean compareBoxes(Box<?> box) {
        return this.getFullWeight() == box.getFullWeight();
    }

}
