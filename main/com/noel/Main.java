package main.com.noel;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import main.com.noel.controller.GameController;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;
import main.com.noel.observer.FinalScorePredictionObserver;
import main.com.noel.observer.GameResultsObserver;
import main.com.noel.observer.GameSummaryObserver;

public class Main {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    GameController gameController = new GameController();

    Team teamA = new Team("Lakers");
    Team teamB = new Team("Bulls");

    gameController.importTeamStatsFromFile(teamA, teamB);

    Scoring scoring = new Scoring(teamA, teamB);
    scoring.registerObserver(new GameSummaryObserver());
    scoring.registerObserver(new FinalScorePredictionObserver());
    scoring.registerObserver(new GameResultsObserver());

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
    gameController.exportStatsToFile(teamA, teamB);
  }
}
