package YahtzeeProject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import junit.framework.JUnit4TestAdapter;


public class LogicCoverageTest {

	private final int ROW_X = 185;
	private final int ACES_ROW_Y = 37;
	private final int TWOS_ROW_Y = 53;
	private final int THREES_ROW_Y = 71;
	private final int FOURS_ROW_Y = 86;
	private final int FIVES_ROW_Y = 101;
	private final int SIXES_ROW_Y = 118;
	private final int THREE_OF_KIND_ROW_Y = 199;
	private final int FOUR_OF_KIND_ROW_Y = 216;
	private final int FULL_HOUSE_ROW_Y = 229;
	private final int SM_STRAIGHT_ROW_Y = 246;
	private final int LG_STRAIGHT_HOUSE_ROW_Y = 260;
	private final int YAHTZEE_ROW_Y = 276;
	private final int YAHTZEE_ROW_BONUS_Y = 293;
	private final int CHANCE_ROW_Y = 310;
	
	@Before
	public void doBefore() {
		GUI.frmYahtzeegui = new JFrame();
		String[] columnNames = {"Type",
	        	"Score", "Used"};

		Object[][] typeScores = {
				{"Upper Section", "Score", "Used"}, 
				{"Aces", 0, ""},
				{"Twos", 0, ""},
				{"Threes", 0, ""},
				{"Fours", 0, ""},
				{"Fives", 0, ""},
				{"Sixes", 0, ""},
				{"Upper Total", 0, ""},
				{"Upper Bonus", 0, ""},
				{"", "", ""},
				{"Lower Section", "", ""},
				{"3 of a kind", 0, ""},
				{"4 of a kind", 0, ""},
				{"Full House", 0, ""},
				{"Sm. Straight", 0, ""},
				{"Lg. Straight", 0, ""},
				{"Yahtzee", 0, ""},
				{"Chance", 0, ""},
				{"Total Lower", 0, ""},
				{"", "", ""},
				{"Grand Total", 0, ""}
		};
		
		GUI.scoreTable = new JTable(typeScores, columnNames);
		GUI.scoreTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GUI.scoreTable.setModel(new DefaultTableModel(
			new Object[][] {
				{"Category", "Score", "Used?"},
				{"Upper Section", "", ""},
				{"Aces", null, ""},
				{"Twos", null, ""},
				{"Threes", null, ""},
				{"Fours", null, ""},
				{"Fives", null, ""},
				{"Sixes", null, ""},
				{"Upper Bonus", null, ""},
				{"Upper Total", null, ""},
				{"", "", ""},
				{"Lower Section", "", ""},
				{"3 of a kind", null, ""},
				{"4 of a kind", null, ""},
				{"Full House", null, ""},
				{"Sm. Straight", null, ""},
				{"Lg. Straight", null, ""},
				{"Yahtzee", null, ""},
				{"Chance", null, ""},
				{"Total Lower", null, ""},
				{"", null, ""},
				{"Grand Total", null, ""},
			},
			new String[] {
				"Type", "Score", "Used"
			}
		));
		GUI.usedCategories = new int[GUI.TURNS];
		GUI.turnsLabel = new JLabel("Turn 1/13");
		GUI.rollLabel = new JLabel("Roll 1/3");
		GUI.rollBtn = new JButton("Roll Dice");
		GUI.dice1LockBtn = new JToggleButton("Lock");
		GUI.dice2LockBtn = new JToggleButton("Lock");
		GUI.dice3LockBtn = new JToggleButton("Lock");
		GUI.dice4LockBtn = new JToggleButton("Lock");
		GUI.dice5LockBtn = new JToggleButton("Lock");
		GUI.dice1 = new JTextPane();
		GUI.dice2 = new JTextPane();
		GUI.dice3 = new JTextPane();
		GUI.dice4 = new JTextPane();
		GUI.dice5 = new JTextPane();
		GUI.d = new Dice();
	}
	
