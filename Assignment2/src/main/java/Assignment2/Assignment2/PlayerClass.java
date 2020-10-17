package Assignment2.Assignment2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

public class PlayerClass implements Serializable {
	private static final long serialVersionUID = 1L; 
	static Client clientConnection;
	
	private Game game = new Game();
	PlayerClass[] players = new PlayerClass[1];
	private String name = new String();
	int playerId = 0;
	boolean fChest = false;
	int numP = 0;
	private int score = 0;
	private int tempScore = 0;
	private int diceUsed = 0;
	private int deductionToSend = 0;
	private int deductionReceived = 0;
	int inSkull = 0;
	int pLength = 0;
	boolean finalMove = false;
	
	public static void main( String[] args ) throws Exception{
		Scanner myObj = new Scanner(System.in);
		System.out.print("What is your name ? ");
		String name = myObj.next();
		PlayerClass p = new PlayerClass(name);
		p.initializePlayers();
		p.connectToClient();
		p.startGame();
			
	 }
	 
	public int getPlayerId() { return playerId; }
	 
	public void setLastTurn(boolean t) { finalMove = t; }
	 
	public boolean getLastTurn() { return finalMove; }
	 
	public void connectToClient() { clientConnection = new Client(); }

	public void connectToClient(int port) { clientConnection = new Client(port); }
	
	public void initializePlayers() {
		for (int i = 0; i < 1; i++) {
			players[i] = new PlayerClass(" ");
		}
	}
	
	public PlayerClass getPlayer() { return this; }
	
	public String getName() {return name;}
	
	public void setGame(Game g) { game = g; }
	
	public Game getGame() { return game; }
	
	public void setScore(int s) { score = s; }
	
	public int getScore() {
		if(scoreRound(1) - deductionReceived > 0) {
			score = scoreRound(1) - deductionReceived;
		
		}else {
			score = 0;
			
		}
		
		return score;
		
	}
	
	public int getScoreBeforeCalulating() { return score; }
	
	public void setDeductionReieved(int i) { deductionReceived = i; }
	
	public int getDeductionReceived() {	return deductionReceived; }
	
	public void setDeductionToSend(int i) { deductionToSend = i; }
	
	public int getDeductionSent() { return deductionToSend; }
	
	public void startGame() {
		boolean lastRound = false;
		int round = 0;
		
		while (!lastRound) {
			players = clientConnection.receivePlayer();
			if(pLength != 1) {
				players = clientConnection.receivePlayer();
				
			}
			
			lastRound = clientConnection.receiveLastTurn();
			String fcc = clientConnection.receiveFortuneCard();
			round = clientConnection.receiveTurnNo();

			game = players[playerId - 1].getGame();
			game.setFortuneCard(fcc);
			
			if (round == -1)
				break;
			System.out.println("\n \n \n ********Round Number " + round + "********");
			String[] firstRoll = players[playerId - 1].getGame().firstRoll();
			players[playerId - 1].getGame().setCurrentRoll(firstRoll);
			playRound(firstRoll);
			
		}
		
		System.out.println("Game ended");
		System.out.println(clientConnection.receiveWinning());
	
	}
	
