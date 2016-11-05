import java.io.IOException;
import java.util.ArrayList;

import edu.neumont.csc110.a.utilities.ConsoleUI;

public class Game {
	Board gamePlay = new Board();
	Player player1 = new Player();
	Player player2 = new Player();
	int[][] example = new int[][] {
		{1,2,3},
		{4,5,6},
		{7,8,9}
	};
	ArrayList<Integer> priorSelection = new ArrayList<Integer>();
	
	public void run() throws IOException {
		while(true) {
			gamePlay.initializer();
			for(int i=priorSelection.size();i>0;i--) {
				priorSelection.remove(i-1);
			}
											//option 1				option 2
			String[] options = new String[] {"1 Player with AI", "2 Players"};
			
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
				//single player ai stuff
				System.out.println("Needs to be implemented");
			} else if(playerChoice == 2) {
				twoPlayerGame();
			}
		}
	}

	public void twoPlayerGame() throws IOException {
		player1.name = ConsoleUI.promptForInput("What is the name of Player 1?", false);
		player1.win = false;
		player1.value = -1;
		player2.name = ConsoleUI.promptForInput("What is the name of Player 2?", false);
		player2.win = false;
		player2.value = 1;
		boolean notFinished = true;
		player1.turn = true;
		//int userSelection = 0;
		//boolean validInput = false;
		do {
			printBoard();
			if(player1.turn) {
				playerTurn(player1);
				player1.turn = false;
//				userSelection = 0;
//				validInput = false;
//				while(!validInput){
//					userSelection = ConsoleUI.promptForInt("Which spot would you like to select " + player1.name + "?", 1, 9);
//					if(!priorSelection.contains(userSelection)) {
//						priorSelection.add(userSelection);
//						validInput = true;
//					}
//				}
//				boardEditor(-1, userSelection);
//				player1.turn = false;
//				player1.win = didThePlayerWin();
			} else {
				playerTurn(player2);
				player1.turn = true;
//				userSelection = 0;
//				validInput = false;
//				while(!validInput){
//					userSelection = ConsoleUI.promptForInt("Which spot would you like to select " + player2.name + "?", 1, 9);
//					if(!priorSelection.contains(userSelection)) {
//						priorSelection.add(userSelection);
//						validInput = true;
//					}
//				}
//				boardEditor(1, userSelection);
//				player1.turn = true;
//				player2.win = didThePlayerWin();
			}
			if(player1.win || player2.win) {
				notFinished = false;
			} else if(priorSelection.size()==9) {
				notFinished = false;
				printBoard();
				System.out.println("It is a stalemate!");
			}
			//a player goes
			//check if win
			//swap players 
		}while(notFinished);
		if(player1.win) {
			printBoard();
			System.out.println(player1.name + " won!");
		} else if(player2.win) {
			printBoard();
			System.out.println(player2.name + " won!");
		}
	}
	
	public void playerTurn(Player player) throws IOException {
		int userSelection = 0;
		boolean validInput = false;
		while(!validInput){
			userSelection = ConsoleUI.promptForInt("Which spot would you like to select " + player.name + "?", 1, 9);
			if(!priorSelection.contains(userSelection)) {
				priorSelection.add(userSelection);
				validInput = true;
			}
		}
		boardEditor(player.value, userSelection);
		//player.turn = false;
		player.win = didThePlayerWin();
	}
	
	public void boardEditor(int player, int index) {
		if(index == 1) {
			gamePlay.base[0][0] = player;
		} else if(index == 2) {
			gamePlay.base[0][1] = player;
		} else if(index == 3) {
			gamePlay.base[0][2] = player;
		} else if(index == 4) {
			gamePlay.base[1][0] = player;
		} else if(index == 5) {
			gamePlay.base[1][1] = player;
		} else if(index == 6) {
			gamePlay.base[1][2] = player;
		} else if(index == 7) {
			gamePlay.base[2][0] = player;
		} else if(index == 8) {
			gamePlay.base[2][1] = player;
		} else if(index == 9) {
			gamePlay.base[2][2] = player;
		}
	}
	
	public void printBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(gamePlay.base[i][j] == -1) {
					System.out.print("X");
				} else if(gamePlay.base[i][j] == 1){
					System.out.print("O");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
	
	public boolean didThePlayerWin() {
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
}
