import java.io.IOException;

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
	
	public void run() throws IOException {
		while(true) {
			gamePlay.initializer();
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
		player2.name = ConsoleUI.promptForInput("What is the name of Player 2?", false);
		player1.value = -1;
		player2.value = 1;
		boolean p1turn = true;		
		
		while(true) {
			gamePlay.printBoard();
			if(p1turn) {
				gamePlay.boardEditor(player1.value, player1.turn(gamePlay));
				if(player1.playerWin(gamePlay)) {
					gamePlay.printBoard();
					System.out.println(player1.name + " won!");
					break;
				}
				p1turn = false;
			} else {
				gamePlay.boardEditor(player2.value, player2.turn(gamePlay));
				if(player2.playerWin(gamePlay)) {
					gamePlay.printBoard();
					System.out.println(player2.name + " won!");
					break;
				}
				p1turn = true;
			
			} 
			if(gamePlay.boardFull()) {
				gamePlay.printBoard();
				System.out.println("It is a stalemate!");
				break;
			}
		}
	}
	

	

}
