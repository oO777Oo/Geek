package Java1.LessonSix;

public class Cat extends Animal {
    private static int catCounter = 0;

    public Cat() {
        super();
        catCounter++;
    }

    public static int getCatCounter() {
        return catCounter;
    }

    @Override
    public void run(int distance) {
        if (distance <= 0) {
            System.out.println("Is not possible to run negative distance!");
        } else if (distance >= 200) {
            System.out.println("Distance is to big for a cat");
        } else {
            System.out.println("Cat run " + distance + " meter.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println("Cat can not swim.");
    }

}
