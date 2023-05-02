package YahtzeeProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;


import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class GUI {

	public static JFrame frmYahtzeegui;
	public static JTable scoreTable;
	public final static int TURNS = 13;
	public final static int ROLLS = 3;
	
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
	public final static int YAHTZEE_BONUS_ROW = 18;
	public final static int CHANCE_ROW = 19;
	public final static int TOTAL_LOWER_ROW = 20;
	public final static int GRAND_TOTAL_ROW = 22;
	public final static int[] validSelectionRows = {
		ACES_ROW, TWOS_ROW, THREES_ROW, FOURS_ROW, FIVES_ROW, SIXES_ROW,
		THREE_OF_A_KIND_ROW, FOUR_OF_A_KIND_ROW, FULL_HOUSE_ROW, SMALL_STRAIGHT_ROW,
		LARGE_STRAIGHT_ROW, YAHTZEE_ROW, CHANCE_ROW, YAHTZEE_BONUS_ROW
	};
	public final static int[] validUpperRows = {
			ACES_ROW, TWOS_ROW, THREES_ROW, FOURS_ROW, FIVES_ROW, SIXES_ROW
		};
	public final static int[] validLowerRows = {
			THREE_OF_A_KIND_ROW, FOUR_OF_A_KIND_ROW, FULL_HOUSE_ROW, SMALL_STRAIGHT_ROW,
			LARGE_STRAIGHT_ROW, YAHTZEE_ROW, CHANCE_ROW, YAHTZEE_BONUS_ROW
		};
	public static JButton rollBtn;
	public static JTextPane dice1;
	public static JTextPane dice2;
	public static JTextPane dice3;
	public static JTextPane dice4;
	public static JTextPane dice5;
	private static boolean dice1Lock = false;
	private static boolean dice2Lock = false;
	private static boolean dice3Lock = false;
	private static boolean dice4Lock = false;
	private static boolean dice5Lock = false;
	
	public static Dice d;
	public static int[] usedCategories;
	public static int currTurn;
	public static int currRoll;
	public static JLabel turnsLabel;
	public static JLabel rollLabel;
	public static JToggleButton dice1LockBtn;
	public static JToggleButton dice2LockBtn;
	public static JToggleButton dice3LockBtn;
	public static JToggleButton dice4LockBtn;
	public static JToggleButton dice5LockBtn;
	public static JButton playAgainBtn;
	private JPanel gameBoard;
	public static boolean gameOver = false;
	public static int yahtzeeBonusScore = 0;
	

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
					for(int i = ACES_ROW; i <= GRAND_TOTAL_ROW; i++) {
						scoreTable.setValueAt("", i, 2);
					}
					ScorePad.update(vals);
					frmYahtzeegui.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					//This portion of code changes the theme 
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					
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
				{"Yahtzee Bonus", 0, ""},
				{"Chance", 0, ""},
				{"Total Lower", 0, ""},
				{"", "", ""},
				{"Grand Total", 0, ""}
		};

		frmYahtzeegui = new JFrame();
		frmYahtzeegui.setResizable(false);
		frmYahtzeegui.setTitle("Yahtzee");
		frmYahtzeegui.setBounds(100, 100, 800, 500);
		frmYahtzeegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		gameBoard = new JPanel();
		gameBoard.setBackground(Color.DARK_GRAY);
		frmYahtzeegui.getContentPane().add(gameBoard, BorderLayout.CENTER);
		
		rollBtn = new JButton("Roll Dice");
		rollBtn.setForeground(Color.WHITE);
		rollBtn.setBackground(Color.GRAY);
		rollBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		rollBtn.setBounds(188, 47, 113, 46);
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
		dice1.setBackground(Color.GRAY);
		dice1.setForeground(Color.WHITE);
		dice1.setEditable(false);
		dice1.setBounds(183, 132, 38, 20);
		gameBoard.add(dice1);
		gameBoard.add(rollBtn);
		
		dice2 = new JTextPane();
		dice2.setForeground(Color.WHITE);
		dice2.setBackground(Color.GRAY);
		dice2.setEditable(false);
		dice2.setBounds(183, 163, 38, 20);
		gameBoard.add(dice2);
		
		dice3 = new JTextPane();
		dice3.setForeground(Color.WHITE);
		dice3.setBackground(Color.GRAY);
		dice3.setEditable(false);
		dice3.setBounds(183, 194, 38, 20);
		gameBoard.add(dice3);
		
		dice4 = new JTextPane();
		dice4.setForeground(Color.WHITE);
		dice4.setBackground(Color.GRAY);
		dice4.setEditable(false);
		dice4.setBounds(183, 225, 38, 20);
		gameBoard.add(dice4);
		
		dice5 = new JTextPane();
		dice5.setForeground(Color.WHITE);
		dice5.setBackground(Color.GRAY);
		dice5.setEditable(false);
		dice5.setBounds(183, 256, 38, 20);
		gameBoard.add(dice5);
		
		dice1LockBtn = new JToggleButton("Lock");
		dice1LockBtn.setForeground(Color.WHITE);
		dice1LockBtn.setBackground(Color.GRAY);
		dice1LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice1Lock) {
					dice1Lock = true;
					dice1LockBtn.setText("Unlock");
					dice1LockBtn.setForeground(Color.BLACK);
				} else {
					dice1Lock = false;
					dice1LockBtn.setText("Lock");
					dice1LockBtn.setForeground(Color.WHITE);
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice1LockBtn.setBounds(230, 132, 75, 20);
		dice1LockBtn.setFocusable(false);
		gameBoard.add(dice1LockBtn);
		
		dice2LockBtn = new JToggleButton("Lock");
		dice2LockBtn.setForeground(Color.WHITE);
		dice2LockBtn.setBackground(Color.GRAY);
		dice2LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice2Lock) {
					dice2Lock = true;
					dice2LockBtn.setText("Unlock");
					dice2LockBtn.setForeground(Color.BLACK);
				} else {
					dice2Lock = false;
					dice2LockBtn.setText("Lock");
					dice2LockBtn.setForeground(Color.WHITE);
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice2LockBtn.setBounds(230, 163, 75, 20);
		dice2LockBtn.setFocusable(false);
		gameBoard.add(dice2LockBtn);
		
		dice3LockBtn = new JToggleButton("Lock");
		dice3LockBtn.setForeground(Color.WHITE);
		dice3LockBtn.setBackground(Color.GRAY);
		dice3LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice3Lock) {
					dice3Lock = true;
					dice3LockBtn.setText("Unlock");
					dice3LockBtn.setForeground(Color.BLACK);
				} else {
					dice3Lock = false;
					dice3LockBtn.setText("Lock");
					dice3LockBtn.setForeground(Color.WHITE);
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice3LockBtn.setBounds(230, 194, 75, 20);
		dice3LockBtn.setFocusable(false);
		gameBoard.add(dice3LockBtn);
		
		dice4LockBtn = new JToggleButton("Lock");
		dice4LockBtn.setForeground(Color.WHITE);
		dice4LockBtn.setBackground(Color.GRAY);
		dice4LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice4Lock) {
					dice4Lock = true;
					dice4LockBtn.setText("Unlock");
					dice4LockBtn.setForeground(Color.BLACK);
				} else {
					dice4Lock = false;
					dice4LockBtn.setText("Lock");
					dice4LockBtn.setForeground(Color.WHITE);
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice4LockBtn.setBounds(230, 225, 75, 20);
		dice4LockBtn.setFocusable(false);
		gameBoard.add(dice4LockBtn);
		
		dice5LockBtn = new JToggleButton("Lock");
		dice5LockBtn.setForeground(Color.WHITE);
		dice5LockBtn.setBackground(Color.GRAY);
		dice5LockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!dice5Lock) {
					dice5Lock = true;
					dice5LockBtn.setText("Unlock");
					dice5LockBtn.setForeground(Color.BLACK);
				} else {
					dice5Lock = false;
					dice5LockBtn.setText("Lock");
					dice5LockBtn.setForeground(Color.WHITE);
				}
				gameBoard.revalidate();
				gameBoard.repaint();
			}
		});
		dice5LockBtn.setBounds(230, 256, 75, 20);
		dice5LockBtn.setFocusable(false);
		gameBoard.add(dice5LockBtn);
		//frmYahtzeegui.getContentPane().setLayout(null);
		
		//JTable doesn't work with absolute layout, so maybe put JTable within panel that can't move in frame?
		scoreTable = new JTable(typeScores, columnNames);
		scoreTable.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		scoreTable.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
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
		scoreTable.getColumnModel().getColumn(0).setPreferredWidth(100);
		scoreTable.getColumnModel().getColumn(1).setPreferredWidth(44);
		scoreTable.getColumnModel().getColumn(2).setPreferredWidth(40);
		gameBoard.add(scoreTable);
		scoreTable.setEnabled(false);
		scoreTable.setBounds(437, 27, 232, 370);
		scoreTable.setFocusable(false);
		
		turnsLabel = new JLabel("Turn 1/13");
		turnsLabel.setForeground(Color.WHITE);
		turnsLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		turnsLabel.setBounds(423, 408, 131, 28);
		gameBoard.add(turnsLabel);
		
		rollLabel = new JLabel("Roll 1/3");
		rollLabel.setForeground(Color.WHITE);
		rollLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		rollLabel.setBounds(596, 408, 107, 28);
		gameBoard.add(rollLabel);
		
		playAgainBtn = new JButton("Play again?");
		playAgainBtn.setForeground(Color.WHITE);
		playAgainBtn.setBackground(Color.GRAY);
		playAgainBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameOver = false;
				yahtzeeBonusScore = 0;
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
		playAgainBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		playAgainBtn.setBounds(188, 323, 113, 46);
		playAgainBtn.setVisible(false);
		gameBoard.add(playAgainBtn);
		
		//General idea for clicking on cell to confirm score
		scoreTable.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent event) {
		    	if(!gameOver) {
		    		handleRowClick(event);
		    	}
		    }
		});
	}
	
	private static void clearLocks() {
		dice1Lock = false;
		dice1LockBtn.setText("Lock");
		dice1LockBtn.setSelected(false);
		dice1LockBtn.setForeground(Color.WHITE);
		dice2Lock = false;
		dice2LockBtn.setText("Lock");
		dice2LockBtn.setSelected(false);
		dice2LockBtn.setForeground(Color.WHITE);
		dice3Lock = false;
		dice3LockBtn.setText("Lock");
		dice3LockBtn.setSelected(false);
		dice3LockBtn.setForeground(Color.WHITE);
		dice4Lock = false;
		dice4LockBtn.setText("Lock");
		dice4LockBtn.setSelected(false);
		dice4LockBtn.setForeground(Color.WHITE);
		dice5Lock = false;
		dice5LockBtn.setText("Lock");
		dice5LockBtn.setSelected(false);
		dice5LockBtn.setForeground(Color.WHITE);
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
		GUI.scoreTable.setValueAt("X", YAHTZEE_BONUS_ROW, 2);
		GUI.scoreTable.setValueAt(yahtzeeBonusScore, YAHTZEE_BONUS_ROW, 1);
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
		int score = 0;
		for(int row : validUpperRows) {
			if(scoreTable.getValueAt(row, 2) == null) {
				continue;
			}
			if(scoreTable.getValueAt(row, 2).equals("X")) {
				score += (int) scoreTable.getValueAt(row, 1);
			}
		}
		return score;
	}
	
	private static int sumLower() {
		int score = 0;
		for(int row : validLowerRows) {
			if(scoreTable.getValueAt(row, 2) == null) {
				continue;
			}
			if(scoreTable.getValueAt(row, 2).equals("X")) {
				score += (int) scoreTable.getValueAt(row, 1);
			}
		}
		return score;
	}
	
	private static void clearScoreTable() {
		for(int row = 2; row <= GRAND_TOTAL_ROW; row++) {
			scoreTable.setValueAt(null, row, 1);
			scoreTable.setValueAt(null, row, 2);
		}
	}
	
	public static void handleRowClick(MouseEvent event) {
		
		int row = scoreTable.rowAtPoint(event.getPoint());
		
    	if(!(ScorePad.contains(validSelectionRows, row)) || isUsedRow(row)) {
    		return;
    	}
    	
    	int roll[] = new int[5];
    	roll[0] = Integer.parseInt(dice1.getText());
    	roll[1] = Integer.parseInt(dice2.getText());
    	roll[2] = Integer.parseInt(dice3.getText());
    	roll[3] = Integer.parseInt(dice4.getText());
    	roll[4] = Integer.parseInt(dice5.getText());
    	
    	if(row == YAHTZEE_BONUS_ROW && !ScorePad.isXOfKind(roll, 5)) {
    		return;
    	}
    	
    	if(row == YAHTZEE_BONUS_ROW && !isUsedRow(YAHTZEE_ROW)) {
    		return;
    	}
    	
    	if(row == YAHTZEE_BONUS_ROW && (int) scoreTable.getValueAt(row, 1) == 0) {
    		return;
    	}
    	
    	if(row == YAHTZEE_ROW && (int) scoreTable.getValueAt(row, 1) == 0) {
    		scoreTable.setValueAt("X", YAHTZEE_BONUS_ROW, 2);
    	}
    	
    	currTurn++;
    	
    	if(row == GUI.YAHTZEE_ROW) {
    		ScorePad.isFirstYahtzee = false;
    	}
    	
    	if(currTurn > 13) {
    		gameOver = true;
    		scoreTable.setValueAt("X", row, 2);
    		endGame();
    		return;
    	}
    	
    	if(row != GUI.YAHTZEE_BONUS_ROW) {
    		scoreTable.setValueAt("X", row, 2);
        	usedCategories[currTurn - 1] = row;
    	} else {
    		yahtzeeBonusScore = (int) scoreTable.getValueAt(YAHTZEE_BONUS_ROW, 1);
    	}
    	
    	turnsLabel.setText("Turn " + currTurn + "/" + TURNS);
    	currRoll = 1;
    	rollLabel.setText("Roll " + currRoll + "/" + ROLLS);
    	rollBtn.setEnabled(true);
    	
    	clearLocks();
    	clearDiceValues();
	}
	
}
