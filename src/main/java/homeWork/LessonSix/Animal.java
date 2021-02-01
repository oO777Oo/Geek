package homeWork.LessonSix;

public abstract class Animal {
    private static int animalCounter = 0;

    public Animal() {
        animalCounter ++;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public static int getAnimalCounter() {
        return animalCounter;
    }
}
