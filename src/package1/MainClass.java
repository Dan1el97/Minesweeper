package package1;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args)  {
		Scanner input = new Scanner(System.in);
		System.out.println("Choose Level");
		System.out.println("Press 0 for BEGGINER");
		System.out.println("Press 1 for INTERMEDIATE");
		System.out.println("Press 2 for ADVANCED");
		int levelOption;
		while(true) {
			 levelOption = input.nextInt();
			 if(levelOption > 2) {
				 System.out.print("No such Option! Try again:");
				 continue;
			 }
			 else {
				 break;
			 }
		}
		Board board = new Board(levelOption);
		while(true) {
			System.out.print("Choose row:");
			int row = input.nextInt();
			System.out.print("Choose cow");
			int cow = input.nextInt();
			boolean isItMine = board.playGame(row, cow);
			if(isItMine) {
				input.close();
				break;
			}
		}
	}
}
