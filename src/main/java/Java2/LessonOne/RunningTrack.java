package Java2.LessonOne;

public class RunningTrack extends Obstacle {
    private int distance = 15;

    public RunningTrack() {

    }

    public RunningTrack(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }
}
