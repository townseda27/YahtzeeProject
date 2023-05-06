package YahtzeeProject;

import static org.junit.Assert.*;

import java.awt.Font;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ISPTest {
	
	//Setting up doBefore and doAfter ---------------------------------------
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
				{"Yahtzee Bonus", 0, ""},
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
					{"Yahtzee Bonus", null, ""},
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
		GUI.playAgainBtn = new JButton();
		GUI.dice1 = new JTextPane();
		GUI.dice2 = new JTextPane();
		GUI.dice3 = new JTextPane();
		GUI.dice4 = new JTextPane();
		GUI.dice5 = new JTextPane();
		GUI.d = new Dice();
		GUI.yahtzeeBonusScore = 0;
		GUI.currRoll = 1;
		GUI.currTurn = 1;
		GUI.gameBoard = new JPanel();
		GUI.gameOver = false;
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
		GUI.yahtzeeBonusScore = 0;
		GUI.gameBoard = null;
		GUI.playAgainBtn = null;
		GUI.gameOver = false;
	}
	
	
	
	//ScorePad.java ISP Tests -----------------------------------------------------

	//Test update ----------------------------------------------------------
	@Test(expected = IllegalArgumentException.class)
	public void testUpdate() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		ScorePad.update(roll1);
		int[] comp1 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll1, comp1));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		ScorePad.update(roll2);
		int[] comp2 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll2, comp2));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		ScorePad.update(roll3);
		int[] comp3 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll3, comp3));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		ScorePad.update(roll4);
		int[] comp4 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll4, comp4));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		ScorePad.update(roll5);
		int[] comp5 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll5, comp5));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		ScorePad.update(roll6);
		int[] comp6 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll6, comp6));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		ScorePad.update(roll7);
		int[] comp7 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll7, comp7));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		ScorePad.update(roll8);
		int[] comp8 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll8, comp8));
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		ScorePad.update(roll9);
		int[] comp9 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll9, comp9));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		ScorePad.update(roll10);
		int[] comp10 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll10, comp10));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		ScorePad.update(roll11);
		int[] comp11 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll11, comp11));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		ScorePad.update(roll12);
		int[] comp12 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll12, comp12));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		ScorePad.update(roll13);
		int[] comp13 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll13, comp13));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		ScorePad.update(roll14);
		int[] comp14 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll14, comp14));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		ScorePad.update(roll15);
		int[] comp15 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll15, comp15));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		ScorePad.update(roll16);
		int[] comp16 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll16, comp16));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		ScorePad.update(roll17);
		int[] comp17 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll17, comp17));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		ScorePad.update(roll18);
		int[] comp18 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll18, comp18));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		ScorePad.update(roll19);
		int[] comp19 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll19, comp19));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		ScorePad.update(roll20);
		int[] comp20 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll20, comp20));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		ScorePad.update(roll21);
		int[] comp21 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll21, comp21));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		ScorePad.update(roll22);
		int[] comp22 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll22, comp22));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		ScorePad.update(roll23);
		int[] comp23 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll23, comp23));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		ScorePad.update(roll24);
		int[] comp24 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll24, comp24));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		ScorePad.update(roll25);
		int[] comp25 = {Integer.parseInt(GUI.dice1.getText()),
				Integer.parseInt(GUI.dice2.getText()),
				Integer.parseInt(GUI.dice3.getText()),
				Integer.parseInt(GUI.dice4.getText()),
				Integer.parseInt(GUI.dice5.getText())
		};
		assertFalse(Arrays.equals(roll25, comp25));
		
		doAfter();
	}
	
	//Test totalOf ----------------------------------------------------------------
	@Test
	public void testTotalOf() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int x = 0;
		int score = ScorePad.totalOf(x, roll1);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		x = 1;
		score = ScorePad.totalOf(x, roll2);
		assertTrue(score == 4);
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		x = 2;
		score = ScorePad.totalOf(x, roll3);
		assertTrue(score == 8);
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		x = 6;
		score = ScorePad.totalOf(x, roll4);
		assertTrue(score == 24);
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		x = 7;
		score = ScorePad.totalOf(x, roll5);
		assertTrue(score == 28);
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		x = 7;
		score = ScorePad.totalOf(x, roll6);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		x = 0;
		score = ScorePad.totalOf(x, roll7);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		x = 1;
		score = ScorePad.totalOf(x, roll8);
		assertTrue(score == 1);
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		x = 2;
		score = ScorePad.totalOf(x, roll9);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		x = 6;
		score = ScorePad.totalOf(x, roll10);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		x = 6;
		score = ScorePad.totalOf(x, roll11);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		x = 7;
		score = ScorePad.totalOf(x, roll12);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		x = 0;
		score = ScorePad.totalOf(x, roll13);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		x = 1;
		score = ScorePad.totalOf(x, roll14);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		x = 2;
		score = ScorePad.totalOf(x, roll15);
		assertTrue(score == 2);
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		x = 2;
		score = ScorePad.totalOf(x, roll16);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		x = 6;
		score = ScorePad.totalOf(x, roll17);
		assertTrue(score == 6);
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		x = 7;
		score = ScorePad.totalOf(x, roll18);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		x = 0;
		score = ScorePad.totalOf(x, roll19);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		x = 1;
		score = ScorePad.totalOf(x, roll20);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		x = 1;
		score = ScorePad.totalOf(x, roll21);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		x = 5;
		score = ScorePad.totalOf(x, roll22);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		x = 6;
		score = ScorePad.totalOf(x, roll23);
		assertTrue(score == 0);
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		x = 7;
		score = ScorePad.totalOf(x, roll24);
		assertTrue(score == 7);
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		x = 0;
		score = ScorePad.totalOf(x, roll25);
		assertTrue(score == 0);
		
		doAfter();
	}
	
	//Test sum ---------------------------------------------------------------------
	@Test
	public void testSum() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int sum = ScorePad.sum(roll1);
		assertTrue(sum == 0);
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		sum = ScorePad.sum(roll2);
		assertTrue(sum == 4);
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		sum = ScorePad.sum(roll3);
		assertTrue(sum == 8);
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		sum = ScorePad.sum(roll4);
		assertTrue(sum == 24);
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		sum = ScorePad.sum(roll5);
		assertTrue(sum == 28);
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		sum = ScorePad.sum(roll6);
		assertTrue(sum == 10);
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		sum = ScorePad.sum(roll7);
		assertTrue(sum == 17);
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		sum = ScorePad.sum(roll8);
		assertTrue(sum == 16);
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		sum = ScorePad.sum(roll9);
		assertTrue(sum == 15);
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		sum = ScorePad.sum(roll10);
		assertTrue(sum == 11);
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		sum = ScorePad.sum(roll11);
		assertTrue(sum == 12);
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		sum = ScorePad.sum(roll12);
		assertTrue(sum == 11);
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		sum = ScorePad.sum(roll13);
		assertTrue(sum == 18);
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		sum = ScorePad.sum(roll14);
		assertTrue(sum == 17);
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		sum = ScorePad.sum(roll15);
		assertTrue(sum == 16);
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		sum = ScorePad.sum(roll16);
		assertTrue(sum == 20);
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		sum = ScorePad.sum(roll17);
		assertTrue(sum == 16);
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		sum = ScorePad.sum(roll18);
		assertTrue(sum == 15);
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		sum = ScorePad.sum(roll19);
		assertTrue(sum == 22);
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		sum = ScorePad.sum(roll20);
		assertTrue(sum == 21);
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		sum = ScorePad.sum(roll21);
		assertTrue(sum == 22);
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		sum = ScorePad.sum(roll22);
		assertTrue(sum == 21);
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		sum = ScorePad.sum(roll23);
		assertTrue(sum == 17);
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		sum = ScorePad.sum(roll24);
		assertTrue(sum == 16);
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		sum = ScorePad.sum(roll25);
		assertTrue(sum == 23);
		
		doAfter();
	}

	//Test contains -----------------------------------------------------------------
	@Test
	public void testContains() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int x = 0;
		assertTrue(ScorePad.contains(roll1, x));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		x = 1;
		assertTrue(ScorePad.contains(roll2, x));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		x = 2;
		assertTrue(ScorePad.contains(roll3, x));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		x = 6;
		assertTrue(ScorePad.contains(roll4, x));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		x = 7;
		assertTrue(ScorePad.contains(roll5, x));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		x = 7;
		assertFalse(ScorePad.contains(roll6, x));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		x = 0;
		assertFalse(ScorePad.contains(roll7, x));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		x = 1;
		assertTrue(ScorePad.contains(roll8, x));
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		x = 2;
		assertFalse(ScorePad.contains(roll9, x));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		x = 6;
		assertFalse(ScorePad.contains(roll10, x));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		x = 6;
		assertFalse(ScorePad.contains(roll11, x));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		x = 7;
		assertFalse(ScorePad.contains(roll12, x));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		x = 0;
		assertFalse(ScorePad.contains(roll13, x));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		x = 1;
		assertFalse(ScorePad.contains(roll14, x));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		x = 2;
		assertTrue(ScorePad.contains(roll15, x));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		x = 2;
		assertFalse(ScorePad.contains(roll16, x));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		x = 6;
		assertTrue(ScorePad.contains(roll17, x));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		x = 7;
		assertFalse(ScorePad.contains(roll18, x));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		x = 0;
		assertFalse(ScorePad.contains(roll19, x));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		x = 1;
		assertFalse(ScorePad.contains(roll20, x));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		x = 1;
		assertFalse(ScorePad.contains(roll21, x));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		x = 5;
		assertFalse(ScorePad.contains(roll22, x));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		x = 6;
		assertFalse(ScorePad.contains(roll23, x));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		x = 7;
		assertTrue(ScorePad.contains(roll24, x));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		x = 0;
		assertFalse(ScorePad.contains(roll25, x));
		
		doAfter();
	}
	
	//Test containsXOrMore ----------------------------------------------------------
	@Test
	public void testContainsXOrMore() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int x = 0;
		assertTrue(ScorePad.containsXOrMore(roll1, x));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		x = 1;
		assertTrue(ScorePad.containsXOrMore(roll2, x));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		x = 2;
		assertTrue(ScorePad.containsXOrMore(roll3, x));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		x = 6;
		assertTrue(ScorePad.containsXOrMore(roll4, x));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		x = 7;
		assertTrue(ScorePad.containsXOrMore(roll5, x));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		x = 7;
		assertFalse(ScorePad.containsXOrMore(roll6, x));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		x = 0;
		assertTrue(ScorePad.containsXOrMore(roll7, x));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		x = 1;
		assertTrue(ScorePad.containsXOrMore(roll8, x));
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		x = 2;
		assertTrue(ScorePad.containsXOrMore(roll9, x));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		x = 6;
		assertTrue(ScorePad.containsXOrMore(roll10, x));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		x = 6;
		assertTrue(ScorePad.containsXOrMore(roll11, x));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		x = 7;
		assertFalse(ScorePad.containsXOrMore(roll12, x));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		x = 0;
		assertTrue(ScorePad.containsXOrMore(roll13, x));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		x = 1;
		assertTrue(ScorePad.containsXOrMore(roll14, x));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		x = 2;
		assertTrue(ScorePad.containsXOrMore(roll15, x));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		x = 2;
		assertTrue(ScorePad.containsXOrMore(roll16, x));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		x = 6;
		assertTrue(ScorePad.containsXOrMore(roll17, x));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		x = 7;
		assertFalse(ScorePad.containsXOrMore(roll18, x));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		x = 0;
		assertTrue(ScorePad.containsXOrMore(roll19, x));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		x = 1;
		assertTrue(ScorePad.containsXOrMore(roll20, x));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		x = 1;
		assertTrue(ScorePad.containsXOrMore(roll21, x));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		x = 5;
		assertTrue(ScorePad.containsXOrMore(roll22, x));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		x = 6;
		assertTrue(ScorePad.containsXOrMore(roll23, x));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		x = 7;
		assertTrue(ScorePad.containsXOrMore(roll24, x));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		x = 0;
		assertTrue(ScorePad.containsXOrMore(roll25, x));
		
		doAfter();
	}
	
	//Test isAtLeastXOfAKind -------------------------------------------------------
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testIsAtLeastXOfAKind() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int x = 0;
		assertFalse(ScorePad.isAtLeastXOfKind(roll1, x));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		x = 1;
		assertFalse(ScorePad.isAtLeastXOfKind(roll2, x));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		x = 2;
		assertTrue(ScorePad.isAtLeastXOfKind(roll3, x));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		x = 6;
		assertFalse(ScorePad.isAtLeastXOfKind(roll4, x));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		x = 7;
		assertFalse(ScorePad.isAtLeastXOfKind(roll5, x));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		x = 7;
		assertFalse(ScorePad.isAtLeastXOfKind(roll6, x));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		x = 0;
		assertFalse(ScorePad.isAtLeastXOfKind(roll7, x));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		x = 1;
		assertFalse(ScorePad.isAtLeastXOfKind(roll8, x));
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		x = 2;
		assertTrue(ScorePad.isAtLeastXOfKind(roll9, x));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		x = 6;
		assertFalse(ScorePad.isAtLeastXOfKind(roll10, x));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		x = 6;
		assertFalse(ScorePad.isAtLeastXOfKind(roll11, x));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		x = 7;
		assertFalse(ScorePad.isAtLeastXOfKind(roll12, x));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		x = 0;
		assertFalse(ScorePad.isAtLeastXOfKind(roll13, x));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		x = 1;
		assertFalse(ScorePad.isAtLeastXOfKind(roll14, x));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		x = 2;
		assertFalse(ScorePad.isAtLeastXOfKind(roll15, x));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		x = 2;
		assertTrue(ScorePad.isAtLeastXOfKind(roll16, x));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		x = 6;
		assertFalse(ScorePad.isAtLeastXOfKind(roll17, x));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		x = 7;
		assertFalse(ScorePad.isAtLeastXOfKind(roll18, x));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		x = 0;
		assertFalse(ScorePad.isAtLeastXOfKind(roll19, x));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		x = 1;
		assertFalse(ScorePad.isAtLeastXOfKind(roll20, x));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		x = 1;
		assertFalse(ScorePad.isAtLeastXOfKind(roll21, x));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		x = 5;
		assertFalse(ScorePad.isAtLeastXOfKind(roll22, x));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		x = 6;
		assertFalse(ScorePad.isAtLeastXOfKind(roll23, x));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		x = 7;
		assertFalse(ScorePad.isAtLeastXOfKind(roll24, x));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		x = 0;
		assertFalse(ScorePad.isAtLeastXOfKind(roll25, x));
		
		doAfter();
	}
	
	//Test isXOfAKind ---------------------------------------------------------------
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testIsXOfAKind() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int x = 0;
		assertFalse(ScorePad.isXOfKind(roll1, x));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		x = 1;
		assertFalse(ScorePad.isXOfKind(roll2, x));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		x = 2;
		assertFalse(ScorePad.isXOfKind(roll3, x));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		x = 6;
		assertFalse(ScorePad.isXOfKind(roll4, x));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		x = 7;
		assertFalse(ScorePad.isXOfKind(roll5, x));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		x = 7;
		assertFalse(ScorePad.isXOfKind(roll6, x));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		x = 0;
		assertFalse(ScorePad.isXOfKind(roll7, x));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		x = 1;
		assertFalse(ScorePad.isXOfKind(roll8, x));
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		x = 2;
		assertTrue(ScorePad.isXOfKind(roll9, x));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		x = 6;
		assertFalse(ScorePad.isXOfKind(roll10, x));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		x = 6;
		assertFalse(ScorePad.isXOfKind(roll11, x));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		x = 7;
		assertFalse(ScorePad.isXOfKind(roll12, x));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		x = 0;
		assertFalse(ScorePad.isXOfKind(roll13, x));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		x = 1;
		assertFalse(ScorePad.isXOfKind(roll14, x));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		x = 2;
		assertFalse(ScorePad.isXOfKind(roll15, x));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		x = 2;
		assertTrue(ScorePad.isXOfKind(roll16, x));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		x = 6;
		assertFalse(ScorePad.isXOfKind(roll17, x));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		x = 7;
		assertFalse(ScorePad.isXOfKind(roll18, x));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		x = 0;
		assertFalse(ScorePad.isXOfKind(roll19, x));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		x = 1;
		assertFalse(ScorePad.isXOfKind(roll20, x));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		x = 1;
		assertFalse(ScorePad.isXOfKind(roll21, x));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		x = 5;
		assertFalse(ScorePad.isXOfKind(roll22, x));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		x = 6;
		assertFalse(ScorePad.isXOfKind(roll23, x));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		x = 7;
		assertFalse(ScorePad.isXOfKind(roll24, x));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		x = 0;
		assertFalse(ScorePad.isXOfKind(roll25, x));
		
		doAfter();
	}
	
	//Test equals ------------------------------------------------------------------
	@Test
	public void testEquals() {
		doBefore();

		int[] roll1 = {0, 0, 0, 0, 0};
		int[] comp1 = {0, 0, 0, 0, 0};
		assertTrue(ScorePad.equals(roll1, comp1));
		
		doAfter();
		doBefore();
		
		int[] roll2 = {0, 1, 1, 1, 1};
		int[] comp2 = {1, 1, 1, 1, 1};
		assertFalse(ScorePad.equals(roll2, comp2));
		
		doAfter();
		doBefore();
		
		int[] roll3 = {0, 2, 2, 2, 2};
		int[] comp3 = {2, 2, 2, 2, 2};
		assertFalse(ScorePad.equals(roll3, comp3));
		
		doAfter();
		doBefore();
		
		int[] roll4 = {0, 6, 6, 6, 6};
		int[] comp4 = {6, 6, 6, 6, 6};
		assertFalse(ScorePad.equals(roll4, comp4));
		
		doAfter();
		doBefore();
		
		int[] roll5 = {0, 7, 7, 7, 7};
		int[] comp5 = {7, 7, 7, 7, 7};
		assertFalse(ScorePad.equals(roll5, comp5));
		
		doAfter();
		doBefore();
		
		int[] roll6 = {1, 0, 1, 2, 6};
		int[] comp6 = {7, 0, 1, 2, 6};
		assertFalse(ScorePad.equals(roll6, comp6));
		
		doAfter();
		doBefore();
		
		int[] roll7 = {1, 1, 2, 6, 7};
		int[] comp7 = {0, 1, 2, 6, 7};
		assertFalse(ScorePad.equals(roll7, comp7));
		
		doAfter();
		doBefore();
		
		int[] roll8 = {1, 2, 6, 7, 0};
		int[] comp8 = {1, 2, 6, 7, 0};
		assertTrue(ScorePad.equals(roll8, comp8));
		
		
		doAfter();
		doBefore();
		
		int[] roll9 = {1, 6, 7, 0, 1};
		int[] comp9 = {2, 6, 7, 0, 1};
		assertFalse(ScorePad.equals(roll9, comp9));
		
		doAfter();
		doBefore();
		
		int[] roll10 = {1, 7, 0, 1, 2};
		int[] comp10 = {6, 7, 0, 1, 2};
		assertFalse(ScorePad.equals(roll10, comp10));
		
		doAfter();
		doBefore();
		
		int[] roll11 = {2, 0, 2, 7, 1};
		int[] comp11 = {6, 0, 2, 7, 1};
		assertFalse(ScorePad.equals(roll11, comp11));
		
		doAfter();
		doBefore();
		
		int[] roll12 = {2, 1, 6, 0, 2};
		int[] comp12 = {7, 1, 6, 0, 2};
		assertFalse(ScorePad.equals(roll12, comp12));
		
		doAfter();
		doBefore();
		
		int[] roll13 = {2, 2, 7, 1, 6};
		int[] comp13 = {0, 2, 7, 1, 6};
		assertFalse(ScorePad.equals(roll13, comp13));
		
		doAfter();
		doBefore();
		
		int[] roll14 = {2, 6, 0, 2, 7};
		int[] comp14 = {1, 6, 0, 2, 7};
		assertFalse(ScorePad.equals(roll14, comp14));
		
		doAfter();
		doBefore();
		
		int[] roll15 = {2, 7, 1, 6, 0};
		int[] comp15 = {2, 7, 1, 6, 0};
		assertTrue(ScorePad.equals(roll15, comp15));
		
		doAfter();
		doBefore();
		
		int[] roll16 = {6, 0, 6, 1, 7};
		int[] comp16 = {2, 0, 6, 1, 7};
		assertFalse(ScorePad.equals(roll16, comp16));
		
		doAfter();
		doBefore();
		
		int[] roll17 = {6, 1, 7, 2, 0};
		int[] comp17 = {6, 1, 7, 2, 0};
		assertTrue(ScorePad.equals(roll17, comp17));
		
		doAfter();
		doBefore();
		
		int[] roll18 = {6, 2, 0, 6, 1};
		int[] comp18 = {7, 2, 0, 6, 1};
		assertFalse(ScorePad.equals(roll18, comp18));
		
		doAfter();
		doBefore();
		
		int[] roll19 = {6, 6, 1, 7, 2};
		int[] comp19 = {0, 6, 1, 7, 2};
		assertFalse(ScorePad.equals(roll19, comp19));
		
		doAfter();
		doBefore();
		
		int[] roll20 = {6, 7, 2, 0, 6};
		int[] comp20 = {1, 7, 2, 0, 6};
		assertFalse(ScorePad.equals(roll20, comp20));
		
		doAfter();
		doBefore();
		
		int[] roll21 = {7, 0, 7, 6, 2};
		int[] comp21 = {1, 0, 7, 6, 2};
		assertFalse(ScorePad.equals(roll21, comp21));
		
		doAfter();
		doBefore();
		
		int[] roll22 = {7, 1, 0, 7, 6};
		int[] comp22 = {2, 1, 0, 7, 6};
		assertFalse(ScorePad.equals(roll22, comp22));
		
		doAfter();
		doBefore();
		
		int[] roll23 = {7, 2, 1, 0, 7};
		int[] comp23 = {6, 2, 1, 0, 7};
		assertFalse(ScorePad.equals(roll23, comp23));
		
		doAfter();
		doBefore();
		
		int[] roll24 = {7, 6, 2, 1, 0};
		int[] comp24 = {7, 6, 2, 1, 0};
		assertTrue(ScorePad.equals(roll24, comp24));
		
		doAfter();
		doBefore();
		
		int[] roll25 = {7, 7, 6, 2, 1};
		int[] comp25 = {0, 7, 6, 2, 1};
		assertFalse(ScorePad.equals(roll25, comp25));
		
		doAfter();
	}
	
	
	
	// Dice.java ISP Test -------------------------------------------------------------
	
	//Test reroll ----------------------------------------------------------------------
	@Test
	public void testReroll() {
		doBefore();
		
		Dice d = new Dice();
		
		//Can't test first case (where all dice are rerolled),
		// since every reroll could potentially be the same values-
		// as the previous roll since it's randomized.
		
		doAfter();
		doBefore();
		
		int[] roll3 = d.getDiceValues();
		d.reroll(true, false, false, false, false);
		int[] roll4 = d.getDiceValues();
		assertTrue(roll3[1] == roll4[1]);
		assertTrue(roll3[2] == roll4[2]);
		assertTrue(roll3[3] == roll4[3]);
		assertTrue(roll3[4] == roll4[4]);
		
		doAfter();
		doBefore();
		
		int[] roll5 = d.getDiceValues();
		d.reroll(false, true, false, true, false);
		int[] roll6 = d.getDiceValues();
		assertTrue(roll5[0] == roll6[0]);
		assertTrue(roll5[2] == roll6[2]);
		assertTrue(roll5[4] == roll6[4]);
		
		doAfter();
		doBefore();
		
		int[] roll7 = d.getDiceValues();
		d.reroll(false, false, true, false, true);
		int[] roll8 = d.getDiceValues();
		assertTrue(roll7[1] == roll8[1]);
		assertTrue(roll7[0] == roll8[0]);
		assertTrue(roll7[3] == roll8[3]);
		
		doAfter();
		
	}
	
	
	
	// GUI.java ISP Test --------------------------------------------------------------
	
	//Test isUsedRow -----------------------------------------------------------------
	@Test
	public void testIsUsedRow() { //STILL IN PROGRESS
		doBefore();

		int row1 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row1));

		doAfter();
		doBefore();
		
		int row2 = 1;
		GUI.usedCategories[0] = 2; GUI.usedCategories[5] = 2; GUI.usedCategories[10] = 2;
		GUI.usedCategories[1] = 2; GUI.usedCategories[6] = 2; GUI.usedCategories[11] = 2;
		GUI.usedCategories[2] = 2; GUI.usedCategories[7] = 2; GUI.usedCategories[12] = 2;
		GUI.usedCategories[3] = 2; GUI.usedCategories[8] = 2; 
		GUI.usedCategories[4] = 2; GUI.usedCategories[9] = 2;
		assertTrue(GUI.isUsedRow(row2));
		
		doAfter();
		doBefore();
		
		int row3 = 1;
		GUI.usedCategories[0] = 3; GUI.usedCategories[5] = 3; GUI.usedCategories[10] = 3;
		GUI.usedCategories[1] = 3; GUI.usedCategories[6] = 3; GUI.usedCategories[11] = 3;
		GUI.usedCategories[2] = 3; GUI.usedCategories[7] = 3; GUI.usedCategories[12] = 3;
		GUI.usedCategories[3] = 3; GUI.usedCategories[8] = 3; 
		GUI.usedCategories[4] = 3; GUI.usedCategories[9] = 3;
		assertTrue(GUI.isUsedRow(row3));
		
		doAfter();
		doBefore();
		
		int row4 = 1;
		GUI.usedCategories[0] = 22; GUI.usedCategories[5] = 22; GUI.usedCategories[10] = 22;
		GUI.usedCategories[1] = 22; GUI.usedCategories[6] = 22; GUI.usedCategories[11] = 22;
		GUI.usedCategories[2] = 22; GUI.usedCategories[7] = 22; GUI.usedCategories[12] = 22;
		GUI.usedCategories[3] = 22; GUI.usedCategories[8] = 22; 
		GUI.usedCategories[4] = 22; GUI.usedCategories[9] = 22;
		assertTrue(GUI.isUsedRow(row4));
		
		doAfter();
		doBefore();
		
		int row5 = 1;
		GUI.usedCategories[0] = 23; GUI.usedCategories[5] = 23; GUI.usedCategories[10] = 23;
		GUI.usedCategories[1] = 23; GUI.usedCategories[6] = 23; GUI.usedCategories[11] = 23;
		GUI.usedCategories[2] = 23; GUI.usedCategories[7] = 23; GUI.usedCategories[12] = 23;
		GUI.usedCategories[3] = 23; GUI.usedCategories[8] = 23; 
		GUI.usedCategories[4] = 23; GUI.usedCategories[9] = 23;
		assertTrue(GUI.isUsedRow(row5));
		
		doAfter();
		doBefore();
		
		int row6 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row6));
		
		doAfter();
		doBefore();
		
		int row7 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row7));
		
		doAfter();
		doBefore();
		
		int row8 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row8));
		
		doAfter();
		doBefore();
		
		int row9 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row9));
		
		doAfter();
		doBefore();
		
		int row10 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row10));
		
		doAfter();
		doBefore();
		
		int row11 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row11));
		
		doAfter();
		doBefore();
		
		int row12 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row12));
		
		doAfter();
		doBefore();
		
		int row13 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row13));
		
		doAfter();
		doBefore();
		
		int row14 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row14));
		
		doAfter();
		doBefore();
		
		int row15 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row15));
		
		doAfter();
		doBefore();
		
		int row16 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row16));
		
		doAfter();
		doBefore();
		
		int row17 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row17));
		
		doAfter();
		doBefore();
		
		int row18 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row18));
		
		doAfter();
		doBefore();
		
		
		int row19 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row19));
		
		doAfter();
		doBefore();
		
		int row20 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row20));
		
		doAfter();
		doBefore();
		
		int row21 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row21));
		
		doAfter();
		doBefore();
		
		int row22 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row22));
		
		doAfter();
		doBefore();
		
		int row23 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row23));
		
		doAfter();
		doBefore();
		
		int row24 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row24));
		
		doAfter();
		doBefore();
		
		int row25 = 1;
		GUI.usedCategories[0] = 1; GUI.usedCategories[5] = 1; GUI.usedCategories[10] = 1;
		GUI.usedCategories[1] = 1; GUI.usedCategories[6] = 1; GUI.usedCategories[11] = 1;
		GUI.usedCategories[2] = 1; GUI.usedCategories[7] = 1; GUI.usedCategories[12] = 1;
		GUI.usedCategories[3] = 1; GUI.usedCategories[8] = 1; 
		GUI.usedCategories[4] = 1; GUI.usedCategories[9] = 1;
		assertTrue(GUI.isUsedRow(row25));
		
		doAfter();
	}
	
	
}
