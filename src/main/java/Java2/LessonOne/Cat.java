package Java2.LessonOne;

public class Cat implements Actions{

    private int maxRunDistance = 20;
    private int maxJumpHeight = 50;

    public Cat() {

    }

    public Cat(int maxRunDistance, int maxJumpHeight) {
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }

    @Override
    public int run(int distance) {
        if(this.maxRunDistance >= distance) {
            System.out.println("Cat run this distance!");
            return 1;
        } else {
            System.out.println("Cat didn't run this distance!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }

    @Override
    public int jump(int height) {
        if(this.maxJumpHeight >= height) {
            System.out.println("Cat jump this height!");
            return 1;
        } else {
            System.out.println("Cat didn't jump this height!");
            System.out.println("His competitions is finished!\n");
            return 0;
        }
    }
}
