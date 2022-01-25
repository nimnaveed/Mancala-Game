import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * GameLabel is a JLabel that will hold an Icon, mainly an Icon of a
 * StoneCluster
 * 
 * @author Nimra
 *
 */
public class GameLabel extends JLabel {

	private Icon icon;
	private GameShape gameShape;


	/**
	 * Paints icon by delegating draw to GameShape
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		gameShape.draw(g2);
	}
	
	/**
	 * Constructor for a GameLabel
	 * 
	 * @param icon - the icon held in the GameIcon(Stones)
	 * @param gameShape   - a reference to the GameShape(Pit)
	 */
	public GameLabel(GameIcon icon, GameShape gameShape) {
		this.gameShape = gameShape;
		gameShape.addLabel(this);

		this.icon = icon;
	}

}