package main.com.noel.observer;

import java.util.Random;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameNewsGenerator implements ScoringObserver {
  Random random = new Random();
  Team teamA;
  Team teamB;

  @Override
  public void update(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();

    generateNews();
  }

  private void generateNews() {
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
  }
}
