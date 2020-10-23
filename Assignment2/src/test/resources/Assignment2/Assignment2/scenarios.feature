Feature: Scenarios feature file

 Scenario: game Playes
  	Given three players connect to the server
    When player 1 sees the scores
    When player 1 presses three to score the round
    When the server sends the scores to player 2 and "it is not" the final round
    When player 2 sees the scores
    When player 2 presses three to score the round
    When the server sends the scores to player 3 and "it is not" the final round
    When player 3 sees the scores
    When player 3 presses three to score the round
    When the server sends the scores to player 1 and "it is" the final round
    When player 1 sees the scores
    