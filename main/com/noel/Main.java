package main.com.noel;

import java.util.Random;
import java.util.Scanner;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;
import main.com.noel.observer.FinalScorePredictionObserver;
import main.com.noel.observer.GameSummaryObserver;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    GameSummaryObserver gameSummaryObserver = new GameSummaryObserver();
    FinalScorePredictionObserver finalScorePredictionObserver = new FinalScorePredictionObserver();

    Team teamA = new Team("Team A");
    Team teamB = new Team("Team B");

    Scoring scoring = new Scoring(teamA, teamB);
    scoring.registerObserver(gameSummaryObserver);
    scoring.registerObserver(finalScorePredictionObserver);

    System.out.println("Game Start");
    while (scoring.getGameQuarter() < 4) {
      System.out.println("Press Enter to go to next quarter");
      String userInput = scanner.nextLine();

      if (userInput.isEmpty()) {
        scoring.addPoints(scoring.getTeamA(), random.nextInt(16));
        scoring.addPoints(scoring.getTeamB(), random.nextInt(16));
        scoring.nextQuarter();
      }
    }
  }
}
