package ttt;

import java.io.IOException;

import csc110.tictactoe.player.ai.neural.Overmind;
import csc110.tictactoe.player.ai.neural.Overmind.Difficulty;
//import edu.neumont.csc110.a.utilities.ConsoleUI;
import patpackages.input.*;
import csc110.wrappers.Vector2Int;

public class Game {
	Board gamePlay = new Board();
	Player player1 = new Player();
	Player player2 = new Player();
	int[][] example = new int[][] {
		{1,2,3},
		{4,5,6},
		{7,8,9}
	};
	
	Overmind ovm = Overmind._newOVM(Vector2Int.newVector(15, 10));
	
	public void run() throws IOException {
		TrainAI();
		while(true) {
			gamePlay.initializer();
											//option 1				option 2
			String[] options = new String[] {"1- 1 Player with AI", "2- 2 Players"};
			
			System.out.println("The game will be played by selecting a number 1-9 to make your choice.");
			System.out.println("This is the grid:");
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					System.out.print(example[i][j]);
				}
				System.out.println();
			}
			int playerChoice = ConsoleUI.promptForMenuSelection(options, true);
			if (playerChoice == 0) {
				System.exit(0);
			} else if(playerChoice == 1) {
				player1.ai = null;
				player1.name = ConsoleUI.promptForInput("What is the name of Player 1?", false);
				ovm.difficulty = Difficulty.values()[ConsoleUI.promptForMenuSelection(new String[]{"1-easy", "2-medium","3-hard"},false)];
				player2.ai = ovm.GetNextBrain();
			} else if(playerChoice == 2) {
				player1.ai = null;
				player2.ai = null;
				player1.name = ConsoleUI.promptForInput("What is the name of Player 1?", false);
				player2.name = ConsoleUI.promptForInput("What is the name of Player 2?", false);
			}
			
			PlayGame(true);
		}
	}

	public void PlayGame(boolean showOutput) throws IOException {
		player1.value = -1;
		player2.value = 1;
		boolean p1turn = true;		
		
		while(true) {
			if(showOutput){
				gamePlay.printBoard();
				System.out.println("");
			}
			if(p1turn) {
				gamePlay.boardEditor(player1.value, player1.turn(gamePlay));
				if(player1.playerWin(gamePlay)) {
					if(showOutput){
						gamePlay.printBoard();
						System.out.println(player1.name + " won!");
					}

					if(player1.ai != null){
						player1.ai.SetFitness(1);
					}
					if(player2.ai != null){
						player2.ai.SetFitness(-1);
					}
					
					break;
				}
				p1turn = false;
			} else {
				gamePlay.boardEditor(player2.value, player2.turn(gamePlay));
				if(player2.playerWin(gamePlay)) {
					if(showOutput){
						gamePlay.printBoard();
						System.out.println(player2.name + " won!");
					}

					if(player1.ai != null){
						player1.ai.SetFitness(-1);
					}
					if(player2.ai != null){
						player2.ai.SetFitness(1);
					}
					
					break;
				}
				p1turn = true;
			
			} 
			if(gamePlay.boardFull()) {
				if(showOutput){
					gamePlay.printBoard();
					System.out.println("It is a stalemate!");
				}

				if(player1.ai != null){
					player1.ai.SetFitness(0);
				}
				if(player2.ai != null){
					player2.ai.SetFitness(0);
				}
				
				break;
			}
		}
		


		if(player1.ai != null){
			ovm.ReturnBrain(player1.ai.Serialize());
		}
		if(player2.ai != null){
			ovm.ReturnBrain(player2.ai.Serialize());
		}
	}

	private void TrainAI() throws IOException{
		ovm.difficulty = Difficulty.any;
		System.out.println("training... please wait");
		int trainingGenerations = 2000, mod = 100;
		for(int i = 0; i < trainingGenerations; i++){
			if(i%mod == 0) System.out.print("-");
		}
		System.out.println("|");
		for(int i = 0; i < trainingGenerations; i++){
			gamePlay.initializer();
			player1.ai = ovm.GetNextBrain();
			player2.ai = ovm.GetNextBrain();
			PlayGame(false);

			if(i%mod == 0) System.out.print("*");
		}
		System.out.println("|");
		System.out.println("training comlete!");
	}
}
