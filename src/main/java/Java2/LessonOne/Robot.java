package Java2.LessonOne;

public class Robot implements Actions {
    private int maxRunDistance = 500;
    private int maxHeight = 200;

    public Robot() {}

    public Robot(int maxRunDistance, int maxHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxHeight = maxHeight;
    }


    @Override
    public int run(int distance) {
        if (this.maxRunDistance >= distance) {
            System.out.println("Robot run this distance!");
            return 1;
        } else {
            System.out.println("Robot didn't run this distance!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }

    @Override
    public int jump(int height) {
        if(this.maxHeight >= height) {
            System.out.println("Robot jump this height!");
            return 1;
        } else {
            System.out.println("Robot didn't jump this height!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }
}
