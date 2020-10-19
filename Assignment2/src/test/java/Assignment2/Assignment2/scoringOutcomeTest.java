package Assignment2.Assignment2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class scoringOutcomeTest {
	PlayerClass player;
	
	@Given("^a game of pirates is in progress$")
	public void a_game_of_pirates_is_in_progress() {
		player = new PlayerClass("");
		
	}
	
	@When("the value of their dice is {string}")
	public void the_value_of_their_dice_is(String diceString) {
		String[] dice = diceString.split(", ");
		player.getGame().setCurrentRoll(dice);
		
	}
	
	@When("their fortune card is {string}")
	public void their_fortune_card_is(String fc) {
		player.getGame().setFortuneCard(fc);
		
	}
	
	@When("they save the dice in position {string}")
	public void they_save_the_dice_in_position(String pos) {
		String[] posSplit = pos.split(", ");
		int[] numPos = new int[posSplit.length];
		for(int i = 0; i < posSplit.length; i++) {
			numPos[i] = Integer.parseInt(posSplit[i]);
			
		}
		
		player.getGame().saveDice(numPos);
	}
	
	@When("they remove the dice in position {string}")
	public void they_remove_the_dice_in_position(String pos) {
		String[] posSplit = pos.split(", ");
		int[] numPos = new int[posSplit.length];
		for(int i = 0; i < posSplit.length; i++) {
			numPos[i] = Integer.parseInt(posSplit[i]);
			
		}
		
		player.getGame().removeDice(numPos);
	}
	
	@When("they {string} their turn")
	public void they_end_their_turn(String s) {
		if(s.equals("end")) { player.getGame().setTurn(false); } 
	}
	
	@When("their score is {int}")
	public void their_score_is(int score) {
		player.setScore(score);
		
	}
	
	@When("it is their second turn")
	public void it_is_theirs_srcond_turn() {
		player.getGame().setFirstTurn(false);
	}
	
	@Then("the full chest is {string}")
	public void the_full_chest_is(String chest) {
		if(chest.equals("Active")) {
			assertTrue(player.getGame().getFullChestActive());
			
		}else {
			assertFalse(player.getGame().getFullChestActive());
		}
	}
	
	@Then("score is {int} and the player {string} have {int} swords")
	public void score_is_and_the_player_does_not_have_swords(int expectedScore, String action, int count) {
		int score = player.getScore();
		boolean dead = player.getGame().isDead();
		int typeCount = player.getGame().getSymbolCount("Sword");
		assertEquals(expectedScore, score);
		if(action.equals("does")) {
			assertEquals(count, typeCount);
		}else {
			assertNotEquals(count, typeCount);
		}
		
		player.printScoreCard();
		
	}
	
	@Then("the score is {int}")
	public void the_score_is(int score) {
		assertEquals(score, player.getScore());
		player.printScoreCard();
	}
	
	@Then("the score is not {int}")
	public void the_score_is_not(int score) {
		assertNotEquals(0, player.getScore());
		
	}
	
	@Then("their are {string} than or equal to {int} swords")
	public void their_are_than_swords(String equal, int count) {
		if(equal.equals("greater")) {
			assertTrue(player.getGame().getSymbolCount("Sword") >= count);
			
		}else {
			assertTrue(player.getGame().getSymbolCount("Sword") <= count);
				
		}
			
	}
	
	@Then("the turn is over? {string}")
	public void the_turn_is_over(String over) {		
		if(over.equals("false")) {
			assertFalse(player.getGame().isTurnOver());
			
		}else {
			assertTrue(player.getGame().isTurnOver());
		}
		
	}
	
	@Then("the player is dead? {string}")
	public void the_player_is_dead(String outcome) {
		if(outcome.equals("true")) {
			assertTrue(player.getGame().isDead());
			
		}else {
			assertFalse(player.getGame().isDead());
		}
		
	}
	
	@Then("the dice rerolled in position {int} is no longer a {string}")
	public void the_dice_rerolled_in_position_is_no_longer_a(int pos, String type) {
		String[] currentDice = player.getGame().getOutCome();
		assertNotEquals(currentDice[pos - 1], type);
		player.printScoreCard();
	}
	
	@Then("the player succesfully rerolled dice in position {string}? {string}")
	public void player_rerolls_dice_in(String pos, String outcome) {
		String[] posSplit = pos.split(", ");
		int[] numPos = new int[posSplit.length];
		for(int i = 0; i < posSplit.length; i++) {
			numPos[i] = Integer.parseInt(posSplit[i]);
			
		}
		
		assertEquals(String.valueOf(player.getGame().reRoll(numPos)), outcome);
	}
	

	@Then("score is {int} and the Player has {int} of dice type {string}")
	public void score_is_and_Player_is_dead_is(int expectedScore, int count, String type) {
		int score = player.getScore();
		boolean dead = player.getGame().isDead();
		int typeCount = player.getGame().getSymbolCount(type);
		assertEquals(expectedScore, score);
		assertEquals(count, typeCount);
		if(type.equals("Skull") && typeCount == 3) {
			assertTrue(dead);
		}else {
			assertFalse(dead);
		}
		
		player.printScoreCard();
		
	}
	
	@Then("score is {int} and the Player has {int} of dice type {string} and {int} of dice type {string}")
	public void score_is_and_Player_has_of_dice_type_and_of_dice_type(int expectedScore, int count, String type, int count2, String type2) {
		int score = player.getScore();
		boolean dead = player.getGame().isDead();
		int typeCount = player.getGame().getSymbolCount(type);
		int typeCount2 = player.getGame().getSymbolCount(type2);
		assertEquals(expectedScore, score);
		assertEquals(count, typeCount);
		assertEquals(count2, typeCount2);
		if(type.equals("Skull") && typeCount == 3) {
			assertTrue(dead);
		}else {
			assertFalse(dead);
		}
		
		player.printScoreCard();
		
	}
	
	@Then("score is {int} and the treasure chest has {int} {string} dice inside")
	public void score_is_and_the_treasure_chest_has_dice_inside(int expectedScore, int count, String type){
		int score = player.getScore();
		int typeCount = player.getGame().getChestDiceCount(type);
		assertEquals(expectedScore, score);
		assertEquals(count, typeCount);
		player.printScoreCard();
		
	}
	
	@Then("score is {int} and the treasure chest has {int} {string} and {int} {string} dice inside")
	public void score_is_and_the_treasure_chest_has_and_dice_inside(int expectedScore, int count1, String type1, int count2, String type2){
		int score = player.getScore();
		boolean dead = player.getGame().isDead();
		int typeCount1 = player.getGame().getChestDiceCount(type1);
		int typeCount2 = player.getGame().getChestDiceCount(type2);
		assertEquals(expectedScore, score);
		assertEquals(count1, typeCount1);
		assertEquals(count2, typeCount2);
		assertFalse(dead);
		player.printScoreCard();
	}
	
}
