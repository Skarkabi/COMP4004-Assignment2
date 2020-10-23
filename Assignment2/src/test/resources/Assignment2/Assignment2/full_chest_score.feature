Feature: Features relating to Full Chest Functionality
  I want to use this template to accuratly score the game & make sure the full chest funcitonality is working
  
  @Rows92,93,94,98
  Scenario Outline: Player scoring their round & checking for full chest bonus after first roll
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then the score is <outcome>
    And the full chest is <chest>
    
  #Row 92
  #Row 93
  #Row 94
  #Row 98
	Examples:
  	|d1			  																								      |fc  |outcome|chest	     |
  	|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Diamond, Parrot" |"CO"|400    |"Not Active"|
  	|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Coin, Coin"      |"CA"|1800   |"Active"    |
  	|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Sword, Diamond"  |"CO"|1000   |"Active"    |
  	|"Monkey, Monkey, Parrot, Coin, Coin, Diamond, Diamond, Diamond"|"MB"|1200   |"Active"    |
  	
  Scenario: Excel Row 95 (Same as outline but has a reroll)
  	Given a game of pirates is in progress
    When their fortune card is "SB2300"
    And the value of their dice is "Monkey, Monkey, Monkey, Monkey, Sword, Parrot, Parrot, Coin"
    Then the score is 0
    And the full chest is "Not Active"
    When the value of their dice is "Monkey, Monkey, Monkey, Monkey, Sword, Coin, Sword, Coin"
    Then the score is 1200
    And the full chest is "Active"