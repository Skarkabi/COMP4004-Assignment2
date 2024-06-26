Feature: Feature To Show Dice Score & Symbol Count after the first roll
  I want to use this template to demonstrate the scoring functionality on the first roll
  
  @Rows39,48,49,54,55,56,57,58
	Scenario Outline: Player scoring their round after first roll checking one value
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type>
    And the player is dead? <dead>
    
  #Row 39
  #Row 48
  #Row 49
  #Row 54  
  #Row 55
  #Row 56
  #Row 57
  #Row 58
	Examples:
  	|d1			  																								       |fc  |outcome|count|type     |dead   |
  	|"Skull, Skull, Skull, Coin, Diamond, Parrot, Sword, Sword"      |"CO"|0			|3    |"Skull"  |"true" | 
  	|"Diamond, Diamond, Diamond, Sword, Monkey, Parrot, Sword, Skull"|"CO"|500    |3    |"Diamond"|"false"|
  	|"Coin, Coin, Coin, Coin, Sowrd, Monkey, Parrot, Skull"          |"DI"|700    |4    |"Coin"   |"false"| 
    |"Monkey, Monkey, Monkey, Monkey, Monkey, Monkey, Sword, Parrot" |"CO"|1100   |6    |"Monkey" |"false"|
    |"Parrot, Parrot, Parrot, Parrot, Parrot, Parrot, Parrot, Skull" |"CO"|2100   |7    |"Parrot" |"false"| 
    |"Coin, Coin, Coin, Coin, Coin, Coin, Coin, Coin"                |"CO"|5400   |9    |"Coin"   |"false"| 
    |"Coin, Coin, Coin, Coin, Coin, Coin, Coin, Coin"                |"DI"|5400   |8    |"Coin"   |"false"|
    |"Sword, Sword, Sword, Sword, Sword, Sword, Sword, Sword"        |"CA"|9000   |8    |"Sword"  |"false"|
    
  
  @Rows44,46,50,64  
  Scenario Outline: Player scoring their round after first roll checking two values
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type> and <count2> of dice type <type2>
  
  #Row 44
  #Row 46
  #Row 50
  #Row 64
	Examples:
  	|d1			  																										|fc  |outcome |count|type    |count2|type2    |
    |"Diamond, Diamond, Coin, Coin, Parrot, Sword, Skull, Parrot" |"CA"|800     |2    |"Coin"  |2			|"Diamond"|
		|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Parrot, Parrot"|"CO"|300     |3    |"Monkey"|3     |"Sword"  |
		|"Sword, Sword, Sword, Parrot, Parrot, Parrot, Parrot, Monkey"|"CO"|400     |3    |"Sword" |4     |"Parrot" |
		|"Monkey, Monkey, Monkey, Monkey, Coin, Coin, Skull, Skull"   |"CO"|600    	|4    |"Monkey"|3     |"Coin"   |
		