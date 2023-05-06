package YahtzeeProject;

import static org.junit.Assert.*;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ISPTest {

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
	public void test() {
		fail("Not yet implemented");
	}

}
