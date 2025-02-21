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
    System.out.println("Predicting final score...");
    System.out.printf(
        "Final score prediction:\nTeam A: %d \nTeam B: %d%n",
        generatePrediction(teamA.getScore()), generatePrediction(teamB.getScore()));
    System.out.println();
  }

  private int generatePrediction(int score) {
    return score / quarter * 4;
  }
}
