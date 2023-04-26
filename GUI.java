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

public class GUI {

	private JFrame frmYahtzeegui;
	public static JTable scoreTable;
	
	public final static int ACES_ROW = 1; //
	public final static int TWOS_ROW = 2;
	public final static int THREES_ROW = 3;
	public final static int FOURS_ROW = 4;
	public final static int FIVES_ROW = 5;
	public final static int SIXES_ROW = 6;
	public final static int UPPER_TOTAL_ROW = 7;
	public final static int UPPER_BONUS_ROW = 8;
	public final static int THREE_OF_A_KIND_ROW = 11;
	public final static int FOUR_OF_A_KIND_ROW = 12;
	public final static int FULL_HOUSE_ROW = 13;
	public final static int SMALL_STRAIGHT_ROW = 14;
	public final static int LARGE_STRAIGHT_ROW = 15;
	public final static int YAHTZEE_ROW = 16;
	public final static int CHANCE_ROW = 17;
	public final static int TOTAL_UPPER_ROW = 19;
	public final static int TOTAL_LOWER_ROW = 19;
	public final static int GRAND_TOTAL_ROW = 20;
	private JButton rollBtn;
	private JTextPane dice1;
	private JTextPane dice2;
	private JTextPane dice3;
	private JTextPane dice4;
	private JTextPane dice5;
	private boolean dice1Lock = false;
	private boolean dice2Lock = false;
	private boolean dice3Lock = false;
	private boolean dice4Lock = false;
	private boolean dice5Lock = false;
	private boolean started = false;
	private Dice d = new Dice();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmYahtzeegui.setVisible(true);
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
        	"Score"};

		Object[][] typeScores = {
				{"Upper Section", 0}, 
				{"Aces", 0},
				{"Twos", 0},
				{"Threes", 0},
				{"Fours", 0},
				{"Fives", 0},
				{"Sixes", 0},
				{"Upper Total", 0},
				{"Upper Bonus", 0},
				{"", ""},
				{"Lower Section", 0},
				{"3 of a kind", 0},
				{"4 of a kind", 0},
				{"Full House", 0},
				{"Sm. Straight", 0},
				{"Lg. Straight", 0},
				{"Yahtzee", 0},
				{"Chance", 0},
				{"Total Upper", 0},
				{"Total Lower", 0},
				{"Grand Total", 0}
		};

		frmYahtzeegui = new JFrame();
		frmYahtzeegui.setResizable(false);
		frmYahtzeegui.setTitle("YahtzeeGUI");
		frmYahtzeegui.setBounds(100, 100, 769, 444);
		frmYahtzeegui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel gameBoard = new JPanel();
		frmYahtzeegui.getContentPane().add(gameBoard, BorderLayout.CENTER);
		
		rollBtn = new JButton("Roll Dice");
		rollBtn.setBounds(225, 11, 94, 32);
		rollBtn.setFocusable(false);
		rollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ScorePad s = new ScorePad();
		    	
		    	if (!started) {
		    		d.reroll(true, true, true, true, true);
		    		started = true;
		    		int[] vals = d.getDiceValues();
		    		dice1.setText((Integer.toString(vals[0])));
		    		dice2.setText((Integer.toString(vals[1])));
		    		dice3.setText((Integer.toString(vals[2])));
		    		dice4.setText((Integer.toString(vals[3])));
		    		dice5.setText((Integer.toString(vals[4])));
		    	} else {
		    		d.reroll(!dice1Lock, !dice2Lock, !dice3Lock, !dice4Lock, !dice5Lock);
		    		int[] vals = d.getDiceValues();
		    		dice1.setText((Integer.toString(vals[0])));
		    		dice2.setText((Integer.toString(vals[1])));
		    		dice3.setText((Integer.toString(vals[2])));
		    		dice4.setText((Integer.toString(vals[3])));
		    		dice5.setText((Integer.toString(vals[4])));
		    	}
		    	
		    	
				
			}
		});
		gameBoard.setLayout(null);
		
		dice1 = new JTextPane();
		dice1.setEditable(false);
		dice1.setBounds(55, 54, 38, 20);
		gameBoard.add(dice1);
		gameBoard.add(rollBtn);
		
		dice2 = new JTextPane();
		dice2.setEditable(false);
		dice2.setBounds(154, 54, 38, 20);
		gameBoard.add(dice2);
		
		dice3 = new JTextPane();
		dice3.setEditable(false);
		dice3.setBounds(254, 54, 38, 20);
		gameBoard.add(dice3);
		
		dice4 = new JTextPane();
		dice4.setEditable(false);
		dice4.setBounds(358, 54, 38, 20);
		gameBoard.add(dice4);
		
		dice5 = new JTextPane();
		dice5.setEditable(false);
		dice5.setBounds(451, 54, 38, 20);
		gameBoard.add(dice5);
		
		JToggleButton dice1LockBtn = new JToggleButton("Lock");
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
		dice1LockBtn.setBounds(35, 85, 75, 20);
		dice1LockBtn.setFocusable(false);
		gameBoard.add(dice1LockBtn);
		
		JToggleButton dice2LockBtn = new JToggleButton("Lock");
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
		dice2LockBtn.setBounds(135, 85, 75, 20);
		dice2LockBtn.setFocusable(false);
		gameBoard.add(dice2LockBtn);
		
		JToggleButton dice3LockBtn = new JToggleButton("Lock");
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
		dice3LockBtn.setBounds(237, 85, 75, 20);
		dice3LockBtn.setFocusable(false);
		gameBoard.add(dice3LockBtn);
		
		JToggleButton dice4LockBtn = new JToggleButton("Lock");
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
		dice4LockBtn.setBounds(339, 85, 75, 20);
		dice4LockBtn.setFocusable(false);
		gameBoard.add(dice4LockBtn);
		
		JToggleButton dice5LockBtn = new JToggleButton("Lock");
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
		dice5LockBtn.setBounds(434, 85, 75, 20);
		dice5LockBtn.setFocusable(false);
		gameBoard.add(dice5LockBtn);
		//frmYahtzeegui.getContentPane().setLayout(null);
		
		//JTable doesn't work with absolute layout, so maybe put JTable within panel that can't move in frame?
		scoreTable = new JTable(typeScores, columnNames);
		gameBoard.add(scoreTable);
		scoreTable.setEnabled(false);
		scoreTable.setBounds(546, 30, 177, 336);
		scoreTable.setFocusable(false);
		
		//General idea for clicking on cell to confirm score
		scoreTable.addMouseListener(new java.awt.event.MouseAdapter() {
			
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent event) {
		        ScorePad.update(d.getDiceValues());
		        frmYahtzeegui.repaint();
		    }
		});
		
		
	}
}
