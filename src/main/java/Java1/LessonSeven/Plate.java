package homeWork.LessonSeven;

public class Plate {

    private int essenCapacity = 0;

    public void fillThePlate(double capacity) {
        this.essenCapacity += capacity;
    }

    public int getEssenCapacity() {
        return essenCapacity;
    }

    public void setEssenCapacity(int essenCapacity) {
        this.essenCapacity = essenCapacity;
    }
}
