import java.io.IOException;

import edu.neumont.csc110.a.utilities.ConsoleUI;

public class Player {
	public String name;
	//public boolean turn;
	public int value;
	
	public boolean playerWin(Board gamePlay) {
		if((gamePlay.base[0][0] == gamePlay.base[0][1]) && (gamePlay.base[0][0] == gamePlay.base[0][2]) && (gamePlay.base[0][0] != 0)) {
			return true;
		} else if((gamePlay.base[1][0] == gamePlay.base[1][1]) && (gamePlay.base[1][0] == gamePlay.base[1][2]) && (gamePlay.base[1][0] != 0)) {
			return true;
		} else if((gamePlay.base[2][0] == gamePlay.base[2][1]) && (gamePlay.base[2][0] == gamePlay.base[2][2]) && (gamePlay.base[2][0] != 0)) {
			return true;
		} else if((gamePlay.base[0][0] == gamePlay.base[1][0]) && (gamePlay.base[0][0] == gamePlay.base[2][0]) && (gamePlay.base[0][0] != 0)) {
			return true;
		} else if((gamePlay.base[0][1] == gamePlay.base[1][1]) && (gamePlay.base[0][1] == gamePlay.base[2][1]) && (gamePlay.base[0][1] != 0)) {
			return true;
		} else if((gamePlay.base[0][2] == gamePlay.base[1][2]) && (gamePlay.base[0][2] == gamePlay.base[2][2]) && (gamePlay.base[0][2] != 0)) {
			return true;
		} else if((gamePlay.base[0][0] == gamePlay.base[1][1]) && (gamePlay.base[0][0] == gamePlay.base[2][2]) && (gamePlay.base[0][0] != 0)) {
			return true;
		} else if((gamePlay.base[2][0] == gamePlay.base[1][1]) && (gamePlay.base[2][0] == gamePlay.base[0][2]) && (gamePlay.base[2][0] != 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int turn(Board gamePlay) throws IOException {
		return playerTurn(gamePlay); 
	}
	
	private int playerTurn(Board gamePlay) throws IOException {
		int userSelection = 0;
	//	boolean validInput = false;
		do{
			userSelection = ConsoleUI.promptForInt("Which spot would you like to select " + name + "?", 1, 9);
		}while(!gamePlay.validInput(userSelection));
		return userSelection;
	}
}
