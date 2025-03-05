package main.com.noel.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import main.com.noel.model.Scoring;

public class PredictionStorage {
  private static final String FILE_PATH = "main/com/noel/resources/predictions.txt";

  public static void printPredictions() {
    int totalPredictions = 0;
    int correctPredictions = 0;
    double totalError = 0.0;

    try (BufferedReader reader = new BufferedReader((new FileReader(FILE_PATH)))) {
      String line;
      boolean isFirstLine = true;
      while ((line = reader.readLine()) != null) {
        if (isFirstLine) {
          isFirstLine = false;
          continue; // Skip the header line
        }
        String[] data = line.split(",");
        if (data.length < 7) continue;

        int predictedA = Integer.parseInt(data[1]);
        int predictedB = Integer.parseInt(data[3]);
        int actualA = Integer.parseInt(data[4]);
        int actualB = Integer.parseInt(data[5]);
        boolean correct = Boolean.parseBoolean(data[6]);

        totalPredictions++;

        if (correct) correctPredictions++;
        totalError += (Math.abs(predictedA - actualA) + Math.abs(predictedB - actualB)) / 2.0;
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("Invalid number format in file.");
    }

    double accuracy = (double) correctPredictions / totalPredictions * 100;
    double averageError = totalError / totalPredictions;

    System.out.println("\nPrediction Statistics");
    System.out.println("Total Predictions: " + totalPredictions);
    System.out.println(
        "Correct Predictions: "
            + correctPredictions
            + " ("
            + String.format("%.2f", accuracy)
            + "%)");
    System.out.println("Average Point Error: " + String.format("%.2f", averageError));
  }

  public static void savePrediction(Scoring scoring) {
    boolean correctPrediction = checkIfCorrectPrediction(scoring);

    try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
      writer.write(
          scoring.getTeamA().getName()
              + ","
              + scoring.getTeamA().getPredictedPoints()
              + ","
              + scoring.getTeamB().getName()
              + ","
              + scoring.getTeamB().getPredictedPoints()
              + ","
              + scoring.getTeamA().getPoints()
              + ","
              + scoring.getTeamB().getPoints()
              + ","
              + correctPrediction
              + "\n");
    } catch (IOException e) {
      System.out.println("Error writing prediction data: " + e.getMessage());
    }
  }

  public static boolean checkIfCorrectPrediction(Scoring scoring) {
    int predictedA = scoring.getTeamA().getPredictedPoints();
    int predictedB = scoring.getTeamB().getPredictedPoints();
    int actualA = scoring.getTeamA().getPoints();
    int actualB = scoring.getTeamB().getPoints();
    return (predictedA > predictedB && actualA > actualB)
        || (predictedB > predictedA && actualB > actualA);
  }
}
