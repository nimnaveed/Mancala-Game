import java.awt.Color;
import java.util.Random;

/**
 * concrete class that takes care of strategy
 * 
 * @author Nimra
 */
public class ColorStyle implements BoardStyle {
	/**
	 * color for background of every panel
	 * 
	 * @return a random color
	 */
	@Override
	public Color getColor() {
		Random rand = new Random();
		int i = rand.nextInt(6);
		return i == 0 ? Color.ORANGE
				: i == 1 ? Color.CYAN
						: i == 2 ? Color.YELLOW : i == 3 ? Color.PINK : i == 4 ? Color.MAGENTA : Color.WHITE;
	}
}