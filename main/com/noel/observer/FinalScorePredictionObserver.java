package main.com.noel.observer;

import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class FinalScorePredictionObserver implements ScoringObserver {
  private Team teamA;
  private Team teamB;
  private int quarter;

  @Override
  public void update(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();
    this.quarter = scoring.getGameQuarter();
    predictFinalScore();
  }

  public void predictFinalScore() {
    teamA.setPredictedPoints(generatePrediction(teamA.getPoints()));
    teamB.setPredictedPoints(generatePrediction(teamB.getPoints()));
    System.out.printf(
        "Final score prediction:\nTeam %s: %d \nTeam %s: %d%n",
        teamA.getName(),
        generatePrediction(teamA.getPoints()),
        teamB.getName(),
        generatePrediction(teamB.getPoints()));
    System.out.println();
  }

  private int generatePrediction(int score) {
    return score / quarter * 4;
  }
}