	@After
	public void doAfter() {
		GUI.scoreTable = null;
		GUI.usedCategories = null;
		GUI.turnsLabel = null;
		GUI.rollLabel = null;
		GUI.rollBtn = null;
		GUI.dice1LockBtn = null;
		GUI.dice2LockBtn = null;
		GUI.dice3LockBtn = null;
		GUI.dice4LockBtn = null;
		GUI.dice5LockBtn = null;
		GUI.d = null;
		GUI.dice1 = null;
		GUI.dice2 = null;
		GUI.dice3 = null;
		GUI.dice4 = null;
		GUI.dice5 = null;
		GUI.frmYahtzeegui = null;
		ScorePad.isFirstYahtzee = true;
		GUI.currTurn = 1;
		GUI.currRoll = 1;
	}
	
	@Test
	public void testPredicate1() {
		// predicate is in the update method
		
		int[] nullRoll = null;
		ScorePad.update(nullRoll);
		assertNull(GUI.scoreTable.getValueAt(GUI.ACES_ROW, 1));
		
		// clear out scoreTable
		doAfter();
		doBefore();
		
		// roll is not null and it has length 5
		int[] roll1 = {1, 1, 1, 1, 1};
		ScorePad.update(roll1);
		assertEquals((int) GUI.scoreTable.getValueAt(GUI.ACES_ROW, 1), 5);
		
		// clear out scoreTable
		doAfter();
		doBefore();
		
		// roll is not null and it does not have length 5
		int[] roll2 = {1, 2, 3, 4};
		ScorePad.update(roll2);
		assertNull(GUI.scoreTable.getValueAt(GUI.TWOS_ROW, 1));
	}
	
