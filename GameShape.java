import java.awt.*;
import java.awt.geom.*;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GameShape can hold multiple shapes (its a congregate)
 * 
 * @author Nimra
 */
public class GameShape {
	
	private JLabel label;
	private JPanel panel;
	private Path2D.Double path;


	/**
	 * Appends a shape to path
	 * 
	 * @param s - shape to needs be added
	 */
	protected void add(Shape s) {
		path.append(s, false);
	}
	
	/**
	 * Constructor for a GameShape
	 */
	public GameShape() {
		path = new Path2D.Double();
	}



	/**
	 * Used to add a JPanel reference
	 * 
	 * @param panel - panel that needs to be added
	 */
	public void addPanel(JPanel panel) {
		this.panel = panel;
	}
	
	/**
	 * Draws shapes in the path
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		g2.draw(path);
	}
	
	/**
	 * to check if a point is there in the path
	 * 
	 * @param point - checking the certain point
	 * @return - is true if the path has the point.
	 */
	public boolean contains(Point2D point) {
		return path.contains(point);
	}


	/**
	 * Gets the JPanel
	 * 
	 * @return - returns the JPanel
	 */
	public JPanel getPanel() {
		return panel;
	}
	
	/**
	 * Gets the JLabel
	 * 
	 * @return - returns the JLabel
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * Adds a JLabel
	 * 
	 * @param label - the JLabel to add
	 */
	public void addLabel(JLabel label) {
		this.label = label;
	}


	/**
	 * Removes all shapes in the path
	 */
	public void reset() {
		path.reset();
	}

}