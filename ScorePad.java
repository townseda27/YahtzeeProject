package YahtzeeProject;

import java.util.Arrays;

/**
 * Represents the score pad in the game Yahtzee. The {@code update()}
 * method will 
 * 
 * @author Daniel Townsend
 */
public class ScorePad {
	
	public static final int FULL_HOUSE_SCORE = 25;
	public static final int SMALL_STRAIGHT_SCORE = 30;
	public static final int LARGE_STRAIGHT_SCORE = 40;
	public static final int YAHTZEE_SCORE = 50;
	public static final int YAHTZEE_BONUS = 100;
	public static final int UPPER_BONUS_REQ = 63;
	public static final int UPPER_BONUS_VALUE = 35;
	public static boolean isFirstYahtzee = true;
	
	
	/**
	 * Examine the current roll ({@code int[] roll} and update the GUI according to the
	 * possible score values for each category.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	public static void update(int[] roll) {
		if(roll != null && roll.length == 5) {
			scoreUpper(roll);
			scoreXOfAKind(roll, 3);
			scoreXOfAKind(roll, 4);
			scoreFullHouse(roll);
			scoreSmallStraight(roll);
			scoreLargeStraight(roll);
			scoreYahtzee(roll);
			scoreChance(roll);
		}
	}
	
	/**
	 * Resets the score pad
	 */
	public static void reset() {
		isFirstYahtzee = true;
		
		// TODO - need GUI done first
	}
	
	/**
	 * Updates the GUI with the total score for each category in the upper section. If a category is
	 * in the upper section is already scored, the GUI will not be updated for that category.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreUpper(int[] roll) {
		int acesScore   = totalOf(1, roll);
		int twosScore   = totalOf(2, roll);
		int threesScore = totalOf(3, roll);
		int foursScore  = totalOf(4, roll);
		int fivesScore  = totalOf(5, roll);
		int sixesScore  = totalOf(6, roll);
		
		if(!GUI.isUsedRow(GUI.ACES_ROW)) {
			GUI.scoreTable.setValueAt(acesScore, GUI.ACES_ROW, 1);
		}
		
		if(!GUI.isUsedRow(GUI.TWOS_ROW)) {
			GUI.scoreTable.setValueAt(twosScore, GUI.TWOS_ROW, 1);
		}
		
		if(!GUI.isUsedRow(GUI.THREES_ROW)) {
			GUI.scoreTable.setValueAt(threesScore, GUI.THREES_ROW, 1);
		}
		
		if(!GUI.isUsedRow(GUI.FOURS_ROW)) {
			GUI.scoreTable.setValueAt(foursScore, GUI.FOURS_ROW, 1);
		}
		
		if(!GUI.isUsedRow(GUI.FIVES_ROW)) {
			GUI.scoreTable.setValueAt(fivesScore, GUI.FIVES_ROW, 1);
		}
		
		if(!GUI.isUsedRow(GUI.SIXES_ROW)) {
			GUI.scoreTable.setValueAt(sixesScore, GUI.SIXES_ROW, 1);
		}
	}
	
	/**
	 * Updates the GUI with the total score the {@code x} of a Kind category. If these categories
	 * are already scored, the GUI will not update for these categories. Furthermore, if there is not
	 * x of a Kind within the {@code roll}, the GUI will display 0 for the given category. 
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 * @param x an integer representing how many of a kind (ex: 3 or 4 of a kind) to search for in {@code roll}
	 */
	private static void scoreXOfAKind(int[] roll, int x) {
		if(GUI.isUsedRow(GUI.THREE_OF_A_KIND_ROW) && x == 3) {
			return;
		}
		
		if(GUI.isUsedRow(GUI.FOUR_OF_A_KIND_ROW) && x == 4) {
			return;
		}
		
		int score = 0;
		if(isXOfKind(roll, x)) {
			score = sum(roll);
		}
		
		if(x == 3) {
			GUI.scoreTable.setValueAt(score, GUI.THREE_OF_A_KIND_ROW, 1);
		} else {
			GUI.scoreTable.setValueAt(score, GUI.FOUR_OF_A_KIND_ROW, 1);
		}

	}
	