	public void playRound(String[] roll) {
		game = players[playerId - 1].getGame();
		Scanner sc = new Scanner(System.in);
		boolean endTurn = false;
		
		while(!endTurn) {
			printScoreCard();
			
			if(game.isDead()) {
				endTurn = true;
				
			}else {
				System.out.println("");
				System.out.println("Select an action");
				
				if(!endTurn) {
					System.out.println("(1) Roll All Dice Again");
					System.out.println("(2) Pick Dice To Re-Roll");
				
				}
				
				System.out.println("(3) Score This Round");
				if(!endTurn && game.getFortuneCard().equals("TC")){
					System.out.println("(4) Selct Dice to Hold In Treasure Chest");
					System.out.println("(5) Remove Dice From Treasure Chest");
					
				}
			
				int act = sc.nextInt();
				System.out.println("");
				if(act == 1 && !endTurn) {
					boolean rooled = game.reRoll(new int[] {1,2,3,4,5,6,7,8});
					if(!rooled) {
						System.out.println("\n CAN NOT ROLL THESE DICE PLEASE CHECK WHICH ONES ARE BEING RE-ROLLED \n");
					
					}
				
				}
			
				if(act == 2 && !endTurn) {
					System.out.print("Which dice would you like to re-roll? ex 1,2,3");
					Scanner myObj = new Scanner(System.in);
					String diceToRoll = myObj.next();
					
					String[] splitD = diceToRoll.split(",", 0);
					int[] diceSplit = new int[splitD.length];
					for(int i = 0; i < splitD.length; i++) {
						diceSplit[i] = Integer.parseInt(splitD[i]);
					
					}
				
					boolean rooled = game.reRoll(diceSplit);
					if(!rooled) {
						System.out.println("\n CAN NOT ROLL THESE DICE PLEASE CHECK WHICH ONES ARE BEING RE-ROLLED \n");
				
					}
				
				}
				
				if(act == 4 && game.getFortuneCard().equals("TC") && !endTurn) {
					System.out.println("Which dice would you like to hold? ex 1,2,3");
					Scanner myObj = new Scanner(System.in);
					String diceToSave = myObj.next();
					
					String[] splitD = diceToSave.split(",", 0);
					int[] diceSplit = new int[splitD.length];
					for(int i = 0; i < splitD.length; i++) {
						diceSplit[i] = Integer.parseInt(splitD[i]);
						
					}
					
					game.saveDice(diceSplit);
					
				}
				
				if(act == 5 && game.getFortuneCard().equals("TC") && !endTurn) {
					System.out.println("Which dice would you like to remove? ex 1,2,3");
					Scanner myObj = new Scanner(System.in);
					String diceToSave = myObj.next();
					
					String[] splitD = diceToSave.split(",", 0);
					int[] diceSplit = new int[splitD.length];
					for(int i = 0; i < splitD.length; i++) {
						diceSplit[i] = Integer.parseInt(splitD[i]);
						
					}
					
					game.removeDice(diceSplit);
					
				}
			
				
				if(act == 3){ endTurn = true; }
				
			}
		
		}
		
		score = score + scoreRound(1);
		clientConnection.sendScore(score);
		System.out.println("Total score after Round is " + score);
		
	}
	
	public int scoreRound(int r) {
		tempScore = 0;
		diceUsed = 0;
		deductionToSend = 0;
		boolean fCHandled = false;
		
		if(!(game.isDead())) {
			if(game.inSkullIsland() || inSkull > 0) {
				game.enteredSkullIsland();
				inSkull++;
				tempScore = score;
				deductionToSend = deductionToSend + game.getSymbolCount("Skull") * 100;
			
			}else {
				int[] rCounts = new int[5];
				int sCount = game.getSymbolCount("Sword");
				int mCount = game.getSymbolCount("Monkey");
				int pCount = game.getSymbolCount("Parrot");
				int cCount = game.getSymbolCount("Coin");
				int dCount = game.getSymbolCount("Diamond");
				
				rCounts[0] = sCount;
				rCounts[1] = mCount;
				rCounts[2] = pCount;
				rCounts[3] = cCount;
				rCounts[4] = dCount;
			
				if(game.getFortuneCard().equals("MB")) {
					rCounts[1] = mCount + pCount;
					rCounts[2] = 0;
				
				}
			
				if(cCount < 3 && !(game.getFortuneCard().equals("CO"))) { diceUsed = diceUsed + cCount; }
			
				if(dCount < 3 && !(game.getFortuneCard().equals("DI"))) { diceUsed = diceUsed + dCount; }
			
				for(int i = 0; i < rCounts.length; i++) {
					if(rCounts[i] == 3) {
						tempScore = tempScore + 100;
						diceUsed = diceUsed + 3;
					
					}else if(rCounts[i] == 4) {
						tempScore = tempScore + 200;
						diceUsed = diceUsed + 4;
					
					}else if(rCounts[i] == 5) {
						tempScore = tempScore + 500;
						diceUsed = diceUsed + 5;
					
					}else if(rCounts[i] == 6) {
						tempScore = tempScore + 1000;
						diceUsed = diceUsed + 6;
					
					}else if(rCounts[i] == 7) {
						tempScore = tempScore + 2000;
						diceUsed = diceUsed + 7;
					
					}else if(rCounts[i] >= 8) {
						tempScore = tempScore + 4000;
						diceUsed = diceUsed + 8;
					
					}
					
				}
				
				int cBonus = 100 * cCount;
				int dBonus = 100 * dCount;
				tempScore = tempScore + cBonus + dBonus;
			
			}
			
		}
		
		if(seperateFC(game.getFortuneCard())[0].equals("SB")) {
			handleFC(seperateFC(game.getFortuneCard())[0]);
			fCHandled = true;
			
		}
		
		if(game.fullChest(diceUsed)) {
			tempScore = tempScore + 500;
			fChest = true;
			if(!(seperateFC(game.getFortuneCard())[0].equals("SB"))) {
				handleFC(seperateFC(game.getFortuneCard())[0]);
				
			}
			
			fCHandled = true;
			
		}else {
			fChest = false;
			
		}
		
		if(!fCHandled) { handleFC(seperateFC(game.getFortuneCard())[0]); }

		if(r == 2) { game.leftSkullIsland(); }
		
		return tempScore;
		
	}
	
