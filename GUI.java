package YahtzeeProject;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;

public class GUI {

	private static JFrame frmYahtzeegui;
	public static JTable scoreTable;
	private final static int TURNS = 13;
	private final static int ROLLS = 3;
	
	public final static int ACES_ROW = 2; //
	public final static int TWOS_ROW = 3;
	public final static int THREES_ROW = 4;
	public final static int FOURS_ROW = 5;
	public final static int FIVES_ROW = 6;
	public final static int SIXES_ROW = 7;
	public final static int UPPER_TOTAL_ROW = 9;
	public final static int UPPER_BONUS_ROW = 8;
	public final static int THREE_OF_A_KIND_ROW = 12;
	public final static int FOUR_OF_A_KIND_ROW = 13;
	public final static int FULL_HOUSE_ROW = 14;
	public final static int SMALL_STRAIGHT_ROW = 15;
	public final static int LARGE_STRAIGHT_ROW = 16;
	public final static int YAHTZEE_ROW = 17;
	public final static int CHANCE_ROW = 18;
	public final static int TOTAL_LOWER_ROW = 19;
	public final static int GRAND_TOTAL_ROW = 21;
	public final static int[] validSelectionRows = {
		ACES_ROW, TWOS_ROW, THREES_ROW, FOURS_ROW, FIVES_ROW, SIXES_ROW,
		THREE_OF_A_KIND_ROW, FOUR_OF_A_KIND_ROW, FULL_HOUSE_ROW, SMALL_STRAIGHT_ROW,
		LARGE_STRAIGHT_ROW, YAHTZEE_ROW, CHANCE_ROW
	};
	private static JButton rollBtn;
	private static JTextPane dice1;
	private static JTextPane dice2;
	private static JTextPane dice3;
	private static JTextPane dice4;
	private static JTextPane dice5;
	private static boolean dice1Lock = false;
	private static boolean dice2Lock = false;
	private static boolean dice3Lock = false;
	private static boolean dice4Lock = false;
	private static boolean dice5Lock = false;
	
	private static Dice d;
	private static int[] usedCategories;
	private static int currTurn;
	private static int currRoll;
	private JLabel turnsLabel;
	private JLabel rollLabel;
	private static JToggleButton dice1LockBtn;
	private static JToggleButton dice2LockBtn;
	private static JToggleButton dice3LockBtn;
	private static JToggleButton dice4LockBtn;
	private static JToggleButton dice5LockBtn;
	private static JButton playAgainBtn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					d = new Dice();
					usedCategories = new int[TURNS];
					currTurn = 1;
					currRoll = 1;
					GUI window = new GUI();
					d.reroll(true, true, true, true, true);
		    		int[] vals = d.getDiceValues();
		    		dice1.setText((Integer.toString(vals[0])));
		    		dice2.setText((Integer.toString(vals[1])));
		    		dice3.setText((Integer.toString(vals[2])));
		    		dice4.setText((Integer.toString(vals[3])));
		    		dice5.setText((Integer.toString(vals[4])));
					frmYahtzeegui.setVisible(true);
					ScorePad.update(vals);
					frmYahtzeegui.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		frmYahtzeegui = new JFrame();
		frmYahtzeegui.setResizable(false);
		frmYahtzeegui.setTitle("YahtzeeGUI");
		frmYahtzeegui.setBounds(100, 100, 769, 444);
		frmYahtzeegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel gameBoard = new JPanel();
		frmYahtzeegui.getContentPane().add(gameBoard, BorderLayout.CENTER);
		
		rollBtn = new JButton("Roll Dice");
		rollBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rollBtn.setBounds(183, 49, 113, 46);
		rollBtn.setFocusable(false);
		rollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	
	    		d.reroll(!dice1Lock, !dice2Lock, !dice3Lock, !dice4Lock, !dice5Lock);
	    		int[] vals = d.getDiceValues();
	    		dice1.setText((Integer.toString(vals[0])));
	    		dice2.setText((Integer.toString(vals[1])));
	    		dice3.setText((Integer.toString(vals[2])));
	    		dice4.setText((Integer.toString(vals[3])));
	    		dice5.setText((Integer.toString(vals[4])));
	    		
	    		ScorePad.update(vals);
	    		frmYahtzeegui.repaint();
		    	
		    	rollLabel.setText("Roll " + ++currRoll + "/" + ROLLS);
		    	
