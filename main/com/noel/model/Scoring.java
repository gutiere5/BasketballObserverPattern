package main.com.noel.model;

import java.util.ArrayList;
import java.util.List;
import main.com.noel.observable.ScoringObservable;
import main.com.noel.observer.FinalScorePredictionObserver;
import main.com.noel.observer.GameSummaryObserver;
import main.com.noel.observer.ScoringObserver;

public class Scoring implements ScoringObservable {
  private final List<ScoringObserver> observerList;
  private final Team teamA;
  private final Team teamB;
  private int gameQuarter;

  public Scoring(Team teamA, Team teamB) {
    this.observerList = new ArrayList<>();
    this.teamA = teamA;
    this.teamB = teamB;
    this.gameQuarter = 0;
  }

  @Override
  public void registerObserver(ScoringObserver scoringObserver) {
    observerList.add(scoringObserver);
  }

  @Override
  public void unregisterObserver(ScoringObserver scoringObserver) {
    observerList.remove(scoringObserver);
  }

  public void notifyObservers() {
    for (ScoringObserver observer : observerList) {
      observer.update(this);
    }
  }

  public void addPoints(Team team, int points) {
    team.addPoints(points);
  }

  public Team getTeamA() {
    return teamA;
  }

  public Team getTeamB() {
    return teamB;
  }

  public int getGameQuarter() {
    return gameQuarter;
  }

  public void nextQuarter() {
    gameQuarter++;
    notifyObservers();
  }

  public void resetGame() {
    teamA.setPoints(0);
    teamB.setPoints(0);
    gameQuarter = 0;
  }

  public void notifyPredictions() {
    for (ScoringObserver observer : observerList) {
      if (observer instanceof FinalScorePredictionObserver) {
        observer.update(this);
      }
    }
  }

  public void notifyGameSummary() {
    for (ScoringObserver observer : observerList) {
      if (observer instanceof GameSummaryObserver) {
        observer.update(this);
      }
    }
  }
}
