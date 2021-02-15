package Java2.LessonOne;


public class Main {
    public static void main(String[] args) {
        JumpRunCompetitions jumpRunCompetitions = new JumpRunCompetitions();
        for (int i = 0; i < 2; i++) {
            // Fill Participants;
            jumpRunCompetitions.fillParticipantsList(new Cat());
            jumpRunCompetitions.fillParticipantsList(new Human());
            jumpRunCompetitions.fillParticipantsList(new Robot());

            // Fill Obstacles;
            jumpRunCompetitions.fillObstaclesList(new RunningTrack());
            jumpRunCompetitions.fillObstaclesList(new Wall());
        }

        jumpRunCompetitions.startCompetition();

    }
}
