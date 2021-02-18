package Java2.LessonOne;

public class Human implements Actions {

    private int maxRunDistance = 30;
    private int maxJumpHeight = 3;

    public Human() {
    }

    public Human(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public int run(int distance) {
        if (this.maxRunDistance >= distance) {
            System.out.println("Human run this distance!");
            return 1;
        } else {
            System.out.println("Human don't run this distance!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }

    @Override
    public int jump(int height) {
        if (this.maxJumpHeight >= height) {
            System.out.println("Human jump this height!");
            return 1;
        } else {
            System.out.println("Human don't jump this height!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }
}
