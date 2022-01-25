import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author Nimra
 * 
 *         Icon made for Stones class
 */
public class GameIcon implements Icon {
	private GameShape gameShape;

	/**
	 * Constructs a GameIcon object
	 * 
	 * @param gameShape - the GameShape taken by gameIcon
	 */
	public GameIcon(GameShape gameShape) {
		this.gameShape = gameShape;
	}
	
	/**
	 * Returns Icon's width
	 * 
	 * @return 5 by default
	 */
	public int getIconWidth() {
		return 5;
	}

	/**
	 * Returns Icon's height
	 * 
	 * @return 5 by default
	 */
	public int getIconHeight() {
		return 5;
	}



	/**
	 * Paints the icon by delegating draw to the GameShape
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		gameShape.draw(g2);
	}

}