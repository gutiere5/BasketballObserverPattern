package main.com.noel.observer;

import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameResultsObserver implements ScoringObserver {
  private Team teamA;
  private Team teamB;

  @Override
  public void update(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();

    if (scoring.getGameQuarter() == 4) {
      displayTeamResults();
    }
  }

  private void displayTeamResults() {
    System.out.println("Game results:");
    getWinner();
    displayTeamStats();
  }

  private void getWinner() {
    if (teamA.getPoints() > teamB.getPoints()) {
      System.out.println("Team A wins!");
      teamA.setTotalWins(teamA.getTotalWins() + 1);
    } else if (teamA.getPoints() < teamB.getPoints()) {
      System.out.println("Team B wins!");
      teamB.setTotalWins(teamB.getTotalWins() + 1);
    } else {
      teamA.setTotalDraws(teamA.getTotalDraws() + 1);
      teamB.setTotalDraws(teamB.getTotalDraws() + 1);
    }
  }

  private void displayTeamStats() {
    System.out.printf(
        "Team %s Total Wins: %d\nTeam %s Total Wins %d\n",
        teamA.getName(), teamA.getTotalWins(), teamB.getName(), teamB.getTotalWins());
    System.out.println();
  }
}
