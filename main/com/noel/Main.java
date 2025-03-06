package main.com.noel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import main.com.noel.console.MenuConsole;
import main.com.noel.controller.GameDataController;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;
import main.com.noel.observer.FinalScorePredictionObserver;
import main.com.noel.observer.GameNewsGenerator;
import main.com.noel.observer.GameResultsObserver;
import main.com.noel.observer.GameSummaryObserver;

public class Main {
  public static void main(String[] args) throws IOException {
    List<Team> teams =
        Arrays.asList(
            new Team("Lakers"), new Team("Bulls"),
            new Team("Celtics"), new Team("Warriors"));

    Scanner scanner = new Scanner(System.in);
    Random random = new Random();

    Team teamA = selectRandomTeam(teams, random);
    Team teamB = selectDifferentRandomTeam(teams, random, teamA);

    Scoring scoring = new Scoring(teamA, teamB);
    registerObservers(scoring);

    GameDataController gameDataController = new GameDataController(scoring);
    gameDataController.importTeamStatsFromFile();

    MenuConsole menu = new MenuConsole(scanner, scoring, gameDataController);
    menu.displayMenu();

    gameDataController.exportStatsToFile();
  }

  private static void registerObservers(Scoring scoring) {
    scoring.registerObserver(new GameSummaryObserver());
    scoring.registerObserver(new FinalScorePredictionObserver());
    scoring.registerObserver(new GameResultsObserver());
    scoring.registerObserver(new GameNewsGenerator());
  }

  private static Team selectDifferentRandomTeam(List<Team> teams, Random random, Team teamA) {
    Team teamB;
    do {
      teamB = selectRandomTeam(teams, random);
    } while (teamA.equals(teamB));
    return teamB;
  }

  private static Team selectRandomTeam(List<Team> teams, Random random) {
    return teams.get(random.nextInt(teams.size()));
  }
}
