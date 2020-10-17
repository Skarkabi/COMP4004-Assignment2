
Feature: score_outcomes
	Player should be able to get expected socre for number of rolls
	
  Scenario Outline: Player scoring their round after first roll checking one value
    Given a game of pirates is in progress
    When the value of their dice is <d1>, <d2>, <d3>, <d4>, <d5>, <d6>, <d7>, and <d8>
    When their fortune card is <fc>
    Then score is <outcome> and the Player has <count> of dice type <type>
    
	Examples:
  	|d1			  |d2			  |d3			|d4		 |d5			 |d6			|d7			|d8			 |fc  |outcome |count|type   |
  	|"Skull"  |"Skull"  |"Skull"|"Coin"|"Diamond"|"Parrot"|"Sword"|"Sword" |""  |0			 |3    |"Skull"|
    
   Scenario Outline: Player scoring their round after first roll checking two values
    Given a game of pirates is in progress
    When the value of their dice is <d1>, <d2>, <d3>, <d4>, <d5>, <d6>, <d7>, and <d8>
    When their fortune card is <fc>
    Then score is <outcome> and the Player has <count> of dice type <type> and <count2> of dice type <type2>
    
	Examples:
  	|d1			  |d2			  |d3			|d4		 |d5			 |d6			|d7			|d8			 |fc  |outcome |count|type   |count2|type2    |
    |"Diamond"|"Diamond"|"Coin" |"Coin"|"Parrot" |"Sword" |"Skull"|"Parrot"|"CA"|800     |2    |"Coin" |2			|"Diamond"|


	Scenario Outline: Player scoring their round after second roll
		Given a game of pirates is in progress
		When the value of their dice is <d1>, <d2>, <d3>, <d4>, <d5>, <d6>, <d7>, and <d8>
    When their fortune card is <fc>
    When the value of their dice is <d21>, <d22>, <d23>, <d24>, <d25>, <d26>, <d27>, and <d28>
    Then score is <outcome> and the Player has <count> of dice type <type>
    
    Examples:
  	|d1			  |d2			 |d3			|d4		 	 |d5			 |d6			|d7			|d8			|d21		|d22		 |d23			|d24		 |d25			|d26		 |d27		 |d28		 |fc |outcome|count|type   |
  	|"Skull"  |"Parrot"|"Parrot"|"Parrot"|"Parrot"|"Sword" |"Sword"|"Sword"|"Skull"|"Parrot"|"Parrot"|"Parrot"|"Parrot"|"Skull" |"Skull"|"Sword"|"CA"|0		 	 |3    |"Skull"|
 		|"Skull"  |"Skull" |"Parrot"|"Parrot"|"Parrot"|"Parrot"|"Sword"|"Sword"|"Skull"|"Skull" |"Parrot"|"Parrot"|"Parrot"|"Parrot"|"Skull"|"Sword"|"CA"|0      |3    |"Skull"|