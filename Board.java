import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * this class is presented as view and controller
 * 
 * @author Nimra
 */
public class Board {

	/**
	 * constructor with two parameters, stones and theme
	 * 
	 * @param theme-theme the user selects at menu
	 * @param stones-amount of starting stones user selects at menu
	 */
	public Board(BoardStyle theme, int stones) {

		// create game model passing in starting number of stones
		final Model m = new Model(stones);

		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(960, 500));

		// the JPanel in the center that holds the main pits with GridLayout
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(2, 6));

		// Setting up player B's pits and stones
		for (int i = 12; i >= 7; i--) {
			Pits pits = new Pits();
			final ShapePanel jPit = new ShapePanel(pits);

			// getting the color from theme
			jPit.setBackground(theme.getColor());
			jPit.setLayout(new BorderLayout());

			// create stones
			// set who gets the pit belongs to
			Stones stone = new Stones(m.getStartingStones(), i, false);
			GameIcon iconStone = new GameIcon(stone);
			final GameLabel jStone = new GameLabel(iconStone, stone);

			// add the stone cluster to the pit component and respective label (ex. "A1")
			jPit.add(BorderLayout.CENTER, jStone);
			jPit.add(BorderLayout.NORTH, new JLabel("B" + (stone.getIndexArray() % 7 + 1)));

			// add mouse listener to each pit for player to "take a turn"
			jStone.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					m.pickUpStones(stone);
				}
			});
			// add to stones to model array
			m.addStoneCluster(stone, stone.getIndexArray());
			// add pit panel to the grid
			middle.add(jPit);

		}
		// Setting up Mancala B and its
		// StoneCluster
		Mancala mancalaB = new Mancala(10, 20);
		final ShapePanel jMancalaB = new ShapePanel(mancalaB);

		// get background color from theme
		jMancalaB.setBackground(theme.getColor());

		jMancalaB.setPreferredSize(new Dimension(120, 100));
		jMancalaB.setLayout(new BorderLayout());
		// mancala B begins empty and goes into index 13 in the model array, false
		// indicates the mancala belongs to player B
		Stones stonesManB = new Stones(0, 13, false);
		GameIcon iconStonesManB = new GameIcon(stonesManB);
		final GameLabel jStonesManB = new GameLabel(iconStonesManB, stonesManB);
		jMancalaB.add(BorderLayout.CENTER, jStonesManB);
		jMancalaB.add(BorderLayout.NORTH, new JLabel("Player B Mancala"));
		// testing mancala pit
		jMancalaB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mancala B @index " + stonesManB.getIndexArray());
				System.out.println("Stones: " + stonesManB.getNumOfStones());

			}
		});
		// add to model ArrayList
		m.addStoneCluster(stonesManB, stonesManB.getIndexArray());

		//Setting up the Player A's pits and its
		// StoneClusters
		// A's pits will be placed into the model array using index 0-5
		for (int i = 0; i <= 5; i++) {
			Pits pit = new Pits();
			final ShapePanel jPit = new ShapePanel(pit);

			jPit.setBackground(theme.getColor());

			jPit.setLayout(new BorderLayout());
			// create stone cluster for A pits using starting stones, index and boolean
			// depicting these pits belong to player A
			Stones stone = new Stones(m.getStartingStones(), i, true);
			GameIcon iconStones = new GameIcon(stone);
			final GameLabel jStones = new GameLabel(iconStones, stone);
			jPit.add(BorderLayout.CENTER, jStones);
			jPit.add(BorderLayout.NORTH, new JLabel("A" + (stone.getIndexArray() + 1)));
			// add mouse listener to each pit for player to take turn
			jStones.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					m.pickUpStones(stone);

				}
			});
			m.addStoneCluster(stone, stone.getIndexArray());

			middle.add(jPit);
		}

		// set up Mancala A with stones
		Mancala mancalaA = new Mancala(10, 20);
		final ShapePanel jMancalaA = new ShapePanel(mancalaA);

		jMancalaA.setBackground(theme.getColor());

		jMancalaA.setPreferredSize(new Dimension(120, 100));
		jMancalaA.setLayout(new BorderLayout());
		// mancala A begins empty and goes into index 6 in the model array, true
		// indicates the mancala belongs to player A
		Stones stonesManA = new Stones(0, 6, true);
		GameIcon iconStonesManA = new GameIcon(stonesManA);
		final GameLabel jStonesManA = new GameLabel(iconStonesManA, stonesManA);
		jMancalaA.add(BorderLayout.CENTER, jStonesManA);
		jMancalaA.add(BorderLayout.NORTH, new JLabel("Player A Mancala"));
		// testing mancala pit
		jMancalaA.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mancala A @index " + stonesManA.getIndexArray());
				System.out.println("Stones: " + stonesManA.getNumOfStones());
			}
		});
		// add to model ArrayList
		m.addStoneCluster(stonesManA, stonesManA.getIndexArray());

		ArrayList<Stones> sc = m.getStones();

		// store state for undo
		m.setBackUp(sc);

		// action listener for undo button
		ActionListener undoListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				m.undo();
				System.out.println("Undo is pressed!");
			}
		};

		// adding main pits and both mancala pits
		frame.add(BorderLayout.CENTER, middle);
		frame.add(BorderLayout.WEST, jMancalaB);
		frame.add(BorderLayout.EAST, jMancalaA);

		// create button bar
		String playerLabel = m.getPlayerTurn() ? "Player A Turn" : "Player B Turn";
		JLabel label = new JLabel(playerLabel);
		JPanel buttonPanel = new JPanel();
		JButton exit = new JButton("Exit");
		JButton undo = new JButton("Undo");
		undo.addActionListener(undoListener);
		// get color from theme
		buttonPanel.setBackground(theme.getColor());

		// player scoreboards
		JLabel playerAScore = new JLabel("Player A Score: " + m.getStones().get(6).getNumOfStones());
		JLabel playerBScore = new JLabel("Player B Score: " + m.getStones().get(13).getNumOfStones());

		// listens for model updates and repaints the view
		ChangeListener cListener = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				for (Stones sc : m.getStones()) {
					int numOfStones = sc.getNumOfStones();
					sc.zeroStones();
					sc.addNumOfStones(numOfStones);
					sc.getLabel().repaint();
				}
				playerAScore.setText("Player A Score: " + m.getStones().get(6).getNumOfStones());
				playerBScore.setText("Player B Score: " + m.getStones().get(13).getNumOfStones());
				playerAScore.repaint();
				playerBScore.repaint();
				String player = m.getPlayerTurn() ? "Player A's Turn" : "Player B's Turn";
				label.setText(player);
				label.repaint();
				buttonPanel.repaint();

			}
		};

		m.attach(cListener);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.exit(-1);
			}
		});

		// add all buttons and labels to button panel
		buttonPanel.add(playerBScore);
		buttonPanel.add(undo);
		buttonPanel.add(label);
		buttonPanel.add(exit);
		buttonPanel.add(playerAScore);
		frame.add(BorderLayout.NORTH, buttonPanel);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}