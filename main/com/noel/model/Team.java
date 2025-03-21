package main.com.noel.model;

public class Team {
  private final String name;
  private int points;
  private int predictedPoints;
  private int totalWins;
  private int totalLosses;
  private int totalDraws;

  public Team(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public void addPoints(int points) {
    this.points += points;
  }

  public int getPredictedPoints() {
    return predictedPoints;
  }

  public void setPredictedPoints(int predictedPoints) {
    this.predictedPoints = predictedPoints;
  }

  public int getTotalWins() {
    return totalWins;
  }

  public void setTotalWins(int totalWins) {
    this.totalWins = totalWins;
  }

  public int getTotalLosses() {
    return totalLosses;
  }

  public void setTotalLosses(int totalLosses) {
    this.totalLosses = totalLosses;
  }

  public int getTotalDraws() {
    return totalDraws;
  }

  public void setTotalDraws(int totalDraws) {
    this.totalDraws = totalDraws;
  }
}
