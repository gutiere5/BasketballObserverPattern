package main.com.noel.observer;

import main.com.noel.model.Scoring;

public interface ScoringObserver {
  void update(Scoring scoring);
}
