package Java2.LessonOne;

public class JumpRunCompetitions {
    Actions[] participantsList = new Actions[100];
    Obstacle[] obstaclesList = new Obstacle[100];

    private int participantsPosition = 0;
    private int obstaclePosition = 0;

    public void fillParticipantsList(Actions actions) {
        if (this.participantsPosition >= 100) {
            System.out.println("Participants List is full!");
        } else {
            this.participantsList[this.participantsPosition] = actions;
            this.participantsPosition++;
        }
    }

    public void fillObstaclesList(Obstacle obstacle) {
        if (this.obstaclePosition >= 100) {
            System.out.println("Obstacle List is full!");
        } else {
            this.obstaclesList[obstaclePosition] = obstacle;
            this.obstaclePosition++;
        }
    }

    public void startCompetition() {
        for (Actions actions : participantsList) {
            if (actions == null) {
                System.out.println("All participants take part of competition");
                break;
            }
            for (Obstacle obstacle : obstaclesList) {
                int ans;

                if (obstacle == null) {
                    System.out.println("All obstacles was passed");
                    System.out.println();
                    break;
                }

                if (obstacle instanceof Wall) {
                    ans = actions.jump(((Wall) obstacle).getHeight());
                } else {
                    ans = actions.run(((RunningTrack) obstacle).getDistance());
                }
                if (ans == 0) {
                    break;
                }
            }
        }
    }
}