		    	if(currRoll > 2) {
		    		rollBtn.setEnabled(false);
		    	}
			}
		});
		gameBoard.setLayout(null);
		
		dice1 = new JTextPane();
		dice1.setEditable(false);
		dice1.setBounds(183, 132, 38, 20);
		gameBoard.add(dice1);
		gameBoard.add(rollBtn);
		
		dice2 = new JTextPane();
		dice2.setEditable(false);
		dice2.setBounds(183, 163, 38, 20);
		gameBoard.add(dice2);
		
		dice3 = new JTextPane();
		dice3.setEditable(false);
		dice3.setBounds(183, 194, 38, 20);
		gameBoard.add(dice3);
		
		dice4 = new JTextPane();
		dice4.setEditable(false);
		dice4.setBounds(183, 225, 38, 20);
		gameBoard.add(dice4);
		
		dice5 = new JTextPane();
		dice5.setEditable(false);
		dice5.setBounds(183, 256, 38, 20);
		gameBoard.add(dice5);
		
		dice1LockBtn = new JToggleButton("Lock");
		dice1LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice1Lock) {
					dice1Lock = true;
					dice1LockBtn.setText("Unlock");
				} else {
					dice1Lock = false;
					dice1LockBtn.setText("Lock");
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice1LockBtn.setBounds(230, 132, 75, 20);
		dice1LockBtn.setFocusable(false);
		gameBoard.add(dice1LockBtn);
		
		dice2LockBtn = new JToggleButton("Lock");
		dice2LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice2Lock) {
					dice2Lock = true;
					dice2LockBtn.setText("Unlock");
				} else {
					dice2Lock = false;
					dice2LockBtn.setText("Lock");
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice2LockBtn.setBounds(230, 163, 75, 20);
		dice2LockBtn.setFocusable(false);
		gameBoard.add(dice2LockBtn);
		
		dice3LockBtn = new JToggleButton("Lock");
		dice3LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice3Lock) {
					dice3Lock = true;
					dice3LockBtn.setText("Unlock");
				} else {
					dice3Lock = false;
					dice3LockBtn.setText("Lock");
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice3LockBtn.setBounds(231, 194, 75, 20);
		dice3LockBtn.setFocusable(false);
		gameBoard.add(dice3LockBtn);
		
		dice4LockBtn = new JToggleButton("Lock");
		dice4LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice4Lock) {
					dice4Lock = true;
					dice4LockBtn.setText("Unlock");
				} else {
					dice4Lock = false;
					dice4LockBtn.setText("Lock");
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice4LockBtn.setBounds(231, 225, 75, 20);
		dice4LockBtn.setFocusable(false);
		gameBoard.add(dice4LockBtn);
		
		dice5LockBtn = new JToggleButton("Lock");
		dice5LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice5Lock) {
					dice5Lock = true;
					dice5LockBtn.setText("Unlock");
				} else {
					dice5Lock = false;
					dice5LockBtn.setText("Lock");
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice5LockBtn.setBounds(231, 256, 75, 20);
		dice5LockBtn.setFocusable(false);
		gameBoard.add(dice5LockBtn);
		//frmYahtzeegui.getContentPane().setLayout(null);
		
		//JTable doesn't work with absolute layout, so maybe put JTable within panel that can't move in frame?
		scoreTable = new JTable(typeScores, columnNames);
		scoreTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scoreTable.setModel(new DefaultTableModel(
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
		scoreTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		scoreTable.getColumnModel().getColumn(1).setPreferredWidth(44);
		scoreTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		gameBoard.add(scoreTable);
		scoreTable.setEnabled(false);
		scoreTable.setBounds(437, 27, 232, 352);
		scoreTable.setFocusable(false);
		
		turnsLabel = new JLabel("Turn 1/13");
		turnsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		turnsLabel.setBounds(28, 145, 107, 28);
		gameBoard.add(turnsLabel);
		
		rollLabel = new JLabel("Roll 1/3");
		rollLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rollLabel.setBounds(28, 194, 107, 28);
		gameBoard.add(rollLabel);
		
		playAgainBtn = new JButton("Play again?");
		playAgainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearDiceValues();
				clearLocks();
				clearScoreTable();
				usedCategories = new int[TURNS];
				currTurn = 1;
				turnsLabel.setText("Turn " + currTurn + "/" + TURNS);
				currRoll = 1;
				rollLabel.setText("Roll " + currRoll + "/" + ROLLS);
				d.reroll(true, true, true, true, true);
				int[] vals = d.getDiceValues();
				dice1.setText((Integer.toString(vals[0])));
	    		dice2.setText((Integer.toString(vals[1])));
	    		dice3.setText((Integer.toString(vals[2])));
	    		dice4.setText((Integer.toString(vals[3])));
	    		dice5.setText((Integer.toString(vals[4])));
				ScorePad.isFirstYahtzee = true;
				ScorePad.update(vals);
				playAgainBtn.setVisible(false);
				frmYahtzeegui.repaint();
				rollBtn.setEnabled(true);
			}
		});
		playAgainBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		playAgainBtn.setBounds(314, 333, 113, 46);
		playAgainBtn.setVisible(false);
		gameBoard.add(playAgainBtn);
		
		//General idea for clicking on cell to confirm score
		scoreTable.addMouseListener(new java.awt.event.MouseAdapter() {
			
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent event) {
		    	int row = scoreTable.rowAtPoint(event.getPoint());
		    	
		    	if(!(ScorePad.contains(validSelectionRows, row)) || isUsedRow(row)) {
		    		return;
		    	}
		    	
		    	currTurn++;		    	
		    	scoreTable.setValueAt("X", row, 2);
		    	
		    	if(currTurn > 13) {
		    		endGame();
		    		return;
		    	}
		    	
		    	usedCategories[currTurn - 1] = row;
		    	turnsLabel.setText("Turn " + currTurn + "/" + TURNS);
		    	currRoll = 1;
		    	rollLabel.setText("Roll " + currRoll + "/" + ROLLS);
		    	rollBtn.setEnabled(true);
		    	
		    	clearLocks();
		    	clearDiceValues();
		    	
		    	
		    }
		});
	}
	
	private static void clearLocks() {
		dice1Lock = false;
		dice1LockBtn.setText("Lock");
		dice1LockBtn.setSelected(false);
		dice2Lock = false;
		dice2LockBtn.setText("Lock");
		dice2LockBtn.setSelected(false);
		dice3Lock = false;
		dice3LockBtn.setText("Lock");
		dice3LockBtn.setSelected(false);
		dice4Lock = false;
		dice4LockBtn.setText("Lock");
		dice4LockBtn.setSelected(false);
		dice5Lock = false;
		dice5LockBtn.setText("Lock");
		dice5LockBtn.setSelected(false);
	}
	
	private static void clearDiceValues() {
		d.reroll(true, true, true, true, true);
		int[] vals = d.getDiceValues();
		dice1.setText((Integer.toString(vals[0])));
		dice2.setText((Integer.toString(vals[1])));
		dice3.setText((Integer.toString(vals[2])));
		dice4.setText((Integer.toString(vals[3])));
		dice5.setText((Integer.toString(vals[4])));
		ScorePad.update(vals);
		frmYahtzeegui.repaint();
	}
	
	public static boolean isUsedRow(int row) {
		return ScorePad.contains(usedCategories, row);
	}
	
	public static void endGame() {
		clearDiceValues();
		clearLocks();
		int sumUpper = sumUpper();	
		if(sumUpper > ScorePad.UPPER_BONUS_REQ) {
			scoreTable.setValueAt(ScorePad.UPPER_BONUS_VALUE, UPPER_BONUS_ROW, 1);
			sumUpper += ScorePad.UPPER_BONUS_VALUE;
		} else {
			scoreTable.setValueAt(0, UPPER_BONUS_ROW, 1);
		}
		
		scoreTable.setValueAt(sumUpper, UPPER_TOTAL_ROW, 1);
		scoreTable.setValueAt(sumLower(), TOTAL_LOWER_ROW, 1);
		scoreTable.setValueAt(sumUpper + sumLower(), GRAND_TOTAL_ROW, 1);
		
		playAgainBtn.setVisible(true);
	}
	
	private static int sumUpper() {
		return (int) scoreTable.getValueAt(ACES_ROW, 1) +
			   (int) scoreTable.getValueAt(TWOS_ROW, 1) +
			   (int) scoreTable.getValueAt(THREES_ROW, 1) +
			   (int) scoreTable.getValueAt(FOURS_ROW, 1) +
			   (int) scoreTable.getValueAt(FIVES_ROW, 1) +
			   (int) scoreTable.getValueAt(SIXES_ROW, 1);
	}
	
	private static int sumLower() {
		return (int) scoreTable.getValueAt(THREE_OF_A_KIND_ROW, 1) +
			   (int) scoreTable.getValueAt(FOUR_OF_A_KIND_ROW, 1) +
			   (int) scoreTable.getValueAt(FULL_HOUSE_ROW, 1) +
			   (int) scoreTable.getValueAt(SMALL_STRAIGHT_ROW, 1) +
			   (int) scoreTable.getValueAt(LARGE_STRAIGHT_ROW, 1) +
			   (int) scoreTable.getValueAt(YAHTZEE_ROW, 1) +
			   (int) scoreTable.getValueAt(CHANCE_ROW, 1);
	}
	
	private static void clearScoreTable() {
		for(int row = 2; row <= GRAND_TOTAL_ROW; row++) {
			scoreTable.setValueAt(null, row, 1);
			scoreTable.setValueAt(null, row, 2);
		}
	}
}
