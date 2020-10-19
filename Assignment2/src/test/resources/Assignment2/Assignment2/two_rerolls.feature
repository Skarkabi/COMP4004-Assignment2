Feature: Title of your feature
  I want to use this template for my feature file

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

