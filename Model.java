import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * model class holds game data and logic, notifies view when udpated
 * 
 * @author Nimra 
 */
public class Model {
	
	private ArrayList<ChangeListener> listeners;
	private ArrayList<Stones> stones;
	private int undoA;
	private int undoB;
	private boolean playerATurn;
	private int[] backUp;
	private int aVSB;
	private boolean isFirstTurn;
	private int startStones;
	private static final int ZERO = 0;
	private static final int NUM_OF_UNDO = 3;

	/**
	 * constructor for model
	 * 
	 * @param start starting num of stones
	 */
	public Model(int start) {
		startStones = start;
		aVSB = 0;
		isFirstTurn = true;
		backUp = new int[14];
		for (int i = 0; i < 14; i++) {
			backUp[i] = ZERO;
		}
		undoA = NUM_OF_UNDO;
		undoB = NUM_OF_UNDO;
		playerATurn = true;
		stones = new ArrayList<Stones>(14);
		for (int i = 0; i < 14; i++) {
			stones.add(new Stones(0, 0, true));
		}
		listeners = new ArrayList<ChangeListener>();
	}


	/**
	 * code for undo function
	 * 
	 */
	public void undo() {
		if (isFirstTurn != true && undoA != 0 && undoB != 0) {
			if (playerATurn == false || !playerATurn == true) {
				switchTurn();
			}
			if (aVSB < 6 && playerATurn != true) {
				switchTurn();
			}

			if ((aVSB > 6 && aVSB < 13) && playerATurn == true) {
				switchTurn();
			}

			if (playerATurn == true) {
				if (undoA > 0) {
					undoA -= 1;
					System.out.println("Player A has " + undoA + " undos left");
					for (int i = 0; i < stones.size(); i++) {
						stones.get(i).setNumberOfStones(backUp[i]);
					}
				}
			} else {
				if (undoB > 0) {
					undoB -= 1;
					System.out.println("Player B has " + undoB + " undos left");
					for (int i = 0; i < stones.size(); i++) {
						stones.get(i).setNumberOfStones(backUp[i]);
					}
				}
			}
		}

		for (ChangeListener l : listeners) {
			l.stateChanged(new ChangeEvent(this));
		}

	}
	
	/**
	 * back up the previous amount of stones into array, so we can 
	 * use it for the undo function
	 * 
	 * @param list array list of stones
	 */
	public void setBackUp(ArrayList<Stones> list) {
		for (int i = 0; i < list.size(); i++) {
			backUp[i] = list.get(i).getNumOfStones();
		}
	}


	/**
	 * check  last stone group, if num of stones is equal to 1 it was
	 * already empty and you take all of opponent stones and add it to yours
	 * 
	 * 
	 * @param a stone group where last stone was dropped
	 * @return
	 */
	public int takeAll(Stones a) {
		int num = 0;
		int numInLast = 0;
		if (a.numOfStones == 1) {
			Stones opp = stones.get(12 - a.getIndexArray());
			num = opp.getNumOfStones();
			opp.zeroStones();

			System.out.println("Opp stones: " + stones.get(12 - a.getIndexArray()).getNumOfStones());
		}
		return num + numInLast;
	}
	
	/**
	 * called every turn to check if either side is completely empty if either side
	 * is empty, game is over, higher score wins
	 * 
	 */
	public boolean isOver() {

		int total = 0;
		for (int i = 0; i < 6; i++) {
			total += stones.get(i).getNumOfStones();
		}
		if (total == 0) {
			int number = 0;
			for (int i = 7; i < 13; i++) {
				number += stones.get(i).getNumOfStones();
				stones.get(i).zeroStones();
			}
			stones.get(13).addNumOfStones(number);

			return true; // only if A area contains 0 stones
		}

		int total2 = 0;
		for (int i = 7; i < 13; i++) {
			total2 += stones.get(i).getNumOfStones();
		}
		if (total2 == 0) {
			int number = 0;
			for (int i = 0; i < 6; i++) {
				number += stones.get(i).getNumOfStones();
				stones.get(i).zeroStones();
			}
			stones.get(6).addNumOfStones(number);

			return true; // if B side has 0 stones
		}

		return false; // game is not over
	}


	/**
	 * Attach a listener to the model array
	 * 
	 * @param Listener the change listener to be added
	 */
	public void attach(ChangeListener listener) {
		listeners.add(listener);
	}
	
	/**
	 * switch player turns
	 * 
	 */
	public void switchTurn() {
		playerATurn = !playerATurn;
	}

