Feature: Features relating to Treasure Chest Fortune Card
  I want to use this template to accuratly score the game when the fortune card is the Treasure Chest

	@Row81
	Scenario: corresponding to row 81
		Given a game of pirates is in progress
		When their fortune card is "TC"
		And the value of their dice is "Parrot, Parrot, Parrot, Sword, Sword, Diamond, Diamond, Coin"
		And they save the dice in position "6, 7, 8"
		Then score is 400 and the treasure chest has 2 "Diamond" and 1 "Coin" dice inside
		When the value of their dice is "Parrot, Parrot, Parrot, Parrot, Parrot, Diamond, Diamond, Coin"
		And they save the dice in position "1, 2, 3, 4, 5"
		And they remove the dice in position "6, 7, 8"
		Then score is 1300 and the treasure chest has 5 "Parrot" dice inside
		When the value of their dice is "Parrot, Parrot, Parrot, Parrot, Parrot, Skull, Parrot, Coin"
		Then score is 1100 and the treasure chest has 5 "Parrot" dice inside
		And the player is dead? "false"
		
	@Row86
	Scenario: corresponding to row 86
		Given a game of pirates is in progress
		When their fortune card is "TC"
		And the value of their dice is "Skull, Skull, Parrot, Parrot, Parrot, Coin, Coin, Coin"
		And they save the dice in position "6, 7, 8"
		Then score is 500 and the treasure chest has 3 "Coin" dice inside
		When the value of their dice is "Skull, Skull, Diamond, Diamond, Coin, Coin, Coin, Coin"
		And they save the dice in position "5"
		Then score is 800 and the treasure chest has 4 "Coin" dice inside
		When the value of their dice is "Skull, Skull, Skull, Coin, Coin, Coin, Coin, Coin"
		Then score is 600 and the treasure chest has 4 "Coin" dice inside
		And the player is dead? "true"
		
	