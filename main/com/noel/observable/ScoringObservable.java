package main.com.noel.observable;

import main.com.noel.observer.ScoringObserver;

public interface ScoringObservable {
  void registerObserver(ScoringObserver scoringObserver);

  void unregisterObserver(ScoringObserver scoringObserver);

  void notifyObservers();
}
