package Assignment2.Assignment2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class MainServer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int turnsMade;
	private boolean finalTurn = false;
	PlayerClass winningPlayer = new PlayerClass("");
	static String[] fortuneCards = new String[35];
	int cardsTaken = 0;
	Game game = new Game();
	
	Server[] playerServer;
	PlayerClass[] players;
	ServerSocket ss;

	int numPlayers;
	static int numConnectionNeeded;
	
	public static void setFortuneCards() {
		for (int i = 0; i < 5; i++) {
			fortuneCards[i] = "TC";
			
		}
		
		for (int i = 5; i < 10; i++) {
			fortuneCards[i] = "CA";
			
		}
		
		for (int i = 10; i < 15; i++) {
			fortuneCards[i] = "SO";
			
		}
		
		for (int i = 15; i < 20; i++) {
			fortuneCards[i] = "CO";
			
		}
		
		for (int i = 20; i < 25; i++) {
			fortuneCards[i] = "DI";
			
		}
		
		for (int i = 25; i < 30; i++) {
			fortuneCards[i] = "MB";
			
		}
		
		fortuneCards[30] = "SB2300";
		fortuneCards[31] = "SB3500";
		fortuneCards[32] = "SB41000";
		fortuneCards[33] = "SK2";
		fortuneCards[34] = "SK1";
		
		fortuneCards = Randomize(fortuneCards);
		
	}
	
	public static String[] Randomize(String[] arr) {
	    String[] randomizedArray = new String[arr.length];
	    System.arraycopy(arr, 0, randomizedArray, 0, arr.length);
	    Random rgen = new Random();

	    for (int i = 0; i < randomizedArray.length; i++) {
	        int randPos = rgen.nextInt(randomizedArray.length);
	        String tmp = randomizedArray[i];
	        randomizedArray[i] = randomizedArray[randPos];
	        randomizedArray[randPos] = tmp;
	        
	    }

	    return randomizedArray;
	}
	
	public static void main( String[] args ) throws Exception {
		setFortuneCards();
		
		System.out.print("How many players are connected? ");
	    Scanner sc = new Scanner(System.in);
	    int numPlayer = sc.nextInt();
	    
	    MainServer server = new MainServer(numPlayer);
		server.ConnectToClients();   
		server.gameLoop();
		
		System.exit(0);
			
	}
	
	public MainServer(int numPlayersToConnect) throws ClassNotFoundException {
		playerServer = new Server[numPlayersToConnect];
		players = new PlayerClass[numPlayersToConnect];
		System.out.println("Server is starting");
		numPlayers = 0;
		numConnectionNeeded = numPlayersToConnect;
		turnsMade = 0;
		
		for(int i = 0; i < players.length; i++) {
			players[i] = new PlayerClass("");
			
		}
		
		try {
			ss = new ServerSocket(3333);
			
		}catch (IOException e) {
			System.out.println("Connection Failed");
			
		}
		
	}
	
	public void ConnectToClients() throws ClassNotFoundException{
		try {
			System.out.println("Waiting for " + numConnectionNeeded + " players to connect...");
			while(numPlayers < numConnectionNeeded) {
				Socket s = ss.accept();
				numPlayers++;
				
				Server server = new Server (s, numPlayers);
				server.jOut.writeInt(server.pId);
				server.jOut.flush();
				
				PlayerClass in = (PlayerClass) server.jIn.readObject();
				System.out.println("Player " + server.pId + " ~ " + in.getName() + " ~ has joined");
			
				players[server.pId - 1] = in;
				playerServer[numPlayers - 1] = server;
				
			}
			
			System.out.println(numPlayers + " players have joined the game");
			
			for(int i = 0; i < playerServer.length; i++) {
				Thread t = new Thread(playerServer[i]);
				t.start();
				
			}
				
		} catch (IOException ex) {
			System.out.println("Could not connect " + numPlayers + " players");
		
		}
		
	}
	
	public void gameLoop() {
		int topScore = 0;
		boolean lastMove = false;
		
		try {
			while(!finalTurn) {
				turnsMade++;
				if(lastMove) { finalTurn = true; }
				
				System.out.println("\n*****************************************");
				System.out.println("Round number " + turnsMade);
				for(int i = 0; i < playerServer.length; i++) {
					playerServer[i].sendPlayers(players);
					if(lastMove) { playerServer[i].sendLastRound(true); }
					
					else { playerServer[i].sendLastRound(false); }
					
					playerServer[i].sendFortuneCard();
					playerServer[i].sendTurnNo(turnsMade);
					cardsTaken++;
					players[i].setScore(playerServer[i].receiveScores()[0]);
					
					System.out.println("\nPlayer " + (i + 1) + " completed turn and their score is " + players[i].getScoreBeforeCalulating());
					
				}
				
				for(int i = 0; i < players.length; i++) {
					if(topScore < players[i].getScoreBeforeCalulating()) {
						topScore = players[i].getScoreBeforeCalulating();
						winningPlayer = players[i];
						
					}
					
					if(players[i].getScoreBeforeCalulating() >= 6000) { lastMove = true; }
					
				}
			
			}
			
			System.out.println("The Winner is " + winningPlayer.getName() + " With a score of " + winningPlayer.getScoreBeforeCalulating());
			for(int i = 0; i < playerServer.length; i++) {
				playerServer[i].sendWinner("The Winner is Player " + winningPlayer.getPlayerId() + ", " + winningPlayer.getName() + ", With a score of " + winningPlayer.getScoreBeforeCalulating());
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}
	
	public class Server implements Runnable{
		private Socket socket;
		private ObjectInputStream jIn;
		private ObjectOutputStream jOut;
		private int pId;

		public Server(Socket s, int id) {
			socket = s;
			pId = id;
			try {
				jOut = new ObjectOutputStream(socket.getOutputStream());
				jIn = new ObjectInputStream(socket.getInputStream());
				
			}catch(IOException e){
				System.out.println("Connection Failed!");
				e.printStackTrace();
				
			}
			
		}
		
		public void run() {
			try {
				while(true) {}
		
			}catch (Exception e) {
				{
					System.out.println("Run Failed!");
					e.printStackTrace();
					
				}
			
			}
		
		}
		
		public void sendPlayers(PlayerClass[] pl) {
			try {
				System.out.println("Sending player now");
				for(PlayerClass p : pl) {
					jOut.writeObject(pl);
					jOut.flush();
					
				}

			} catch (IOException ex) {
				System.out.println("Players not sent");
				ex.printStackTrace();
				
			}

		}
		
		public void sendFortuneCard() {
			try {
				if(cardsTaken == 35) {
					cardsTaken = 0;
					setFortuneCards();
					
				}
				
				Object card = fortuneCards[cardsTaken];
				jOut.writeObject(card);
				jOut.flush();
				
			}catch (IOException ex) {
				System.out.println("Players not sent");
				ex.printStackTrace();
				
			}
			
		}
		
		public void sendTurnNo(int r) {
			try {
				jOut.writeObject(r);
				jOut.flush();
				
			} catch (Exception e) {
				System.out.println("turn number not sent");
				e.printStackTrace();
				
			}
		}
		
		public boolean receiveConfirmation() {
			try {
				return jIn.readBoolean();
				
			}catch (Exception e) {
				System.out.println(e);
				
			}
			
			return false;
			
		}
		
		public int[] receiveScores() {
			try {
				int[] sc = new int [numPlayers];
				for(int i = 0; i < 1; i++) {
					Object scc = jIn.readObject();
					sc[i] = Integer.parseInt(scc.toString());
					
				}
				
				return sc;
				
			}catch (Exception e) {
				System.out.println("Score sheet not receicved scores");
				e.printStackTrace();
				
			}
			
			return null;
			
		}
		
		public void sendLastRound(boolean l) {
			try {
				jOut.writeBoolean(l);
				jOut.flush();
				
			} catch (Exception e) {
				System.out.println("turn number not sent");
				e.printStackTrace();
				
			}
			
		}
		
		public void sendWinner(String s) {
			try {
				jOut.writeObject(s);
				jOut.flush();
			
			}catch (IOException ex) {
				System.out.println("Players not sent");
				ex.printStackTrace();
				
			}
			
		}

		public void sendScores(PlayerClass[] pl) {
			try {
				for(int i = 0; i < pl.length; i++) {
					jOut.writeInt(pl[i].getScoreBeforeCalulating());
					
				}
				
				jOut.flush();
				
			}catch (Exception e) {
				System.out.println("Score not sent");
				e.printStackTrace();
				
			}
			
		}
		
	}
	
}