package Assignment2.Assignment2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class scoringOutcomeTest {
	PlayerClass player;
	PlayerClass player2;
	PlayerClass player3;
	PlayerClass[] players;
	MainServer server;
	int turnsMade = 0;
	int[] scores;
	int[] scores2;
	int[] scores3;
	
	@Given("a game of pirates server has started")
	public void a_game_of_pirates_server_has_started() throws InterruptedException {
		try {
			server = new MainServer(3);
			MainServer.setFortuneCards();
			players = new PlayerClass[3];
			player = new PlayerClass("Player 1");
			player2 = new PlayerClass("Player 2");
			player3 = new PlayerClass("Player 3");
			
			players[0] = player;
			players[1] = player2;
			players[2] = player3;
			
			
			Thread thread1 = new Thread() {
			    public void run() {
			    	try {
			    		TimeUnit.SECONDS.sleep(1);
						player.connectToClient();
						TimeUnit.SECONDS.sleep(1);
						player2.connectToClient();
						TimeUnit.SECONDS.sleep(1);
						player3.connectToClient();
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			};
			
			Thread thread2 = new Thread() {
			    public void run() {
			    	try {
			    		server.ConnectToTestClient1();
			    		TimeUnit.SECONDS.sleep(1);
			    		server.ConnectToTestClient2();
			    		TimeUnit.SECONDS.sleep(1);
			    		server.ConnectToTestClient3();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			};
			
			thread1.start();
			thread2.start();
			
			thread1.join();
			thread2.join();
			
			
			
			//server.ConnectToTestClient2();
			//player2.connectToClient();
		//	server.ConnectToTestClient3();
		//	player3.connectToClient();
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	
	@Given("a game of pirates with three players is in progress")
	public void  a_game_of_pirates_with_three_players_is_in_progress() {
		player = new PlayerClass("Player 1");
		player2 = new PlayerClass("Player 2");
		player3 = new PlayerClass("Player 3");
		
	}
	

	@Given("three players connect to the server")
	public void three_players_connect_to_the_server() {
		int round = 1;
		boolean lastRound1 = false;
		int round1 = 0;
		boolean lastRound2 = false;
		int round2 = 0;
		boolean lastRound3 = false;
		int round3 = 0;
		players = new PlayerClass[3];
		player = new PlayerClass("Player 1");
		player2 = new PlayerClass("Player 2");
		player3 = new PlayerClass("Player 3");
		
		players[0] = player;
		players[1] = player2;
		players[2] = player3;
		
	}
	
	
	@When("player {int} sees the scores")
	public void player_one_sees_the_scores(int p) throws InterruptedException {
		
		Thread clientThread = new Thread() {
		    public void run() {
		    	
					scores = new int[3];
					player.setLastTurn(false);
					player.clientConnection.receiveFortuneCard();
					System.out.println("Received fcc");

					//player.getGame().setFortuneCard(fcc);
					
					
				
		    	
				
		    }
		};
		
		Thread serverThread = new Thread() {
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(1);
					
					server.getPlayerServer(1).sendFortuneCard();
					System.out.println("Sent Fortune");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		
		clientThread.start();
		serverThread.start();
		System.out.println("2");
		clientThread.join();
		System.out.println("1");
		serverThread.join();
		
		
		
		turnsMade++;
		
		String[] dice = new String[8];
		dice[0] = "Monkey";
		dice[1] = "Monkey";
		dice[2] = "Sword";
		dice[3] = "Sword";
		dice[4] = "Parrot";
		dice[5] = "Parrot";
		dice[6] = "Coin";
		dice[7] = "Diamond"; 
		
		if(p == 1) {
			player.getGame().setCurrentRoll(dice);
			player.printScoreCard();

			
		}else if(p == 2) {
			player2.getGame().setCurrentRoll(dice);
			player2.printScoreCard();
			
			
		}else if(p == 3) {
			player3.getGame().setCurrentRoll(dice);
			player3.printScoreCard();
		}
		
		
		
	}
	
	@Then("player {int} sends the scores to the server")
	public void player_sends_the_scores_to_the_server(int i) {
		
		
	}
	
	@When("the server sends the scores to player {int} and {string} the final round")
	public void the_server_sends_the_scores_to_player(int p, String end) {
		turnsMade++;
		boolean fin;
		int[] scores = new int[3];
		if(end.equals("it is")) {
			fin = true;
			
		}else {
			fin = false;
			
		}
		//scores[p] = pServer.receiveScores()[0];
		//pServer.sendPlayerScores(scores, false, false);
		
	}
	
	@When("player {int} presses three to score the round")
	public void player_presses_three_to_score_the_round(int i) {
		player.simulateRoundOutput(3);
	}
	
	@When("")
	public void player_preses_to_score_the_round(int i) throws InterruptedException {
		Thread clientThread = new Thread() {
		    public void run() {
		    	try {
		    		System.out.println("*********** Ended Round With Score of ***********");
			    	player.score = player.score + player.scoreRound(1);
					TimeUnit.SECONDS.sleep(3);
					player.clientConnection.sendScore(player.score);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
				
		    }
		};
		
		Thread serverThread = new Thread() {
			public void run() {
				server.getPlayerServer(i - 1).sendPlayers(players);
				System.out.println("Server Thread");
				server.playerServer[0].receiveScores();
				System.out.println("Received Scores()");
				//server.playerServer[0].sendPlayerScores(player.scores, false, true);
				
				
			}
			
		};
		
		clientThread.start();
		serverThread.start();
		clientThread.join();
		serverThread.join();
		
		player.scores = player.clientConnection.receivePlayerScores();
		player.printPlayerScores = true;
		player.printScoreCard();
		
	}
	
	@When("player ones fortune card is {string}")
	public void player_ones_fortune_card_is(String fc) {
		player.getGame().setFortuneCard(fc);
	}
	
	@When("the value of player ones dice is {string}")
	public void the_value_of_player_ones_dice_is(String diceString) {
		String[] dice = diceString.split(", ");
		player.getGame().setCurrentRoll(dice);
		
	}
	
	
	@When("player two received the deduction")
	public void player_two_received_the_deduction() {
		players = new PlayerClass[3];
		players[0] = player;
		players[1] = player2;
		players[2] = player3;
		
		player2.setPlayers(players);
		System.out.println("sdfd: " + player2.players.length);
	}
	
	@When("player three received the deduction")
	public void player_three_received_the_deduction() {
		players = new PlayerClass[3];
		players[0] = player;
		players[1] = player2;
		players[2] = player3;
		
		player3.setPlayers(players);
	}
	
	@Then("player two has a deduction of {int}")
	public void player_two_has_a_deduction_of(int p) {
		assertEquals(p, player2.getDeductionReceived());
		
	}
	
	@Then("player three has a deduction of {int}")
	public void three(int p) {
		assertEquals(p, player3.getDeductionReceived());
		
	}
	
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
	
	@Then("the penalty being sent is {int}")
	public void the_penalty_being_sent_is(int p) {
		assertEquals(p, player.getDeductionSent());
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