	@Test
	public void testScoreUpper() {
		
		// ----ACES ROW-----
		// aces row is already used
		int acesRoll[] = {1, 1, 3, 4, 6};
		ScorePad.update(acesRoll);
		MouseEvent acesEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, ACES_ROW_Y, 1, false);
		GUI.handleRowClick(acesEvent);
		int acesRoll2[] = {1, 2, 3, 4, 5};
		ScorePad.update(acesRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.ACES_ROW, 1), 2);
		
		doAfter();
		doBefore();
		
		// aces row is not already used
		int acesRoll3[] = {1, 1, 1, 1, 2};
		ScorePad.update(acesRoll3);
		MouseEvent acesEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, ACES_ROW_Y, 1, false);
		GUI.handleRowClick(acesEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.ACES_ROW, 1), 4);
		
		doAfter();
		doBefore();
		
		// ----TWOS ROW----
		// twos row is already used
		int twosRoll[] = {2, 2, 3, 4, 6};
		ScorePad.update(twosRoll);
		MouseEvent twosEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, TWOS_ROW_Y, 1, false);
		GUI.handleRowClick(twosEvent);
		int twosRoll2[] = {2, 1, 3, 4, 5};
		ScorePad.update(twosRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.TWOS_ROW, 1), 4);
		
		doAfter();
		doBefore();
		
		// twos row is not already used
		int twosRoll3[] = {2, 2, 2, 2, 1};
		ScorePad.update(twosRoll3);
		MouseEvent twosEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, TWOS_ROW_Y, 1, false);
		GUI.handleRowClick(twosEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.TWOS_ROW, 1), 8);
		
		doAfter();
		doBefore();
		
		// ----THREES ROW----
		// threes row is already used
		int threesRoll[] = {2, 2, 3, 4, 6};
		ScorePad.update(threesRoll);
		MouseEvent threesEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREES_ROW_Y, 1, false);
		GUI.handleRowClick(threesEvent);
		int threesRoll2[] = {3, 1, 3, 4, 5};
		ScorePad.update(threesRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREES_ROW, 1), 3);
		
		doAfter();
		doBefore();
		
		// threes row is not already used
		int threesRoll3[] = {3, 3, 3, 3, 1};
		ScorePad.update(threesRoll3);
		MouseEvent threesEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREES_ROW_Y, 1, false);
		GUI.handleRowClick(threesEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREES_ROW, 1), 12);
		
		// ----FOURS ROW----
		// fours row is already used
		int foursRoll[] = {2, 2, 3, 4, 6};
		ScorePad.update(foursRoll);
		MouseEvent foursEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOURS_ROW_Y, 1, false);
		GUI.handleRowClick(foursEvent);
		int foursRoll2[] = {4, 1, 3, 4, 5};
		ScorePad.update(foursRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOURS_ROW, 1), 4);
		
		doAfter();
		doBefore();
		
		// fours row is not already used
		int foursRoll3[] = {4, 4, 4, 4, 1};
		ScorePad.update(foursRoll3);
		MouseEvent foursEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOURS_ROW_Y, 1, false);
		GUI.handleRowClick(foursEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOURS_ROW, 1), 16);
		
		doAfter();
		doBefore();
		
		// ----FIVES ROW----
		// fives row is already used
		int fivesRoll[] = {2, 2, 3, 5, 5};
		ScorePad.update(fivesRoll);
		MouseEvent fivesEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FIVES_ROW_Y, 1, false);
		GUI.handleRowClick(fivesEvent);
		int fivesRoll2[] = {4, 1, 3, 4, 5};
		ScorePad.update(fivesRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.FIVES_ROW, 1), 10);
		
		doAfter();
		doBefore();
		
		// fives row is not already used
		int fivesRoll3[] = {5, 5, 5, 5, 1};
		ScorePad.update(fivesRoll3);
		MouseEvent fivesEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FIVES_ROW_Y, 1, false);
		GUI.handleRowClick(fivesEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FIVES_ROW, 1), 20);
		
		doAfter();
		doBefore();
		
		// ----SIXES ROW----
		// sixes row is already used
		int sixesRoll[] = {2, 2, 3, 5, 6};
		ScorePad.update(sixesRoll);
		MouseEvent sixesEvent = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, SIXES_ROW_Y, 1, false);
		GUI.handleRowClick(sixesEvent);
		int sixesRoll2[] = {4, 6, 3, 4, 6};
		ScorePad.update(sixesRoll2);
		// we should not update the score pad if we've already used this row
		assertEquals(GUI.scoreTable.getValueAt(GUI.SIXES_ROW, 1), 6);
		
		doAfter();
		doBefore();
		
		// sixes row is not already used
		int sixesRoll3[] = {6, 6, 6, 6, 1};
		ScorePad.update(sixesRoll3);
		MouseEvent sixesEvent2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, SIXES_ROW_Y, 1, false);
		GUI.handleRowClick(sixesEvent2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SIXES_ROW, 1), 24);
		
	}

	@Test
	public void testScore3OfKind() {
		
		// three of a kind row is used and x is equal to 3
		int roll[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll);
		MouseEvent event = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREE_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event);
		
		int roll2[] = {1, 1, 1, 4, 5};
		ScorePad.update(roll2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREE_OF_A_KIND_ROW, 1), 5);
	
		doAfter();
		doBefore();
		
		// three of a kind row is not used and x is equal to 3
		int roll3[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll3);
		MouseEvent event2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREE_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREE_OF_A_KIND_ROW, 1), 5);
		
		doAfter();
		doBefore();
		
		// three of a kind row is used and x is not equal to 3
		int roll4[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll4);
		MouseEvent event3 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREE_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event3);
		
		int roll5[] = {1, 1, 1, 1, 5};
		ScorePad.update(roll5);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOUR_OF_A_KIND_ROW, 1), 9);
	
		doAfter();
		doBefore();
	}
	
	@Test
	public void testScore4OfKind() {
		
		// four of a kind row is used and x is equal to 4
		int roll[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll);
		MouseEvent event = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOUR_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event);
		
		int roll2[] = {1, 1, 1, 1, 5};
		ScorePad.update(roll2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOUR_OF_A_KIND_ROW, 1), 5);
	
		doAfter();
		doBefore();
		
		// four of a kind row is not used and x is equal to 4
		int roll3[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll3);
		MouseEvent event2 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOUR_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event2);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOUR_OF_A_KIND_ROW, 1), 5);
		
		doAfter();
		doBefore();
		
		// four of a kind row is used and x is not equal to 4
		int roll4[] = {1, 1, 1, 1, 1};
		ScorePad.update(roll4);
		MouseEvent event3 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOUR_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(event3);
		
		int roll5[] = {1, 1, 1, 1, 5};
		ScorePad.update(roll5);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREE_OF_A_KIND_ROW, 1), 9);
	
		doAfter();
		doBefore();
	}
	
	@Test
	public void testScoreXOfAKind() {
		int[] roll4OfAKind = {1, 1, 1, 1, 2};
		ScorePad.update(roll4OfAKind);
		MouseEvent eventRow3 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, THREE_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(eventRow3);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREE_OF_A_KIND_ROW, 1), 6);
		ScorePad.update(roll4OfAKind);
		MouseEvent eventRow4 = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FOUR_OF_KIND_ROW_Y, 1, false);
		GUI.handleRowClick(eventRow4);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOUR_OF_A_KIND_ROW, 1), 6);
		
		doAfter();
		doBefore();
		
		int[] rollNo4OfAKind = {1, 2, 3, 4, 5};
		ScorePad.update(rollNo4OfAKind);
		assertEquals(GUI.scoreTable.getValueAt(GUI.THREE_OF_A_KIND_ROW, 1), 0);
		ScorePad.update(rollNo4OfAKind);
		GUI.handleRowClick(eventRow4);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FOUR_OF_A_KIND_ROW, 1), 0);
	}
	
	@Test
	public void testScoreFullHouse() {
		// full house row is used
		int roll[] = {1, 1, 2, 2, 2};
		ScorePad.update(roll);
		MouseEvent event = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, FULL_HOUSE_ROW_Y, 1, false);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 25);
		ScorePad.update(roll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 25);
		
		doAfter();
		doBefore();
		
		// full house is not used
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), null);
		ScorePad.update(roll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 25);
		
		doAfter();
		doBefore();
		
		// roll is a full house
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), null);
		int[] fullHouseRoll = {1, 1, 1, 2, 2};
		ScorePad.update(fullHouseRoll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 25);
		
		doAfter();
		doBefore();
		
		// roll is a two of a kind and not a 3 of a kind
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), null);
		int[] twoOfKindRoll = {1, 3, 4, 2, 2};
		ScorePad.update(twoOfKindRoll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 0);
		
		doAfter();
		doBefore();
		
		// roll is a 3 of a kind and not a two of a kind for a different dice value
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), null);
		int[] threeOfKindRoll = {1, 3, 2, 2, 2};
		ScorePad.update(threeOfKindRoll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 0);
		
		doAfter();
		doBefore();
		
		// roll is not a 3 of a kind and roll is not a 2 of a kind
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), null);
		int[] neitherRoll = {1, 2, 3, 4, 5};
		ScorePad.update(threeOfKindRoll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.FULL_HOUSE_ROW, 1), 0);
	}
	
	@Test
	public void testScoreSmallStraight() {
		// small straight row is used
		int roll[] = {1, 3, 4, 2, 6};
		ScorePad.update(roll);
		MouseEvent event = new MouseEvent(GUI.scoreTable, 0, 0, 0, ROW_X, SM_STRAIGHT_ROW_Y, 1, false);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		ScorePad.update(roll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		
		doAfter();
		doBefore();
		
		// small straight row is not used
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(roll);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		
		doAfter();
		doBefore();
		
		// sortedNoDups has length 4
		int[] sortedNoDupsLength4 = {1, 2, 3, 4, 1};
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(sortedNoDupsLength4);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		
		doAfter();
		doBefore();
		
		// sortedNoDups has length > 4
		int[] sortedNoDupsLengthGreater4 = {1, 2, 3, 4, 5};
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(sortedNoDupsLengthGreater4);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		
		doAfter();
		doBefore();
		
		// sortedNoDups has length < 4
		int[] sortedNoDupsLengthLess4 = {1, 2, 3, 1, 1};
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(sortedNoDupsLengthLess4);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 0);
		
		doAfter();
		doBefore();
		
		// sequence count is 3
		int[] rollSeq3 = {1, 2, 3, 4, 6};
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(rollSeq3);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 30);
		
		doAfter();
		doBefore();
		
		// sequence count is not 3
		int[] rollSeqLess3 = {1, 2, 3, 5, 6};
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), null);
		ScorePad.update(rollSeqLess3);
		GUI.handleRowClick(event);
		assertEquals(GUI.scoreTable.getValueAt(GUI.SMALL_STRAIGHT_ROW, 1), 0);
	}
	
}
