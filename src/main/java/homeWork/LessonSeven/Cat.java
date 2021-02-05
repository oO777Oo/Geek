package homeWork.LessonSeven;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cat {

    private int eatCapacity;
    private boolean isHungry = true;

    public Cat(int eatCapacity) {
        if (eatCapacity <= 0) {
            System.out.println("Cat can not eat negative capacity of food!");
            while (true) {
                System.out.println("Insert new value bigger than 0: ");
                try {
                    int newCapacity =Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                    if (newCapacity > 0) {
                        this.eatCapacity = newCapacity;
                        break;
                    }
                } catch (IOException | NumberFormatException e ) {

                    System.out.println("Write please a number not a character");
                }
            }
        } else {
            this.eatCapacity = eatCapacity;
        }
    }

    public void eat (Plate plate) {
        if (plate.getEssenCapacity() > this.eatCapacity) {
           plate.setEssenCapacity(plate.getEssenCapacity() - this.eatCapacity);
           this.isHungry = false;
        }
    }

    public boolean isHungry() {
        return this.isHungry;
    }

}