	/**
	 * Updates the GUI with the total score for the Full House category. If this category
	 * is already scored, the GUI is not changed. Furthermore, if the roll is not a Full House, then
	 * the GUI will display 0 for the Full House category.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreFullHouse(int[] roll) {
		if(GUI.isUsedRow(GUI.FULL_HOUSE_ROW)) {
			return;
		}
		
		int score = 0;
		if(isXOfKind(roll, 2) && isXOfKind(roll, 3)) {
			score = FULL_HOUSE_SCORE;
		}
		
		GUI.scoreTable.setValueAt(score, GUI.FULL_HOUSE_ROW, 1);
	}
	
	/**
	 * Updates the GUI with the total score for the Small Straight category. If this category
	 * is already scored, the GUI is not changed. Furthermore, if the roll is not a Small Straight, then
	 * the GUI will display 0 for the Small Straight category.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreSmallStraight(int[] roll) {
		if(GUI.isUsedRow(GUI.SMALL_STRAIGHT_ROW)) {
			return;
		}
		
		int[] sortedRoll = Arrays.copyOf(roll, roll.length);
		Arrays.sort(sortedRoll);
		int[] sortedNoDups = removeDuplicates(sortedRoll);
		
		if(sortedNoDups.length < 4) {
			GUI.scoreTable.setValueAt(0, GUI.SMALL_STRAIGHT_ROW, 1);
			return;
		}
		
		int score = 0;
		int sequenceCount = 0;
		for(int i = 0; i < sortedNoDups.length - 1; i++) {
			if((sortedNoDups[i] + 1 == sortedNoDups[i + 1])) {
				sequenceCount++;
				if(sequenceCount == 4 - 1) {
					score = SMALL_STRAIGHT_SCORE;
					break;
				}
			}
		}
		GUI.scoreTable.setValueAt(score, GUI.SMALL_STRAIGHT_ROW, 1);
	}
	
	/**
	 * Updates the GUI with the total score for the Large Straight category. If this category
	 * is already scored, the GUI is not changed. Furthermore, if the roll is not a Large Straight, then
	 * the GUI will display 0 for the Large Straight category.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreLargeStraight(int[] roll) {
		if(GUI.isUsedRow(GUI.LARGE_STRAIGHT_ROW)) {
			return;
		}
		
		int score = 0;
		int[] sortedRoll = Arrays.copyOf(roll, roll.length);
		Arrays.sort(sortedRoll);
		
		int[] largeStraight1 = {1, 2, 3, 4, 5};
		int[] largeStraight2 = {2, 3, 4, 5, 6};
		
		if(equals(sortedRoll, largeStraight1) || equals(sortedRoll, largeStraight2)) {
			score = LARGE_STRAIGHT_SCORE;
		}
		
		GUI.scoreTable.setValueAt(score, GUI.LARGE_STRAIGHT_ROW, 1);
	}
	
	/**
	 * Updates the GUI with the total score for the Yahtzee category. 
	 * If the roll is not a Yahtzee, then the GUI will display 0 or the current value of the category if
	 * one exists.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreYahtzee(int[] roll) {
		if(!isXOfKind(roll, 5)) {
			if(GUI.scoreTable.getValueAt(GUI.YAHTZEE_ROW, 1) == null) {
				GUI.scoreTable.setValueAt(0, GUI.YAHTZEE_ROW, 1);
			} else {
				GUI.scoreTable.setValueAt((int) GUI.scoreTable.getValueAt(GUI.YAHTZEE_ROW, 1), GUI.YAHTZEE_ROW, 1);
			}
			
			return;
		}
		
		if(isFirstYahtzee == true) {
			isFirstYahtzee = false;
			GUI.scoreTable.setValueAt(YAHTZEE_SCORE, GUI.YAHTZEE_ROW, 1);
		} else {
			GUI.scoreTable.setValueAt((int) GUI.scoreTable.getValueAt(GUI.YAHTZEE_ROW, 1) + YAHTZEE_BONUS, GUI.YAHTZEE_ROW, 1);
		}
	}
	
	/**
	 * Updates the GUI with the total score for the Chance category. If this category
	 * is already scored, the GUI is not changed.
	 * 
	 * @param roll an integer array containing the 5 values for each of
	 * the dice rolled.
	 */
	private static void scoreChance(int[] roll) {
		if(GUI.isUsedRow(GUI.CHANCE_ROW)) {
			return;
		}
		
		int score = sum(roll);
		GUI.scoreTable.setValueAt(score, GUI.CHANCE_ROW, 1);
	}
	
