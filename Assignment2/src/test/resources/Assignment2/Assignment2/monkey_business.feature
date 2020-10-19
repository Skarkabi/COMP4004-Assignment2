Feature: Monkey Business Test Cases
  Player should recieve appropriate score with the Monkey Business Fortune Card

  Scenario: Corresponding to Excel sheet row 76
  	Given a game of pirates is in progress
  	When their fortune card is "MB"
    And the value of their dice is "Monkey, Monkey, Monkey, Parrot, Parrot, Parrot, Skull, Coin"
    Then score is 1100 and the Player has 3 of dice type "Monkey" and 3 of dice type "Parrot"
    
  Scenario Outline: Testing Monkey Business Fortune Card Over Several Roll
  	Given a game of pirates is in progress
		When their fortune card is "MB"
		And the value of their dice is <d1>
		Then score is <outcome1> and the Player has <count11> of dice type "Monkey" and <count12> of dice type "Parrot"
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count21> of dice type "Monkey" and <count22> of dice type "Parrot"
    When the value of their dice is <d3>
    Then score is <outcome3> and the Player has <count31> of dice type "Monkey" and <count32> of dice type "Parrot"
    
  Examples:
  	|d1			  																									    |d2			 																								     |d3																									           |outcome1|count11|count12|outcome2|count21|count22|outcome3|count31|count32|
  	|"Monkey, Diamond, Coin, Diamond, Sword, Sword, Diamond, Monkey"|"Monkey, Diamond, Coin, Diamond, Sword, Sword, Coin, Monkey"|"Monkey, Diamond, Coin, Parrot, Sword, Sword, Coin, Monkey"    |500     |2      |0      |400     |2      |0      |400     |2      |1      |
  	|"Monkey, Diamond, Coin, Parrot, Coin, Diamond, Sword, Sword"   |"Monkey, Monkey, Coin, Parrot, Parrot, Parrot, Sword, Sword"|"Monkey, Monkey, Monkey, Parrot, Parrot, Parrot, Parrot, Sword"|400     |1      |1      |600     |2      |3      |2000    |3      |4      |