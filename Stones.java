import java.awt.geom.Ellipse2D;

/**
 * StoneCluster is a the main GameShape class that holds the numberOfStones in a
 * Pit
 * 
 * @author Nimra
 */
public class Stones extends GameShape {
	int indexArray;
	int numOfStones;
	boolean isA;

	/**
	 * StoneCluster constructor that starts the cluster with specified number of
	 * stones
	 * 
	 * @param numberOfStones
	 * @param indexInArray
	 */
	public Stones(int numberOfStones, int indexInArray, boolean isA) {
		this.indexArray = indexInArray;
		this.isA = isA;
		// Creates and adds a specified number of new circles to the GameShape -
		// StoneCluster
		for (int i = 0; i < numberOfStones; i++) {
			this.numOfStones++;
			add(new Ellipse2D.Double(30 + (this.numOfStones % 5) * 10, 20 + (this.numOfStones % 6) * 21, 10, 10));
		}
	}

	/**
	 * Add one stone to the cluster
	 */
	public void addOneStone() {
		this.numOfStones++;
		add(new Ellipse2D.Double(30 + (this.numOfStones % 5) * 10, 20 + (this.numOfStones % 6) * 21, 10, 10));
		// super.getLabel().repaint();
	}

	/**
	 * Add a specified number of stones to the cluster
	 * 
	 * @param number - the number of stones to add
	 */
	public void addNumOfStones(int number) {
		for (int i = 0; i < number; i++) {
			this.numOfStones++;
			add(new Ellipse2D.Double(30 + (this.numOfStones % 5) * 10, 20 + (this.numOfStones % 6) * 21, 10, 10));
		}

	}

	/**
	 * Zero's out the stones in this group
	 */
	public void zeroStones() {
		this.reset();
		this.numOfStones = 0;

	}

	/**
	 * 
	 * @return the StoneCluster object
	 */
	public Stones getStones() {
		return this;
	}

	/**
	 * @return the numOfStones
	 */
	public int getNumOfStones() {
		return numOfStones;
	}

	/**
	 * @param numOfStones the numOfStones to set
	 */
	public void setNumberOfStones(int numberOfStones) {
		this.numOfStones = numberOfStones;
	}

	/**
	 * @return the indexArray
	 */
	public int getIndexArray() {
		return indexArray;
	}

	/**
	 * @param indexArray the indexArray to set
	 */
	public void setIndexArray(int indexArray) {
		this.indexArray = indexArray;
	}

}