	/**
	 * Returns the total of all occurrences of the x values in the roll array.
	 * 
	 * @param x the integer to sum up all occurrences of.
	 * @param roll the integer array to search through.
	 * @return the total of all the occurrences of {@code x} in {@code roll}
	 */
	private static int totalOf(int x, int[] roll) {
		int score = 0;
		
		for (int num : roll) {
			if(num == x) {
				score += x;
			}
		}
		
		return score;
	}
	
	/**
	 * Returns the sum of all elements in the array {@code roll}
	 * 
	 * @param roll an integer array to sum over
	 * @return the sum of all elements in the array, or 0 if the array is null or empty
	 */
	private static int sum(int[] roll) {
		if(roll == null || roll.length == 0) return 0;
		
		int sum = 0;
		for(int num : roll) {
			sum += num;
		}
		return sum;
	}
	
	/**
	 * Returns true if {@code arr} contains {@code x} and false otherwise. 
	 * 
	 * @param arr an integer array to search through
	 * @param x an integer to search {@code arr} for
	 * @return a boolean, representing whether or not {@code x} was found in the array
	 */
	public static boolean contains(int[] arr, int x) {
		if(arr == null || arr.length == 0) return false;
		
		for(int num : arr) {
			if(num == x) return true;
		}
		
		return false;
	}
	
	/**
	 * Returns true if {@code roll} is a X of a Kind (ex: 3 of a Kind).
	 * 
	 * @param roll the integer array representing the dice values
	 * @param x an integer representing how many of a kind to search for
	 * @return a boolean value representing if roll is a X of a Kind (true) or not (false)
	 */
	private static boolean isXOfKind(int[] roll, int x) {
		// the value at index i represents the number of occurrences of i in roll
		int[] totalArr = new int[7];
		
		for(int i = 0; i < roll.length; i++) {
			totalArr[roll[i]]++;
		}
		
		return contains(totalArr, x);
	}
	
	/**
	 * Returns true if {@code arr1} {@code arr2} contain the same elements in the same order.
	 * 
	 * @param arr1 an integer array
	 * @param arr2 an integer array
	 * @return a boolean representing if arr1 and arr2 are the same
	 */
	private static boolean equals(int[] arr1, int[] arr2) {
		if(arr1.length != arr2.length) return false;
		
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		
		return true;
	}
	
	/**
	 * Returns {@code arr} with the duplicates removed (if any exist).
	 * 
	 * @param arr an integer array to remove duplicates from.
	 * @return
	 */
	private static int[] removeDuplicates(int[] arr) {
		int[] noDups = new int[arr.length - countDuplicates(arr)];
		
		int noDupsIndex = 0;
		for(int i = 0; i < arr.length; i++) {
			if(!contains(noDups, arr[i])) {
				noDups[noDupsIndex++] = arr[i];
			}
		}
		
		return noDups;
	}
	
	/**
	 * Returns the number of duplicate elements in {@code arr}.
	 * 
	 * @param arr an integer array to search for duplicates over.
	 * @return an integer representing the number of duplicates.
	 */
	private static int countDuplicates(int[] arr) {
		int duplicateCount = 0;
		int[] duplicates = new int[4];
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length; j++) {
				if(i != j && arr[i] == arr[j] && !contains(duplicates, arr[i])) {
					duplicates[duplicateCount] = arr[i];
					duplicateCount++;
				}
			}
		}
		
		return duplicateCount;
	}
}
