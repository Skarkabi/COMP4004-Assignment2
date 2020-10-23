Feature: Title of your feature
  I want to use this template for my feature file


 @Row110,111,112,114,117,120
 Scenario Outline: Player scoring their round after first roll
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    And their score is <input>
    Then score is <outcome> and the player <action> have <count> swords
    And the player is dead? <dead>
    
  #Row 110
  #Row 111
  #Row 112
  #Row 114
  #Row 117
  #Row 120
	Examples:
  	|d1			  																								    |fc       |input|outcome|action    |count|dead   |
  	|"Skull, Skull, Skull, Parrot, Parrot, Parrot, Diamond, Sword"|"SB2300" |300  |0      |"does not"|2    |"true" |
  	|"Skull, Skull, Skull, Parrot, Parrot, Parrot, Diamond, Sword"|"SB3500" |500  |0      |"does not"|3    |"true" |
  	|"Skull, Skull, Skull, Parrot, Parrot, Parrot, Diamond, Sword"|"SB41000"|1000 |0      |"does not"|4    |"true" |
  	|"Monkey, Monkey, Monkey, Sword, Sword, Coin, Parrot, Parrot" |"SB2300" |0    |500    |"does"    |2    |"false"|
  	|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Sword, Parrot" |"SB3500" |0    |800    |"does"    |4    |"false"|
  	|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Sword, Skull"  |"SB41000"|0    |1300   |"does"    |4    |"false"|
  	
  	

@Rows115,118
 Scenario Outline: Player scoring their round after a reroll
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    And their score is <input>
    Then the turn is over? "false"
    And their are "less" than or equal to <count> swords
    When the value of their dice is <d2> 
    And it is their second turn
    Then the score is <outcome>
    And their are "greater" than or equal to <count> swords
    And the player is dead? <dead>
  
  #Row 115
  #Row 118
	Examples:
  	|d1			  																								     |d2                                                          |fc      |input|outcome|count|dead   |
  	|"Monkey, Monkey, Monkey, Monkey, Sword, Skull, Parrot, Parrot"|"Monkey, Monkey, Monkey, Monkey, Sword, Skull, Sword, Skull"|"SB2300"|0    |500    |2    |"false"|
  	|"Monkey, Monkey, Monkey, Monkey, Sword, Sword, Skull, Skull"  |"Skull, Skull, Sword, Sword, Sword, Sword, Skull, Skull"    |"SB3500"|500  |0      |3    |"true" |
  	
  Scenario: Excel row 121 (Same as the Scenario Outline but has 2 rerolls)
  	Given a game of pirates is in progress
  	When their fortune card is "SB41000"
  	And the value of their dice is "Monkey, Monkey, Monkey, Sword, Skull, Diamond, Parrot, Parrot"
  	Then the score is 0
  	And their are "less" than or equal to 4 swords 
  	When the value of their dice is "Monkey, Monkey, Monkey, Sword, Skull, Diamond, Sword, Sword"
  	Then the score is 0
  	And their are "less" than or equal to 4 swords
  	When the value of their dice is "Sword, Parrot, Parrot, Sword, Skull, Diamond, Sword, Sword"
  	Then the score is 1300
  	And their are "greater" than or equal to 4 swords
 