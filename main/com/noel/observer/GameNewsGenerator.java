package main.com.noel.observer;

import java.util.Random;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameNewsGenerator implements ScoringObserver {
  Random random = new Random();
  Team teamA;
  Team teamB;
  int gameQuarter;

  @Override
  public void update(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();
    this.gameQuarter = scoring.getGameQuarter();

    generateNews();
  }

  private void generateNews() {
    if (gameQuarter < 4) {
      switch (random.nextInt(4)) {
        case 0:
          if (teamA.getPoints() > teamB.getPoints()) {
            System.out.println(
                "Breaking News: " + teamA.getName() + " are ahead of " + teamB.getName());
          } else {
            System.out.println(
                "Breaking News: " + teamB.getName() + " are ahead of " + teamA.getName());
          }
          break;
        case 1:
          if (teamA.getPoints() > teamB.getPoints()) {
            System.out.println(
                "Breaking News: " + teamA.getName() + " are leading against " + teamB.getName());
          } else {
            System.out.println(
                "Breaking News: " + teamB.getName() + " are leading against " + teamA.getName());
          }
          break;
        case 2:
          if (teamA.getPoints() > teamB.getPoints()) {
            System.out.println(
                "Breaking News: " + teamA.getName() + " are dominating " + teamB.getName());
          } else {
            System.out.println(
                "Breaking News: " + teamB.getName() + " are dominating " + teamA.getName());
          }
          break;
        case 3:
          if (teamA.getPoints() > teamB.getPoints()) {
            System.out.println(
                "Breaking News: " + teamA.getName() + " are winning " + teamB.getName());
          } else {
            System.out.println(
                "Breaking News: " + teamB.getName() + " are winining " + teamA.getName());
          }
          break;
        default:
          System.out.println("Breaking News: No News To Report");
      }
    } else {
      if (teamA.getPoints() > teamB.getPoints()) {
        System.out.println(
            "Breaking News: " + teamA.getName() + " wins against " + teamB.getName());
      } else if (teamA.getPoints() < teamB.getPoints()) {
        System.out.println(
            "Breaking News: " + teamB.getName() + " wins against " + teamA.getName());
      } else {
        System.out.println(
            "Breaking News: It's a draw between " + teamA.getName() + " and " + teamB.getName());
      }
    }
  }
}
