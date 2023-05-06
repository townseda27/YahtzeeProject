package YahtzeeProject;

// This class creates a Dice object that contains the dice int[] array and the rounds int[][] 2D array.
// The arrays can be accessed by using the getDiceValues() method to get the current dice values,
// and the getDiceArrays() method to get the dice arrays that have been stored in the rounds array.

public class Dice {

	// dice array to hold dice values
	private int[] dice = new int[5];

	// rounds array to hold dice arrays
	private int[][] rounds = new int[13][];

	// constructor uses for loop to fill array and randommize dice values
	public Dice() {
		for (int i = 0; i < 5; i++) {
			dice[i] = 1 + (int) (Math.random() * 6);
		}
	}

	// returns an array of the dice values
	public int[] getDiceValues() {
		return dice;
	}

	// returns an array of the dice arrays/rounds
	public int[][] getDiceArrays() {
		return rounds;
	}

	// reroll method accepts five boolean values, if statements are used to check
	// the boolean variables and randomizes dice values accordingly(true = reroll)
	public void reroll(boolean a, boolean b, boolean c, boolean d, boolean e) {
		if (a) {
			dice[0] = 1 + (int) (Math.random() * 6);
		}
		if (b) {
			dice[1] = 1 + (int) (Math.random() * 6);
		}
		if (c) {
			dice[2] = 1 + (int) (Math.random() * 6);
		}
		if (d) {
			dice[3] = 1 + (int) (Math.random() * 6);
		}
		if (e) {
			dice[4] = 1 + (int) (Math.random() * 6);
		}
	}

	// method used to test the class by printing the dice array
//	public void printDice() {
//		System.out.print("[");
//		for (int i = 0; i < 5; i++) {
//			if (i == 4) {
//				System.out.print(dice[i]);
//			} else {
//				System.out.print(dice[i] + ", ");
//			}
//		}
//		System.out.println("]");
//	}

	// method used to test the class by printing the rounds 2D array
//	public void printRounds() {
//		if(rounds[0] != null) {System.out.print("{");}
//		for (int i = 0; i < rounds.length && rounds[i] != null; i++) {
//			System.out.print("[");
//			for (int j = 0; j < rounds[i].length; j++) {
//				if (j == rounds[i].length - 1) {
//					System.out.print(rounds[i][j]);
//				} else {
//					System.out.print(rounds[i][j] + ", ");
//				}
//			}
//			System.out.print("]");
//			if (i == rounds.length - 1 || rounds[i + 1] == null) {
//				System.out.println("}");
//			} else {
//				System.out.print(", ");
//			}
//		}
//	}

	// method that stores the current dice array in the rounds array and resets the
	// dice array
//	public void finishRound() {
//		if(rounds[12] != null) {
//			System.out.println("Rounds array is full");
//			return;
//		}
//		for (int i = 0; i < 13; i++) {
//			if (rounds[i] == null) {
//				int[] round = new int[dice.length];
//				for (int j = 0; j < dice.length; j++) {
//					round[j] = dice[j];
//				}
//				rounds[i] = round;
//				resetDice();
//				return;
//			}
//		}
//	}

	// reset method uses for loop to randomize dice values
	public void resetDice() {
		for (int i = 0; i < 5; i++) {
			dice[i] = 1 + (int) (Math.random() * 6);
		}
	}
	
	// reset method for the rounds 2D array  
	public void resetRounds() {
		rounds = new int[13][];
	}
}
