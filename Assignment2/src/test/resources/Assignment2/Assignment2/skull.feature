
Feature: Title of your feature
  I want to use this template for my feature file


  Scenario Outline: Title of your scenario outline
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then the score is <outcome>
    And the player is dead? <dead>
    And the penalty being sent is <penalty>

    Examples: 
      |d1																														|fc   |outcome|dead   |penalty|
			|"Skull, Monkey, Monkey, Sword, Sword, Parrot, Parrot, Monkey"|"SK2"|0      |"true" |0      |
			|"Skull, Skull, Monkey, Monkey, Sword, Sword, Parrot, Parrot" |"SK1"|0      |"true" |0      |
			|"Skull, Skull, Skull, Skull, Skull, Monkey, Sword, Parrot"   |"CA" |0      |"false"|1000   |
	
	Scenario: Title of your scenario
    Given a game of pirates with three players is in progress 
    When player ones fortune card is "SK2"
    When the value of player ones dice is "Skull, Skull, Skull, Monkey, Sword, Sword, Parrot, Parrot"
    Then the score is 0
    And the penalty being sent is 500
    When the value of player ones dice is "Skull, Skull, Skull, Monkey, Sword, Sword, Coin, Monkey"
    Then the score is 0
    And the penalty being sent is 500
    When player two received the deduction 
    And player three received the deduction
    Then the score is 0
    And the player is dead? "false"
    And player two has a deduction of 500
    And player three has a deduction of 500
    
			
	Scenario Outline: Title of your scenario outline2
    Given a game of pirates with three players is in progress 
    When player ones fortune card is <fc>
    When the value of player ones dice is <d1>
    Then the score is 0
    And the penalty being sent is <penalty1>
    When the value of player ones dice is <d2>
    Then the score is 0
    And the penalty being sent is <penalty2>
    When the value of player ones dice is <d3>
    Then the score is 0
    And the penalty being sent is <penalty3>
    When player two received the deduction 
    And player three received the deduction
    Then the score is 0
    And the player is dead? <dead>
    And player two has a deduction of <penalty3>
    And player three has a deduction of <penalty3>
    

    Examples: 
      |d1																													 |d2																												|d3																												 |fc   |dead   |penalty1|penalty2|penalty3|
      |"Skull, Skull, Monkey, Monkey, Sword, Sword, Parrot, Parrot"|"Skull, Skull, Skull, Skull, Sword, Sword, Parrot, Parrot"|"Skull, Skull, Skull, Skull, Skull, Sword, Parrot, Parrot"|"SK2"|"false"|400     |600     |700     |
			|"Skull, Skull, Skull, Monkey, Monkey, Sword, Sword, Parrot" |"Skull, Skull, Skull, Skull, Parrot, Sword, Sword, Parrot"|"Skull, Skull, Skull, Skull, Monkey, Parrot, Sword, Coin" |"SK1"|"false"|400     |500     |500     |