	private void handleFC(String fc) {
		if(fc.equals("CA")) { handleCA(tempScore); }
		
		if(fc.equals("SB")) { handleSB(); }
		
		if(fc.equals("TC")) { handleTC(); }
		
	}
	
	private void handleCA(int newScore) { tempScore = newScore * 2; }
	
	private void handleSB() {
		int sCount = game.getSymbolCount("Sword");
		if(sCount >= Integer.parseInt(seperateFC(game.getFortuneCard())[1]) && !game.isDead()) {
			tempScore = tempScore + Integer.parseInt(seperateFC(game.getFortuneCard())[2]);
			if(sCount < 3) { diceUsed = diceUsed + sCount; }
			
		}else { tempScore = 0; }
		
	}
	
	private void handleTC() {
		int chestScore = 0;
		
		int[] rCounts = new int[5];
		int sCount = game.getChestDiceCount("Sword");
		int mCount = game.getChestDiceCount("Monkey");
		int pCount = game.getChestDiceCount("Parrot");
		int cCount = game.getChestDiceCount("Coin");
		int dCount = game.getChestDiceCount("Diamond");
	
		rCounts[0] = sCount;
		rCounts[1] = mCount;
		rCounts[2] = pCount;
		rCounts[3] = cCount;
		rCounts[4] = dCount;
	
		for(int i = 0; i < rCounts.length; i++) {
			if(rCounts[i] == 3) { chestScore = chestScore + 100; }
			
			else if(rCounts[i] == 4) { chestScore = chestScore + 200; }
			
			else if(rCounts[i] == 5) { chestScore = chestScore + 500; } 
			
			else if(rCounts[i] == 6) { chestScore = chestScore + 1000; }
			
			else if(rCounts[i] == 7) { chestScore = chestScore + 2000; }
			
			else if(rCounts[i] >= 8) { chestScore = chestScore + 4000; }
			
		}
		
		int cBonus = 100 * cCount;
		int dBonus = 100 * dCount;
		chestScore = chestScore + cBonus + dBonus;
		
		if(tempScore == 0) { tempScore = chestScore; }
		
	}
	
	private String[] seperateFC(String fortuneCard) {
		if(fortuneCard.length() <= 2) {
			String[] together = {fortuneCard};
			return together;
			
		}
		
		String[] seperated = new String[3];
		seperated[0] = fortuneCard.substring(0, 2);
		seperated[1] = fortuneCard.substring(2,3);
		seperated[2] = fortuneCard.substring(3, fortuneCard.length());
		
		return seperated;
		
	}
	
	public void printScoreCard() {
		String[] roll = game.getOutCome();
		System.out.println("--------------------------------------------------------------------------");
		System.out.println(" Player Name: " + name);
		System.out.println("");
		System.out.println(" Fortune Card: " + game.getFortuneCard());
		System.out.println("");
		System.out.println(" Current Roll: ");
		System.out.println(" -------  -------  -------  -------  -------  -------  ------- ------- " );
		System.out.println(" |     |  |     |  |     |  |     |  |     |  |     |  |     | |     |");
		System.out.println(" | " + roll[0].substring(0,3)  + " | " + " | " + roll[1].substring(0,3) + " | " + " | " + roll[2].substring(0,3) + " | " +  " | " + roll[3].substring(0,3) + " | " + " | " + roll[4].substring(0,3) + " | " +" | " + roll[5].substring(0,3) + " | " + " | " + roll[6].substring(0,3) + " | " + "| " + roll[7].substring(0,3) + " |");
		System.out.println(" |     |  |     |  |     |  |     |  |     |  |     |  |     | |     |");
		System.out.println(" -------  -------  -------  -------  -------  -------  ------- ------- " );
		System.out.println("");
		int roundScore = scoreRound(1);
		System.out.println(" Current Roll Score: " + roundScore);
		System.out.println("");
		System.out.print(" Full Chest: ");
		if(!fChest){ System.out.print("Not "); }
		System.out.println("Activated");
		System.out.println("");
		System.out.println(" Total Score if Dice Scored: " + (score + roundScore));
		
		if(game.getFortuneCard().equals("TC")) {
			System.out.println("\nDice Saved In The Treasure Chest: ");
			for(int i = 0; i < game.getChestDice().size(); i++) {
				System.out.print(" ------- ");
			}
			System.out.println("");
			for(int i = 0; i < game.getChestDice().size(); i++) {
				System.out.print(" |     | ");
			}
			System.out.println("");
			for(int i = 0; i < game.getChestDice().size(); i++) {
				System.out.print(" | " + game.getChestDice().get(i).substring(0,3) + " | ");
			}
			System.out.println("");
			for(int i = 0; i < game.getChestDice().size(); i++) {
				System.out.print(" |     | ");
			}
			System.out.println("");
			for(int i = 0; i < game.getChestDice().size(); i++) {
				System.out.print(" ------- ");
			}
			System.out.println("");

			
		}
		System.out.println("--------------------------------------------------------------------------\n");
		
	}
	
