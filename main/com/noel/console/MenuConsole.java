package main.com.noel.console;

import java.util.Random;
import java.util.Scanner;
import main.com.noel.controller.GameDataController;
import main.com.noel.controller.PredictionStorage;
import main.com.noel.model.Scoring;

public class MenuConsole {
  private final Scanner scanner;
  private final Scoring scoring;
  private final GameDataController gameDataController;
  private final Random random;

  public MenuConsole(Scanner scanner, Scoring scoring, GameDataController gameDataController) {
    this.scanner = scanner;
    this.scoring = scoring;
    this.gameDataController = gameDataController;
    this.random = new Random();
  }

  public void displayMenu() {
    int userChoice = 0;
    do {
      System.out.println();
      System.out.println("Welcome to the Basketball Game");
      System.out.println("1. Start New Game");
      System.out.println("2. Simulate One Quarter");
      System.out.println("3. Print Current Score");
      System.out.println("4. Print Current Predictions");
      System.out.println("5. Print Table Of Scores");
      System.out.println("6. Print Prediction Statistics");
      System.out.println("0. Exit");
      userChoice = scanner.nextInt();
      handleChoice(userChoice);
    } while (userChoice != 0);
  }

  private void handleChoice(int userChoice) {
    switch (userChoice) {
      case 1:
        startGame();
        break;
      case 2:
        simulateQuarter();
        break;
      case 3:
        printCurrentScore();
        break;
      case 4:
        scoring.notifyPredictions();
        break;
      case 5:
        gameDataController.printTableOfScores();
        break;
      case 6:
        PredictionStorage.printPredictions();
        break;
      case 0:
        System.out.println("Goodbye!");
        break;
      default:
        System.out.println("Invalid choice");
    }
  }

  private void startGame() {
    System.out.println();
    System.out.println("Game Start");
    scoring.resetGame();
    while (scoring.getGameQuarter() < 4) {
      System.out.println("Press Enter to go to next quarter");
      String userInput = scanner.nextLine();

      if (userInput.isEmpty()) {
        simulateQuarter();
      }
    }

    PredictionStorage.savePrediction(scoring);
    gameDataController.saveGameResults();
  }

  private void simulateQuarter() {
    scoring.addPoints(scoring.getTeamA(), random.nextInt(16));
    scoring.addPoints(scoring.getTeamB(), random.nextInt(16));
    scoring.nextQuarter();
  }

  private void printCurrentScore() {
    System.out.println("Team A: " + scoring.getTeamA().getPoints());
    System.out.println("Team B: " + scoring.getTeamB().getPoints());
    System.out.println("Quarter: " + scoring.getGameQuarter());
  }
}
