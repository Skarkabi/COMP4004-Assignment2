
Feature: score_outcomes
	Player should be able to get expected socre for number of rolls
	
	Scenario: excel ro 39
	 	Given a game of pirates is in progress
    When the value of their dice is "Skull", "Skull", "Skull", "Coin", "Diamond", "Parrot", "Sword", and "Sword"
    When their fortune card is "CA"
    Then score is 0 and the Player has 3 of dice type "Skull"
    
  Scenario: excel row 40
    Given a game of pirates is in progress
    When their fortune card is "CA"
		When the value of their dice is "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Sword", "Sword", and "Sword"
		Then score is 600 and the Player has 1 of dice type "Skull"
    When the value of their dice is "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Skull", "Skull", and "Sword"
    Then score is 0 and the Player has 3 of dice type "Skull"
    
  Scenario: excel row 41
    Given a game of pirates is in progress
    When their fortune card is "CA"
		When the value of their dice is "Skull", "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Sword", and "Sword"
		Then score is 400 and the Player has 2 of dice type "Skull"
    When the value of their dice is "Skull", "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Skull", and "Sword"
    Then score is 0 and the Player has 3 of dice type "Skull"
    
   Scenario: excel row 42
    Given a game of pirates is in progress
    When their fortune card is "CA"
		When the value of their dice is "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Sword", "Sword", and "Sword"
		Then score is 600 and the Player has 1 of dice type "Skull"
    When the value of their dice is "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Skull", "Monkey", and "Monkey"
    Then score is 400 and the Player has 2 of dice type "Skull"
    When the value of their dice is "Skull", "Parrot", "Parrot", "Parrot", "Parrot", "Skull", "Skull", and "Monkey"
    Then score is 0 and the Player has 3 of dice type "Skull"
  