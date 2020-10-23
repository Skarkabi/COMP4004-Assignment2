Feature: Feature To Show Dice Score & Symbol Count after the one reroll
  I want to use this template to demonstrate the scoring functionality after the one reroll

 @Rows40,41,45,60,61,62,63
  Scenario Outline: Player scoring their round after first reroll
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count2> of dice type <type>
    And the player is dead? <dead>
    
   #Row 40
   #Row 41
   #Row 45
   #Row 60
   #Row 61
   #Row 62
   #Row 63
   Examples:
  	|d1			  																									    |d2			 																										     |fc  |outcome|count|outcome2|count2|type     |dead   |
  	|"Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword, Sword"   |"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Skull, Sword"    |"CA"|600		|1    |0       |3     |"Skull"  |"true" |
 		|"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword"   |"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Skull, Sword"    |"CA"|400		|2		|0       |3     |"Skull"  |"true" |
 		|"Monkey, Monkey, Parrot, Sword, Sword, Parrot, Skull, Skull"   |"Monkey, Monkey, Monkey, Sword, Sword, Parrot, Skull, Skull"    |"CO"|100    |2    |200     |3     |"Monkey" |"false"|
 		|"Diamond, Monkey, Monkey, Sword, Sword, Parrot, Parrot, Skull" |"Diamond, Monkey, Monkey, Sword, Sword, Diamond, Parrot, Skull" |"DI"|200    |2    |400     |3     |"Diamond"|"false"|
 		|"Diamond, Diamond, Monkey, Sword, Sword, Parrot, Parrot, Skull"|"Diamond, Diamond, Monkey, Sword, Sword, Diamond, Parrot, Skull"|"CO"|300    |2    |500     |3     |"Diamond"|"false"|
 		|"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Monkey"     |"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Coin"        |"CO"|400    |3    |600     |4     |"Coin"   |"false"|
 		|"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Monkey"     |"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Coin"        |"DI"|300    |2    |500     |3     |"Coin"   |"false"|
 	
 	@Row47
 	Scenario Outline: Player scoring fghbfgb round after second rolls and checking two values
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
    Then score is <outcome1> and the Player has <count11> of dice type <type1> and <count21> of dice type <type2>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count12> of dice type <type1> and <count22> of dice type <type2>
   
  #Row 47
  Examples:
  	|d1			  																									 |d2			 																									    |fc  |outcome1|count11|count12|outcome2|count21|count22|type1   |type2  |
  	|"Monkey, Sword, Sword, Skull, Skull, Parrot, Monkey, Parrot"|"Monkey, Monkey, Parrot, Skull, Skull, Parrot, Monkey, Parrot"|"CO"|100     |2      |3      |300     |2      |3      |"Monkey"|"Parrot"|
 		
 	
 	@Row66
  Scenario: Player tries rolling one die after rolling seven swords
		Given a game of pirates is in progress
		When their fortune card is "CO"
		And the value of their dice is "Monkey, Sword, Sword, Sword, Sword, Sword, Sword, Sword"
		Then score is 2100 and the Player has 7 of dice type "Sword"
		Then the player succesfully rerolled dice in position "1"? "false"
		Then the player succesfully rerolled dice in position "1, 2"? "true"
		  	 		