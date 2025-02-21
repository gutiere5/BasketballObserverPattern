package main.com.noel.controller;

import java.io.*;
import main.com.noel.model.Team;

public class GameController {

  public void importTeamStatsFromFile(Team teamA, Team teamB) {
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

          // Check which team to update
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

  public void exportStatsToFile(Team teamA, Team teamB) throws IOException {
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
}
