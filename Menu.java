import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * main menu class, allows user to choose board style and starting number of
 * stones
 * 
 * @author Nimra
 */
public class Menu {

	/**
	 * constructor for main menu, allows user to choose style and number of stones
	 * to start with
	 * 
	 */
	public Menu() {

		//frame/buttons
		JFrame frame = new JFrame();
		frame.setTitle("Mancala Main Menu");
		frame.setLayout(new FlowLayout());

		JButton classic = new JButton("Classic Theme : 4 Stones");
		JButton color = new JButton("Color Theme : 4 Stones");


		classic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Board(new regularStyle(), 4);
				frame.dispose();

			}
		});

		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Board(new ColorStyle(), 4);
				frame.dispose();

			}
		});

		frame.add(classic);
		frame.add(color);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}