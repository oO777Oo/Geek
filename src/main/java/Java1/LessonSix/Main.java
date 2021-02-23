package Java1.LessonSix;

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.run(200);
        dog.swim(5);

        cat.run(150);
        cat.swim(12);

        System.out.println("We have " + Animal.getAnimalCounter() + " animals.");
        System.out.println("We have " + Cat.getCatCounter() + " cats.");
        System.out.println("We have " + Dog.getDogCounter() + " dogs.");
    }
}
