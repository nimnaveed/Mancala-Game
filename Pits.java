import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

/**
 * Defines the shape of the Pit
 * 
 * @author Nimra
 *
 */
public class Pits extends GameShape {

	/**
	 * Constructor for Pit - adds pit to path
	 * 
	 */
	public Pits() {

		Ellipse2D.Double pits = new Ellipse2D.Double(5, 20, 100, 100 * 1.5);

		add(pits);
	}

	/**
	 * A method to return this Pit
	 * 
	 * @return
	 */
	public Pits getPitShape() {
		return this;
	}

}