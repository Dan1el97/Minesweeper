package package1;

import java.util.Arrays;
import java.util.Random;

public class Board {
	static String board[][];
	int levelOption;
	/*
	 * rowCordinates, cowCordinates - arrays containing the random coordinates for the bombs
	 */
	int[] rowCoordinates;
	int[] cowCoordinates;
	int numberOfMines = 0;

	public Board(int levelOption) {
		this.levelOption = levelOption;
		
		chooseBoard(levelOption);
		placeMines();
	}
	/*
	 * winGame() - Finds out if there are elements of the board equal to "-"
	 * if there are none - you win
	 */
	private boolean winGame() {
		for(int i = 0; i < board[0].length; i++) {
			for(int j = 0; j < board[1].length; j++) {
				if(board[i][j] == "-") {
					return false;
				}
			}
		}
		return true;
	}
	public boolean playGame(int row, int cow) {
		if(winGame()) {
			exploreMines(2);
		}
		int count = 0;
		//Two dimensional array containing the bombs;
		String[][] arr = new String[rowCoordinates.length][cowCoordinates.length];
		for(int i = 0; i < rowCoordinates.length; i++) {
			arr[rowCoordinates[i]][cowCoordinates[i]] = "*";
		}
		if(row > board[0].length-1 || cow > board[1].length-1 || row <=0 || cow <= 0 ) {
			System.out.println("Out of bounds! Try again!");
			return false;
		}
		//In case row and cow both are less than the max values
		if(row < board[0].length-1 && cow < board[1].length-1) {
			if(arr[row][cow] == "*") {
					exploreMines(1);
					return true;
				}
			if(arr[row-1][cow]== "*") {
				count++;
			}
			if(arr[row+1][cow]== "*") {
				count++;
			}
			if(arr[row][cow-1]== "*") {
				count++;
			}
			if(arr[row][cow+1]== "*") {
				count++;
			}
			if(arr[row-1][cow-1]== "*") {
				count++;
			}
			if(arr[row-1][cow+1]== "*") {
				count++;
			}
			if(arr[row+1][cow-1]== "*") {
				count++;
			}
			if(arr[row+1][cow+1]== "*") {
				count++;
			}
		}
		//In case row is equal to the max value
		else if(row == board[0].length-1 && cow != board[1].length-1) {
			if(arr[row][cow]== "*") {
				exploreMines(1);
				System.out.println("YOU LOSE!");
				return true;
			}
			if(arr[row-1][cow]== "*") {
				count++;
			}
			if(arr[row][cow-1]== "*") {
				count++;
			}
			if(arr[row][cow+1]== "*") {
				count++;
			}
			if(arr[row-1][cow-1]== "*") {
				count++;
			}
			if(arr[row-1][cow+1]== "*") {
				count++;
			}
		}
		//In case cow is equal to the max value
		else if(cow == board[1].length-1 && row != board[0].length-1) {
			if(arr[row][cow]== "*") {
				exploreMines(1);
				System.out.println("YOU LOSE!");
				return true;
			}
			if(arr[row-1][cow]== "*") {
				count++;
			}
			if(arr[row+1][cow]== "*") {
				count++;
			}
			if(arr[row][cow-1]== "*") {
				count++;
			}
			if(arr[row-1][cow-1]== "*") {
				count++;
			}
			if(arr[row+1][cow-1]== "*") {
				count++;
			}
		}
		//In case row and cow both are equal to the max values
		else if(row == board[0].length-1 && cow == board[1].length-1) {
			if(arr[row][cow]== "*") {
				exploreMines(1);
				return true;
			}
			if(arr[row-1][cow]== "*") {
				count++;
			}
			if(arr[row][cow-1]== "*") {
				count++;
			}
			if(arr[row-1][cow-1]== "*") {
				count++;
			}
		}
		board[row][cow] = String.valueOf(count);
		outputBoard();
		return false;
	}
	/*
	 * chooseBoard(int levelOption) - Choose the size of the board according to the level you have chosen
	 */
	private  void chooseBoard(int levelOption) {
		if(levelOption == 0) {
			board = new String[10][10];
			initializeBoard();
		}
		else if(levelOption == 1) {
			board = new String[17][17];
			initializeBoard();
		}
		else if(levelOption == 2){
			board = new String[26][26];
			initializeBoard();
		}
	}
	
	private  void initializeBoard() {
		for(int i = 0; i < board[0].length ; i++) {
			for(int j = 0; j < board[1].length; j++) {
				if(i == 0 && j == 0) {
					board[i][j] = " ";
				}
				else if(i == 0) {
					board[i][j] = String.valueOf(j);
				}
				else if(j == 0) {
					board[i][j] = String.valueOf(i);
				}
				else {
					board[i][j] = "-";
				}
			}
		}
		outputBoard();
	}
	
	private void outputBoard() {
		for(int i = 0; i <  board[0].length; i++) {
			for(int j = 0; j < board[1].length; j++) {
				System.out.printf("%3s ", board[i][j]);
			}
			System.out.println();	
		}
	}
	/*
	 * placeMines() - Places the exact number of mines in random positions(elements)
	 */
	private void placeMines() {
		 Random rand = new Random();
		 if(levelOption == 0) {
			 rowCoordinates = new int[10];
			 cowCoordinates = new int[10];
			 numberOfMines = 10;
		 }
		 else if(levelOption == 1) {
			 rowCoordinates = new int[45];
			 cowCoordinates = new int[45];
			 numberOfMines = 45;
		 }
		 else {
			 rowCoordinates = new int[99];
			 cowCoordinates = new int[99];
			 numberOfMines = 99;
		 }
		 for(int i = 0 ; i < numberOfMines; i++) {
			 int randomNumber = rand.nextInt(board[0].length-1)+1;
			 int randomNumber2 = rand.nextInt(board[1].length-1)+1;
			 rowCoordinates[i] = randomNumber;
			 cowCoordinates[i] = randomNumber2;
		 }  
		 System.out.println(Arrays.toString(rowCoordinates));
		 System.out.println(Arrays.toString(cowCoordinates));	 
	}
	/*
	 * exploreMine(int option) - In case you win or lose
	 */
	private void exploreMines(int option) {
		for(int i = 0; i < numberOfMines; i++) {
			board[rowCoordinates[i]][cowCoordinates[i]] = "*";
		}
		outputBoard();
		if(option == 1) {
			System.out.println("YOU LOSE!");
		}
		else {
			System.out.println("YOU WIN!");
		}
	}
}