	/**
	 * add a stone cluster to a specific index
	 * 
	 * @param sc    stone cluster to be added
	 * @param index desired index
	 */
	public void addStoneCluster(Stones sc, int index) {
		this.stones.set(index, sc);
	}


	/**
	 * logic for player taking turn, called when a pit is clicked
	 * 
	 * @param sc stone cluster that was clicked
	 */
	public void pickUpStones(Stones sc) {
		isFirstTurn = false;
		createbackUp();
		aVSB = sc.getIndexArray();

		// test for clicking on  empty pit
		int stonesPickedUp = sc.getNumOfStones();
		if (stonesPickedUp == 0) {
			System.out.println("Pick a Pit with STONES");
			return;
		}

		// see if current player has clicked own pit 
		if ((sc.getIndexArray() < 6 && playerATurn == true)
				|| ((sc.getIndexArray() > 6 && sc.getIndexArray() < 13) && playerATurn == false)) {


			int controlNumber = sc.getIndexArray() + stonesPickedUp;
			sc.zeroStones();
			int currIndex = sc.getIndexArray() + 1;

			// continue stones  dropping
			while (currIndex < sc.getIndexArray() + 1 + stonesPickedUp) {
				
				// checking that player does not drop stones into other player's mancala
				if (currIndex % 14 == 6 && !playerATurn || currIndex % 14 == 13 && playerATurn) {
					currIndex++;
					stonesPickedUp++;
					continue;
				}

				stones.get(currIndex % stones.size()).addOneStone();
				currIndex++;
			}

			currIndex -= 1;
			Stones lastCluster = stones.get(currIndex % 14);

			// checking conditions if a player takes all the stones from opposite pit
			if (currIndex % stones.size() < 6 && playerATurn == true) {
				stones.get(6).addNumOfStones(takeAll(lastCluster));
			}

			if ((currIndex % stones.size() < 13 && currIndex % stones.size() > 6)
					&& playerATurn == false) {
				stones.get(13).addNumOfStones(takeAll(lastCluster));
			}

			// checking whether player turn changes
			if ((controlNumber % stones.size()) != 6 && (controlNumber % stones.size()) != 13) {
				switchTurn();
				System.out.println("Turn has been changed");
			}

			// undos
			if (aVSB > 6 && aVSB < 13) {
				undoA = 3;
			}
			if (aVSB < 6) {
				undoB = 3;
			}

			for (ChangeListener l : listeners) {
				l.stateChanged(new ChangeEvent(this));
			}

			// checking for game over
			if (isOver()) {
				System.out.println("Game Over");
				if (stones.get(6).getNumOfStones() > stones.get(13).getNumOfStones()) {
					System.out.println("Player A won! ");
					JOptionPane.showMessageDialog(null, "Player A won", "Game Alert: " + "Winner Announcment",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					System.out.println("Player B won! ");
					JOptionPane.showMessageDialog(null, "Player B won", "Game Alert: " + "Winner Announcment",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} else {
			// error warning when clicking on wrong pit
			System.out.println("It's not your turn");
			JOptionPane.showMessageDialog(null, "Wrong pit!", "Game Alert", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	/**
	 * store previous update of stones into backup array when initializing
	 * 
	 */
	public void createbackUp() {
		for (int i = 0; i < 14; i++) {
			backUp[i] = stones.get(i).getNumOfStones();
		}
	}

	/**
	 * getter method for starting number of stones
	 * 
	 * @return int number of starting stones
	 */
	public int getStartingStones() {
		return this.startStones;
	}

	/**
	 * get current update of stones
	 * 
	 * @return array list of stones
	 */
	public ArrayList<Stones> getStones() {
		return stones;
	}
	
	/**
	 * getter method for current player turn
	 * 
	 * @return boolean true if it is player A's turn, false otherwise
	 */
	public boolean getPlayerTurn() {
		return this.playerATurn;
	}


	/**
	 * updating model data for stones
	 * 
	 * @param stoneClusters updated array list of stones
	 */
	public void setStoneClusters(ArrayList<Stones> stoneClusters) {
		this.stones = stoneClusters;
	}


	/**
	 * setting listeners
	 * 
	 * @param listeners change listeners to be set
	 */
	public void setListeners(ArrayList<ChangeListener> listeners) {
		this.listeners = listeners;
	}
	
	/**
	 * getter method for accessing list of change listeners
	 * 
	 */
	public ArrayList<ChangeListener> getListeners() {
		return listeners;
	}

}