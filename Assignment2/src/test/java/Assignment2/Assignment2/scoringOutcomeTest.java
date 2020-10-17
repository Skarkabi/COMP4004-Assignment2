package Assignment2.Assignment2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
		player.printScoreCard();
		
		
	}
	
	@When("their fortune card is {string}")
	public void their_fortune_card_is(String fc) {
		player.getGame().setFortuneCard(fc);
		
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
	
	

}
