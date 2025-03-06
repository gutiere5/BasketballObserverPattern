package main.com.noel.controller;

import java.io.*;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameDataController {
  private static final String TEAM_STATS_FILE_PATH = "main/com/noel/resources/team_standings.txt";
  private static final String SCORES_FILE_PATH = "main/com/noel/resources/scores.txt";
  private final Team teamA;
  private final Team teamB;

  public GameDataController(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();
  }

  public void importTeamStatsFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader(TEAM_STATS_FILE_PATH))) {
      String line;
      boolean firstLine = true;

      while ((line = br.readLine()) != null) {
        if (firstLine) {
          firstLine = false;
          continue;
        }

        String[] data = line.split(", ");
        if (data.length == 4) {
          String teamName = data[0];
          int wins = Integer.parseInt(data[1]);
          int losses = Integer.parseInt(data[2]);
          int draws = Integer.parseInt(data[3]);

          if (teamName.equalsIgnoreCase(teamA.getName())) {
            teamA.setTotalWins(wins);
            teamA.setTotalLosses(losses);
            teamA.setTotalDraws(draws);
          } else if (teamName.equalsIgnoreCase(teamB.getName())) {
            teamB.setTotalWins(wins);
            teamB.setTotalLosses(losses);
            teamB.setTotalDraws(draws);
          }
        }
      }
    } catch (IOException | NumberFormatException e) {
      logError("Error reading team stats file: ", e);
    }
  }



  public void exportStatsToFile() {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(TEAM_STATS_FILE_PATH))) {
      bufferedWriter.write("Team, Wins, Losses, Draws\n");
      bufferedWriter.write(
          teamA.getName()
              + ", "
              + teamA.getTotalWins()
              + ", "
              + teamA.getTotalLosses()
              + ", "
              + teamA.getTotalDraws()
              + "\n");
      bufferedWriter.write(
          teamB.getName()
              + ", "
              + teamB.getTotalWins()
              + ", "
              + teamB.getTotalLosses()
              + ", "
              + teamB.getTotalDraws()
              + "\n");
    } catch (IOException e) {
      logError("Error writing to file: ", e);
    }
  }

  public void saveGameResults() {
    String winner = (teamA.getPoints() > teamB.getPoints()) ? teamA.getName() : teamB.getName();
    try (FileWriter writer = new FileWriter(SCORES_FILE_PATH, true)) {
      writer.write(
          teamA.getName()
              + ","
              + teamA.getPoints()
              + ","
              + teamB.getName()
              + ","
              + teamB.getPoints()
              + ","
              + winner
              + "\n");
    } catch (IOException e) {
      logError("Error writing game results file", e);
    }
  }

  public void printTableOfScores() {
    System.out.printf(
        "%-10s %-10s %-10s %-10s %-10s%n", "Team A", "Points", "Team B", "Points", "Winner");
    System.out.println("--------------------------------------------------");

    try (BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE_PATH))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        System.out.printf(
            "%-10s %-10s %-10s %-10s %-10s%n", data[0], data[1], data[2], data[3], data[4]);
      }
    } catch (IOException | NumberFormatException e) {
      logError("Error reading scores file", e);
    }
  }

  private void logError(String message, Exception e) {
    System.err.println(message + ": " + e.getMessage());
  }
}