
public class Board {
	int[][] base = new int[3][3];
	Square box00 = new Square();
	Square box01 = new Square();
	Square box02 = new Square();
	Square box10 = new Square();
	Square box11 = new Square();
	Square box12 = new Square();
	Square box20 = new Square();
	Square box21 = new Square();
	Square box22 = new Square();
	
	public void initializer() {
		base[0][0] = box00.reset;
		base[0][1] = box01.reset;
		base[0][2] = box02.reset;
		base[1][0] = box10.reset;
		base[1][1] = box11.reset;
		base[1][2] = box12.reset;
		base[2][0] = box20.reset;
		base[2][1] = box21.reset;
		base[2][2] = box22.reset;
	}
	
	public int[] convertorTo2d(int index) {
		int[] array = new int[2];
		index -= 1;
		array[0] = index / 3;
		array[1] = index % 3;
		return array;
	}
	
	public void boardEditor(int player, int index) {
		int[] array = convertorTo2d(index);
		base[array[0]][array[1]] = player;
	}
	
	public boolean validInput(int index) {
		int[] array = convertorTo2d(index);
		if(base[array[0]][array[1]] != 0) {
			return false;
		} 
		return true;
	}
	
	public boolean boardFull() {
		for(int i=0;i<base.length;i++){
			for(int j=0;j<base[i].length;j++) {
				if (base[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void printBoard() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(base[i][j] == -1) {
					System.out.print("X");
				} else if(base[i][j] == 1){
					System.out.print("O");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
}
