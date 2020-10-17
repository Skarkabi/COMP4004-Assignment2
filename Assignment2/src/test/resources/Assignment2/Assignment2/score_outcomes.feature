
Feature: score_outcomes
	Player should be able to get expected socre for number of rolls
	  
  Scenario Outline: Player scoring their round after first roll checking one value
    Given a game of pirates is in progress
    When the value of their dice is <d1>
    When their fortune card is <fc>
    Then score is <outcome> and the Player has <count> of dice type <type>
    
	Examples:
  	|d1			  																								       |fc  |outcome|count|type     |
  	|"Skull, Skull, Skull, Coin, Diamond, Parrot, Sword, Sword"      |"CO"|0			|3    |"Skull"  |
  	|"Diamond, Diamond, Diamond, Sword, Monkey, Parrot, Sword, Skull"|"CO"|500    |3    |"Diamond"|
  	|"Coin, Coin, Coin, Coin, Sowrd, Monkey, Parrot, Skull"          |"DI"|700    |4    |"Coin"   |
    
   Scenario Outline: Player scoring their round after first roll checking two values
    Given a game of pirates is in progress
    When their fortune card is <fc>
    When the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type> and <count2> of dice type <type2>
    
	Examples:
  	|d1			  																										|fc  |outcome |count|type    |count2|type2    |
    |"Diamond, Diamond, Coin, Coin, Parrot, Sword, Skull, Parrot" |"CA"|800     |2    |"Coin"  |2			|"Diamond"|
		|"Monkey, Monkey, Monkey, Sword, Sword, Sword, Parrot, Parrot"|"CO"|300     |3    |"Monkey"|3     |"Sword"  |
		|"Sword, Sword, Sword, Parrot, Parrot, Parrot, Parrot, Monkey"|"CO"|400     |3    |"Sword" |4     |"Parrot" |

	Scenario Outline: Player scoring their round after second rolls
		Given a game of pirates is in progress
		When their fortune card is <fc>
		When the value of their dice is <d1>
    Then score is <outcome> and the Player has <count> of dice type <type>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count2> of dice type <type>
    
   Examples:
  	|d1			  																									 |d2			 																										|fc  |outcome|count|outcome2|count2|type    |
  	|"Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword, Sword"|"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Skull, Sword"|"CA"|600		 |1    |0       |3     |"Skull" |
 		|"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword"|"Skull, Skull, Parrot, Parrot, Parrot, Parrot, Skull, Sword"|"CA"|400		 |2		 |0       |3     |"Skull" |
 		|"Monkey, Monkey, Parrot, Sword, Sword, Parrot, Skull, Skull"|"Monkey, Monkey, Monkey, Sword, Sword, Parrot, Skull, Skull"|"CO"|100    |2    |200     |3     |"Monkey"|
 	
 	Scenario Outline: Player scoring their round after second rolls and checking two values
		Given a game of pirates is in progress
		When their fortune card is <fc>
		When the value of their dice is <d1>
    Then score is <outcome1> and the Player has <count11> of dice type <type1> and <count21> of dice type <type2>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count12> of dice type <type1> and <count22> of dice type <type2>
    
   Examples:
  	|d1			  																									 |d2			 																									 |fc  |outcome1|count11|count12|outcome2|count21|count22|type1   |type2  |
  	|"Monkey, Parrot, Parrot, Skull, Skull, Sword, Monkey, Sword"|"Monkey, Monkey, Sword, Skull, Skull, Sword, Monkey, Sword"|"CO"|100     |2      |3      |300     |2      |3      |"Monkey"|"Sword"|
 		
	Scenario Outline: Player scoring their round after three rolls
		Given a game of pirates is in progress
		When their fortune card is <fc>
		When the value of their dice is <d1>
		Then score is <outcome1> and the Player has <count1> of dice type <type>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count2> of dice type <type>
    When the value of their dice is <d3>
    Then score is <outcome3> and the Player has <count3> of dice type <type>
    
   Examples:
  	|d1			  																									 |d2			 																											|d3																														|fc  |outcome1|count1|outcome2|count2|outcome3|count3|type|
  	|"Skull, Parrot, Parrot, Parrot, Parrot, Sword, Sword, Sword"|"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Monkey, Monkey"|"Skull, Parrot, Parrot, Parrot, Parrot, Skull, Skull, Monkey"|"CO"|400     |1     |300     |2     |0       |3     |"Skull"|
 		
 	Scenario Outline: Player scoring their round after three rolls and checking value for two dice symbols
		Given a game of pirates is in progress
		When their fortune card is <fc>
		When the value of their dice is <d1>
		Then score is <outcome1> and the Player has <count11> of dice type <type1> and <count12> of dice type <type2>
    When the value of their dice is <d2>
    Then score is <outcome2> and the Player has <count21> of dice type <type1> and <count22> of dice type <type2>
    When the value of their dice is <d3>
    Then score is <outcome3> and the Player has <count31> of dice type <type1> and <count32> of dice type <type2>
    
  Examples:
  	|d1			  																									|d2			 																								 |d3																									 |fc  |outcome1|count11|count12|outcome2|count21|count22|outcome3|count31|count32|type1 |type2  |
  	|"Sword, Sword, Coin, Monkey, Monkey, Parrot, Parrot, Skull"|"Sword, Sword, Coin, Coin, Monkey, Parrot, Sword, Skull"|"Sword, Sword, Coin, Coin, Coin, Sword, Sword, Skull"|"CO"|200     |2      |2      |500     |3      |3      |800     |4      |4      |"Coin"|"Sword"|
 		 		