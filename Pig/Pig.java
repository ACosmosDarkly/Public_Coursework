import java.security.SecureRandom;

public class Pig implements Game {

	private int player1_score = 0;
	private int player2_score = 0;
	private boolean is_player1s_turn = true, playerQuit = false;;
	private SecureRandom die = new SecureRandom();
	private int score_limit;
	private PigGUI guiAccess;
	
	public Pig(int score_limit, PigGUI guiAccess) {
		this.score_limit = score_limit;
		this.guiAccess = guiAccess; // Gives access to protected variables and public methods
	}

	@Override
	public void play() throws InterruptedException {
		//until a player reaches the score_limit
		while( !isGameOver() && playerQuit == false) {
			//current player plays
			//they get their points
			if(is_player1s_turn) {
				guiAccess.currentPlay.setText("Player One's Turn");
				Thread.sleep(1200);
				player1_score += takeTurn();
				guiAccess.playerOnePoints.append(String.valueOf(player1_score+"\n"));
			}
			else {
				guiAccess.currentPlay.setText("Player Two's Turn");
				Thread.sleep(1200);
				player2_score += takeTurn();
				guiAccess.playerTwoPoints.append(String.valueOf(player2_score+"\n"));
			}
			
			//then we switch players
			is_player1s_turn = !is_player1s_turn;
		}
		
		if(playerQuit == true) {
			guiAccess.currentPlay.setText("A player has quit the game. Closing...");
			Thread.sleep(5000);
			guiAccess.quitGUI();
		}
		else if (player1_score > player2_score) {
			guiAccess.currentPlay.setText("Player 1 wins with "+player1_score+" points!");
			Thread.sleep(5000);
			guiAccess.currentPlay.setText("Closing...");
			Thread.sleep(3000);
			guiAccess.quitGUI();
		}
		else {
			guiAccess.currentPlay.setText("Player 2 wins with "+player2_score+" points!");
			Thread.sleep(5000);
			guiAccess.currentPlay.setText("Closing...");
			Thread.sleep(3000);
			guiAccess.quitGUI();
		}
	}
	
	/* takeTurn returns the number of points earned by
	the current player. */
	@Override
	public int takeTurn() throws InterruptedException {
		//immediately roll
		int points = 0;
		int most_recent_roll = rollDie();
		
		//until the player chooses hold or rolls 1
		//	they keep gaining points
		while(most_recent_roll != 1) {
			//Accumulate points
			points = points + most_recent_roll;
			

			guiAccess.currentPlay.setText("Rolled: "+most_recent_roll+". At risk points: "+points);
			Thread.sleep(1200);

			//player chooses to hold or roll
			guiAccess.currentPlay.setText("roll or hold?");

			// Waits until a button selection is made
			while (guiAccess.buttonSelection == null) {
				Thread.sleep(500);
			}

			if(guiAccess.buttonSelection.equals("hold")) {
				// Reset the button selection in the GUI and the 
				guiAccess.buttonSelection = null;
				break;
			}
			else if(guiAccess.buttonSelection.equals("quit")) {
				playerQuit = true;
				break;
			}
			else {
				// Reset button choice to null 
				guiAccess.buttonSelection = null;
				//Roll again
				most_recent_roll = rollDie();
			}
		}
		
		//if they roll 1 the turn is over and they lose all points
		if(most_recent_roll == 1) { 
			points = 0; 
		}

		guiAccess.currentPlay.setText("Turn is over.");
		Thread.sleep(1200);
		guiAccess.currentPlay.setText("Last rolled: "+most_recent_roll);
		Thread.sleep(1200);
		guiAccess.currentPlay.setText("Points gained: "+points);
		Thread.sleep(1200);

		return points;
	}
	
	@Override
	public boolean isGameOver() {
		if(player1_score >= score_limit) {
			return true;
		}
		else if(player2_score >= score_limit) {
			return true;
		}
		else {
			return false;
		}
	}

	private int rollDie() {
		//Returns a random number between 1 and 6 inclusive
		return die.nextInt(6)+1;
	}
	
	public void testRandomNumbers() {
		int upperBound = 6;
		int randomInt = rollDie();
		for(int i=0; i<100; i++) {
			randomInt = rollDie();
			System.out.println(randomInt);
		}
	}

}