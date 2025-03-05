package main.com.noel.controller;

import java.io.*;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameDataController {
  Team teamA;
  Team teamB;

  public GameDataController(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();
  }

  public void importTeamStatsFromFile() {
    String filePath = "main/com/noel/resources/team_standings.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("Invalid number format in file.");
    }
  }

  public void exportStatsToFile() throws IOException {
    String filePath = "main/com/noel/resources/team_standings.txt";
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
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
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  public void saveGameResults() {
    String filePath = "main/com/noel/resources/scores.txt";
    String winner = (teamA.getPoints() > teamB.getPoints()) ? teamA.getName() : teamB.getName();
    try (FileWriter writer = new FileWriter(filePath, true)) {
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
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  public void printTableOfScores() {
    String filePath = "main/com/noel/resources/scores.txt";

    System.out.printf(
        "%-10s %-10s %-10s %-10s %-10s%n", "Team A", "Points", "Team B", "Points", "Winner");
    System.out.println("--------------------------------------------------");

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        System.out.printf(
            "%-10s %-10s %-10s %-10s %-10s%n", data[0], data[1], data[2], data[3], data[4]);
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("Invalid number format in file.");
    }
  }
}
