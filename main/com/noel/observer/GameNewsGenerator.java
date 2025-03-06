package main.com.noel.observer;

import java.util.Random;
import main.com.noel.model.Scoring;
import main.com.noel.model.Team;

public class GameNewsGenerator implements ScoringObserver {
  Random random = new Random();
  Team teamA;
  Team teamB;
  int gameQuarter;

  @Override
  public void update(Scoring scoring) {
    this.teamA = scoring.getTeamA();
    this.teamB = scoring.getTeamB();
    this.gameQuarter = scoring.getGameQuarter();

    generateNews();
  }

  private void generateNews() {
    if (gameQuarter < 4) {
      generateQuarterNews();
    } else {
      generateFinalNews();
    }
  }

  private void generateQuarterNews() {
    String leadingTeam = teamA.getPoints() > teamB.getPoints() ? teamA.getName() : teamB.getName();
    String trailingTeam = teamA.getPoints() < teamB.getPoints() ? teamA.getName() : teamB.getName();
    String newsMessage;

    switch (random.nextInt(4)) {
      case 0:
        newsMessage =
            String.format(("Breaking News: %s are ahead of %s"), leadingTeam, trailingTeam);
        break;
      case 1:
        newsMessage =
            String.format("Breaking News: %s are leading against %s", leadingTeam, trailingTeam);
        break;
      case 2:
        newsMessage =
            String.format("Breaking News: %s are dominating %s", leadingTeam, trailingTeam);
        break;
      case 3:
        newsMessage = String.format("Breaking News: %s are winning %s", leadingTeam, trailingTeam);
        break;
      default:
        newsMessage = "Breaking News: No News To Report";
    }
    System.out.println(newsMessage);
  }

  private void generateFinalNews() {
    String finalMessage = "";

    if (teamA.getPoints() > teamB.getPoints()) {
      finalMessage =
          String.format("Breaking News: %s wins against %s", teamA.getName(), teamB.getName());
    } else if (teamA.getPoints() < teamB.getPoints()) {
      finalMessage =
          String.format("Breaking News: %s wins against %s", teamB.getName(), teamA.getName());
    } else {
      String.format(
          "Breaking News: It's a draw between %s and %s", teamA.getName(), teamB.getName());
    }
    System.out.println(finalMessage);
  }
}
