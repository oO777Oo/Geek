package homeWork.LessonSix;

public class Dog extends Animal{
    private static int dogCounter = 0;

    public Dog() {
        super();
        dogCounter ++;
    }

    public static int getDogCounter() {
        return dogCounter;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println("Is not possible to run negative distance.");
        } else {
            if (distance > 500) {
                System.out.println("Dog can not run this distance.");
            } else {
                System.out.println("Dog run " + distance + " meter.");
            }
        }
    }

    @Override
    public void swim(int distance) {
        if (distance < 0) {
            System.out.println("Is not possible to swim negative distance.");
        } else {
            if (distance > 10) {
                System.out.println("Dog can not swim this distance.");
            } else {
                System.out.println("Dog swim " + distance + " meter.");
            }
        }
    }
}
