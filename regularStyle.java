import java.awt.Color;

/**
 * concrete class
 * 
 * @author Nimra
 */
public class regularStyle implements BoardStyle {

	/**
	 * the color used for background of each panel
	 * 
	 * @return light gray color
	 */
	@Override
	public Color getColor() {
		return Color.LIGHT_GRAY;
	}

}