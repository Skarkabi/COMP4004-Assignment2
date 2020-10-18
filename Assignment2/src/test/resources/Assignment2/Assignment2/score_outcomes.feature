
Feature: score_outcomes
	Player should be able to get expected socre for number of rolls
	  
  Scenario Outline: Player scoring their round after first roll checking one value
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type>
    
	Examples:
  	|d1			  																								       |fc  |outcome|count|type     |
  	|"Skull, Skull, Skull, Coin, Diamond, Parrot, Sword, Sword"      |"CO"|0			|3    |"Skull"  |
  	|"Diamond, Diamond, Diamond, Sword, Monkey, Parrot, Sword, Skull"|"CO"|500    |3    |"Diamond"|
  	|"Coin, Coin, Coin, Coin, Sowrd, Monkey, Parrot, Skull"          |"DI"|700    |4    |"Coin"   |
    |"Monkey, Monkey, Monkey, Monkey, Monkey, Monkey, Sword, Parrot" |"CO"|1100   |6    |"Monkey" |
    |"Parrot, Parrot, Parrot, Parrot, Parrot, Parrot, Parrot, Skull" |"CO"|2100   |7    |"Parrot" |
    |"Coin, Coin, Coin, Coin, Coin, Coin, Coin, Coin"                |"CO"|5400   |9    |"Coin"   |
    |"Coin, Coin, Coin, Coin, Coin, Coin, Coin, Coin"                |"DI"|5400   |8    |"Coin"   |
    |"Sword, Sword, Sword, Sword, Sword, Sword, Sword, Sword"        |"CA"|9000   |8    |"Sword"  | 
    
    
   Scenario Outline: Player scoring their round after first roll checking two values
    Given a game of pirates is in progress
    When their fortune card is <fc>
    And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type> and <count2> of dice type <type2>
    
	Examples:
  	|d1			  																										|fc  |outcome |count|type    |count2|type2    |
    |"Diamond, Diamond, Coin, Coin, Parrot, Sword, Skull, Parrot" |"CA"|800     |2    |"Coin"  |2			|"Diamond"|
		|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Parrot, Parrot"|"CO"|300     |3    |"Monkey"|3     |"Sword"  |
		|"Sword, Sword, Sword, Parrot, Parrot, Parrot, Parrot, Monkey"|"CO"|400     |3    |"Sword" |4     |"Parrot" |
		|"Monkey, Monkey, Monkey, Monkey, Coin, Coin, Skull, Skull"   |"CO"|600    	|4    |"Monkey"|3     |"Coin"   |
		
	Scenario Outline: Player scoring their round after second rolls
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count2> of dice type <type>
    
   Examples:
  	|d1			  																									    |d2			 																										     |fc  |outcome|count|outcome2|count2|type     |
  	|"Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword, Sword"   |"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Skull, Sword"    |"CA"|600		|1    |0       |3     |"Skull"  |
 		|"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword"   |"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Skull, Sword"    |"CA"|400		|2		|0       |3     |"Skull"  |
 		|"Monkey, Monkey, Parrot, Sword, Sword, Parrot, Skull, Skull"   |"Monkey, Monkey, Monkey, Sword, Sword, Parrot, Skull, Skull"    |"CO"|100    |2    |200     |3     |"Monkey" |
 		|"Diamond, Monkey, Monkey, Sword, Sword, Parrot, Parrot, Skull" |"Diamond, Monkey, Monkey, Sword, Sword, Diamond, Parrot, Skull" |"DI"|200    |2    |400     |3     |"Diamond"|
 		|"Diamond, Diamond, Monkey, Sword, Sword, Parrot, Parrot, Skull"|"Diamond, Diamond, Monkey, Sword, Sword, Diamond, Parrot, Skull"|"CO"|300    |2    |500     |3     |"Diamond"|
 		|"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Monkey"     |"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Coin"        |"CO"|400    |3    |600     |4     |"Coin"   |
 		|"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Monkey"     |"Coin, Coin, Monkey, Sword, Sword, Parrot, Parrot, Coin"        |"DI"|300    |2    |500     |3     |"Coin"   |
 	
 	Scenario Outline: Player scoring their round after second rolls and checking two values
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
    Then score is <outcome1> and the Player has <count11> of dice type <type1> and <count21> of dice type <type2>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count12> of dice type <type1> and <count22> of dice type <type2>
    
   Examples:
  	|d1			  																									 |d2			 																									 |fc  |outcome1|count11|count12|outcome2|count21|count22|type1   |type2  |
  	|"Monkey, Parrot, Parrot, Skull, Skull, Sword, Monkey, Sword"|"Monkey, Monkey, Sword, Skull, Skull, Sword, Monkey, Sword"|"CO"|100     |2      |3      |300     |2      |3      |"Monkey"|"Sword"|
 		
	Scenario Outline: Player scoring their round after three rolls
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
		Then score is <outcome1> and the Player has <count1> of dice type <type>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count2> of dice type <type>
    When the value of their dice is <d3>
    Then score is <outcome3> and the Player has <count3> of dice type <type>
    
   Examples:
  	|d1			  																									  |d2			 																											 |d3																														  |fc  |outcome1|count1|outcome2|count2|outcome3|count3|type    |
  	|"Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword, Sword" |"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Monkey, Monkey"|"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Skull, Monkey"   |"CO"|400     |1     |300     |2     |0       |3     |"Skull" |
 		|"Sword, Sword, Monkey, Parrot, Skull, Monkey, Parrot, Skull" |"Sword, Sword, Sword, Sword, Skull, Monkey, Parrot, Skull"    |"Sword, Sword, Sword, Sword, Skull, Sword, Parrot, Skull"       |"CO"|100     |2     |300     |4     |600     |5     |"Sword" |
 		|"Monkey, Monkey, Parrot, Parrot, Sword, Sword, Coin, Diamond"|"Monkey, Monkey, Monkey, Monkey, Sword, Monkey, Coin, Monkey" |"Monkey, Monkey, Monkey, Monkey, Monkey, Monkey, Monkey, Monkey"|"CO"|300     |2     |1200    |6     |4600    |8     |"Monkey"|
 		
 	Scenario Outline: Player scoring their round after three rolls and checking value for two dice symbols
		Given a game of pirates is in progress
		When their fortune card is <fc>
		And the value of their dice is <d1>
		Then score is <outcome1> and the Player has <count11> of dice type <type1> and <count12> of dice type <type2>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count21> of dice type <type1> and <count22> of dice type <type2>
    When the value of their dice is <d3>
    Then score is <outcome3> and the Player has <count31> of dice type <type1> and <count32> of dice type <type2>
    
  Examples:
  	|d1			  																									|d2			 																								 |d3																									 |fc  |outcome1|count11|count12|outcome2|count21|count22|outcome3|count31|count32|type1 |type2  |
  	|"Sword, Sword, Coin, Monkey, Monkey, Parrot, Parrot, Skull"|"Sword, Sword, Coin, Coin, Monkey, Parrot, Sword, Skull"|"Sword, Sword, Coin, Coin, Coin, Sword, Sword, Skull"|"CO"|200     |2      |2      |500     |3      |3      |800     |4      |4      |"Coin"|"Sword"|
  	|"Sword, Sword, Coin, Monkey, Monkey, Parrot, Parrot, Skull"|"Sword, Sword, Coin, Coin, Monkey, Parrot, Sword, Skull"|"Sword, Sword, Coin, Coin, Coin, Sword, Sword, Skull"|"CA"|200     |1      |2      |600     |2      |3      |1200    |3      |4      |"Coin"|"Sword"|
 	
 	
	Scenario: Player tries rolling one die after rolling seven swords
		Given a game of pirates is in progress
		When their fortune card is "CO"
		And the value of their dice is "Monkey, Sword, Sword, Sword, Sword, Sword, Sword, Sword"
		Then score is 2100 and the Player has 7 of dice type "Sword"
		Then the player succesfully rerolled dice in position "1"? "false"
		Then the player succesfully rerolled dice in position "1, 2"? "true"
		  	 		