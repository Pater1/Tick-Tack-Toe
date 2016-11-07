package ttt;

import java.io.IOException;

import csc110.tictactoe.player.ai.neural.Brain;
//import edu.neumont.csc110.a.utilities.ConsoleUI;
import patpackages.input.*;
import ttt.Board;

public class Player {
	public String name;
	public Brain ai;
	public int value;
	
	public boolean playerWin(Board gamePlay) {
		if((gamePlay.getBase()[0][0] == gamePlay.getBase()[0][1]) && (gamePlay.getBase()[0][0] == gamePlay.getBase()[0][2]) && (gamePlay.getBase()[0][0] != 0)) {
			return true;
		} else if((gamePlay.getBase()[1][0] == gamePlay.getBase()[1][1]) && (gamePlay.getBase()[1][0] == gamePlay.getBase()[1][2]) && (gamePlay.getBase()[1][0] != 0)) {
			return true;
		} else if((gamePlay.getBase()[2][0] == gamePlay.getBase()[2][1]) && (gamePlay.getBase()[2][0] == gamePlay.getBase()[2][2]) && (gamePlay.getBase()[2][0] != 0)) {
			return true;
		} else if((gamePlay.getBase()[0][0] == gamePlay.getBase()[1][0]) && (gamePlay.getBase()[0][0] == gamePlay.getBase()[2][0]) && (gamePlay.getBase()[0][0] != 0)) {
			return true;
		} else if((gamePlay.getBase()[0][1] == gamePlay.getBase()[1][1]) && (gamePlay.getBase()[0][1] == gamePlay.getBase()[2][1]) && (gamePlay.getBase()[0][1] != 0)) {
			return true;
		} else if((gamePlay.getBase()[0][2] == gamePlay.getBase()[1][2]) && (gamePlay.getBase()[0][2] == gamePlay.getBase()[2][2]) && (gamePlay.getBase()[0][2] != 0)) {
			return true;
		} else if((gamePlay.getBase()[0][0] == gamePlay.getBase()[1][1]) && (gamePlay.getBase()[0][0] == gamePlay.getBase()[2][2]) && (gamePlay.getBase()[0][0] != 0)) {
			return true;
		} else if((gamePlay.getBase()[2][0] == gamePlay.getBase()[1][1]) && (gamePlay.getBase()[2][0] == gamePlay.getBase()[0][2]) && (gamePlay.getBase()[2][0] != 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	public int turn(Board gamePlay) throws IOException {
		if(ai == null){
			return playerTurn(gamePlay); 
		}else{
			return aiTurn(gamePlay); 
		}
	}

	private int playerTurn(Board gamePlay) throws IOException {
		int userSelection = 0;
		do{
			userSelection = ConsoleUI.promptForInt("Which spot would you like to select " + name + "?", 1, 9);
		}while(!gamePlay.validInput(userSelection));
		return userSelection;
	}
	private int aiTurn(Board gamePlay) throws IOException {
		ai.UpdateInputs(gamePlay);
		
		int[] air = ai.GetOutputs();
		int out = (air[0]+1)*3 + (air[1]+1) + 1;

		while(out > 9 || !gamePlay.validInput(out)){
			out = ((out + 1) % 9)+1;
		}
		
		return out;
	}
}
