import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * The Panel that holds a GameShape and it can be put
 * onto a Jframe
 * 
 * @author Nimra
 */
public class ShapePanel extends JPanel {

	private GameShape gameShape;

	/**
	 * Constructor for ShapePanel
	 * 
	 * @param gameShape - the gameShape to be held by the ShapePanel
	 */
	public ShapePanel(GameShape gameShape) {
		this.gameShape = gameShape;
		
		// adds the GameShape to JPanel
		gameShape.addPanel(this);
	}

	/**
	 * Paints the icon by delegating draw to the GameShape
	 */
	public void paintComponent(Graphics g) {
		// Paints the component based on how each GameShape defines itself to look
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		gameShape.draw(g2);
	}

}