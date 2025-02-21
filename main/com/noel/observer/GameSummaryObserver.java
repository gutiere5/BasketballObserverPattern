package main.com.noel.observer;

import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameSummaryObserver implements ScoringObserver {

  @Override
  public void update(Scoring scoring) {
    Team teamA = scoring.getTeamA();
    Team teamB = scoring.getTeamB();
    System.out.println("Game Summary:");
    System.out.println("Team " + teamA.getName() + ": " + teamA.getScore() + " points");
    System.out.println("Team " + teamB.getName() + ": " + teamB.getScore() + " points");
    System.out.println("Quarter: " + scoring.getGameQuarter());
    System.out.println();
  }
}
