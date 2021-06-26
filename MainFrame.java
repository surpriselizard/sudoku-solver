import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Debugging
/*
import javax.swing.border.LineBorder;
import javax.swing.JPanel;
import java.awt.Color;
*/

/*
 *	Version: 16/06/2021
 *	- Added a 'solveButton' and an ActionListener
 *
 *	Version: 18/06/2021
 *	- Added a 'FlowLayout'
 *	- Set the size of the 'solveButton'
 *
 *	Version: 22/06/2021
 *	- Fiddled with the layout a lot, will be changing it to a GridBagLayout
 *
 *	Version: 23/06/2021
 *	- Implemented a GridBagLayout for the mainFrame
 *	- There is some code commented out to show the outlines of the the grid for
 *	  debugging and educational purposes
 */

public class MainFrame extends JFrame implements ActionListener {

	private Board mainBoard;
	private JButton solveButton;

	public MainFrame() {

		setup();
	}

	private void setup() {

		mainBoard = new Board();
		solveButton = new JButton("Solve Puzzle");
		setLayout(new GridBagLayout());

		solveButton.setPreferredSize(new Dimension(140, 30));
		solveButton.addActionListener(this);

		add(mainBoard, new GridBagConstraints(
					0,	// gridx
					0,	// gridy
					3,	// gridwidth
					3,	//gridheight
					1,	// weightx
					1,	// weighty
					GridBagConstraints.FIRST_LINE_START, // anchor
					GridBagConstraints.NONE,	// fill
					new Insets(20, 25, 25, 0),	// Insets
					0,	// ipadx
					0)	// ipady
		   );

		add(solveButton, new GridBagConstraints(
					1,
					3,
					1,
					1,
					1,
					1,
					GridBagConstraints.PAGE_END,
					GridBagConstraints.NONE,
					new Insets(15, 10, 15, 10),
					0,
					0)
		   );

		/*
		for (int i = 0; i < 4; ++i) {

			for (int j = 0; j < 3; ++j) {

				JPanel tempPanel = new JPanel();
				tempPanel.setBorder(new LineBorder(Color.BLACK, 5));

				add(tempPanel, new GridBagConstraints(
							j,
							i,
							1,
							1,
							1,
							1,
							GridBagConstraints.CENTER,
							GridBagConstraints.BOTH,
							new Insets(0,0,0,0),
							0,
							0)
				   );
			}
		}
		*/

		setResizable(false);
		setSize(mainBoard.getWidth() + 50, mainBoard.getHeight() + 100);
		setTitle("Sudoku Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		mainBoard.solveGraph();
		repaint();
	}
}