	public class Client{
		Socket socket;
		private ObjectInputStream jIn;
		private ObjectOutputStream jOut;
		   
		public Client() {
			try {
				socket = new Socket("localhost", 3333);
				jOut = new ObjectOutputStream(socket.getOutputStream());
				jIn = new ObjectInputStream(socket.getInputStream());

				playerId = jIn.readInt();

				System.out.println(name + " Connected as player " + playerId);
				sendPlayer();

			} catch (IOException ex) {
				System.out.println("Client failed to open");
				
			}
		
		}
		
		public Client(int portId) {
			try {
				socket = new Socket("localhost", portId);
				jOut = new ObjectOutputStream(socket.getOutputStream());
				jIn = new ObjectInputStream(socket.getInputStream());

				playerId = jIn.readInt();

				System.out.println(name + " Connected as player " + playerId);
				sendPlayer();

			} catch (IOException ex) {
				System.out.println("Client failed to open");
				
			}
			
		}
		
		public void sendPlayer() {
			try {
				jOut.writeObject(getPlayer());
				jOut.flush();
				
			} catch (IOException ex) {
				System.out.println("Player not sent");
				ex.printStackTrace();
			}
			
		}
		
		public void sendConfirmation() {
			try {
				jOut.writeBoolean(true);
				jOut.flush();
				
			}catch (IOException ex) {
				System.out.println("Confirmation not sent");
				ex.printStackTrace();
			}
			
		}
		
		public void sendString(String str) {
			try{
				jOut.writeUTF(str);
				jOut.flush();
				
			} catch (IOException ex) {
				System.out.println("String not sent");
				ex.printStackTrace();
			}
			
		}
		
		public void sendScore(int scores) {
			try {
				jOut.writeObject(score);
				jOut.flush();

			} catch (IOException e) {
				System.out.println("Score sheet not received");
				e.printStackTrace();
			}
			
		}
		
		public PlayerClass[] receivePlayer() {
			PlayerClass[] pl;
			PlayerClass p = new PlayerClass(" ");
			try {
				pl = (PlayerClass[]) jIn.readObject();
				pLength = pl.length;
				return pl;

			} catch (IOException e) {
				System.out.println("Player not received");
				e.printStackTrace();
				
			} catch (ClassNotFoundException e) {
				System.out.println("class not found");
				e.printStackTrace();
				
			}
			
			pl = new PlayerClass[1];
			return pl;
			
		}
		
		public int receiveScores() {
			try {
				System.out.println();
				return jIn.readInt();
			
			}catch(Exception e) {
				System.out.println("Score sheet not received");
				e.printStackTrace();
				
			}
			
			return 0;
			
		}
		
		public boolean receiveLastTurn() {
			try {
				System.out.print("here ");
				return jIn.readBoolean();
				
			}catch (Exception e) {
				System.out.println("Last Turn not received");
				e.printStackTrace();
				
			}
			
			return false;
		
		}
		
		public String receiveFortuneCard() {
			try {
				Object card = new Object();
				card = jIn.readObject();
				return card.toString();
				
			}catch(Exception e) {
				System.out.println("fortune not received");
				e.printStackTrace();
				
			}
			
			return null;
		}
		
		public String receiveWinning() {
			try {
				Object win = new Object();
				win = jIn.readObject();
				return win.toString();
				
			}catch(Exception e) {
				System.out.println("fortune not received");
				e.printStackTrace();
				
			}
			
			return null;
		}
		
		int entered = 0;
		public int receiveTurnNo() {
			try {
				Object turnNumber = jIn.readObject();
				return Integer.parseInt(turnNumber.toString());
				
			} catch (Exception e) {
				System.out.println("Score sheet not received in round " + entered);
				e.printStackTrace();
				
			}
			
			entered++;
			return 0;
		}
		
	}
	
	class IsItFriday {
		 String isItFriday(String today) {
		        return "Friday".equals(today) ? "TGIF" : "Nope";
		    }
	}

	public PlayerClass(String n) { name = n; }
	
}