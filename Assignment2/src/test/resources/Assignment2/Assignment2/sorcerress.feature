Feature: Sorceress Test Cases
  Player should recieve appropriate score with the Sorceress Fortune Card

  Scenario: Corresponding to excel row 71
    Given a game of pirates is in progress
   	When their fortune card is "SO"
   	And the value of their dice is "Skull, Skull, Monkey, Monkey, Sword, Sword, Parrot, Parrot"
   	Then score is 0 and the Player has 2 of dice type "Skull"
		Then the player succesfully rerolled dice in position "1"? "false"
		Then the player succesfully rerolled dice in position "1, 2"? "false"
		Then the player succesfully rerolled dice in position "1, 3"? "true" 
		And the dice rerolled in position 1 is no longer a "Skull"
		
		
	Scenario Outline: Corresponding to excel row 72
		Given a game of pirates is in progress
		When their fortune card is "SO"
		And the value of their dice is "Monkey, Monkey, Sword, Sword, Monkey, Parrot, Sword, Coin"
		Then score is 300 and the Player has 0 of dice type "Skull"
		When the value of their dice is "Skull, Monkey, Sword, Sword, Monkey, Parrot, Sword, Coin"
		Then score is 200 and the Player has 1 of dice type "Skull"
		Then the player succesfully rerolled dice in position "1"? "false"
		Then the player succesfully rerolled dice in position "1, 2"? "true"
		And the dice rerolled in position 1 is no longer a "Skull"
		When they <end> their turn 
		And the turn is over? <outcome>
		And the player is dead? "false"
		And the score is not 0
		
	Examples:
		|end         |outcome|
		|"end"       |"true" |
		|"do not end"|"false"|
		