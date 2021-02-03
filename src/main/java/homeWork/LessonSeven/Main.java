package homeWork.LessonSeven;

public class Main {
    public static void main(String[] args) {
        Cat[] catsList = new Cat[20];
        for (int i = 0; i < catsList.length; i++) {
           catsList[i] = new Cat(i+1);
        }

        Plate plate = new Plate();
        /* while cats eat capacity is incremented (1+2+3+...)
           i used as a parameter formula 1+2+3+...n-1+n = n(n+1) / 2;
         */
        plate.fillThePlate((catsList.length * (catsList.length + 1)) / 2);
        for (Cat cat : catsList) {
            cat.eat(plate);
        }

        for (Cat cat : catsList) {
            System.out.println(cat.isHungry());
        }
    }
}
