import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *	Version: 16/06/2021
 *	- Added a 'solveButton' and an ActionListener
 *
 *	Version: 18/06/2021
 *	- Added a 'FlowLayout'
 *	- Set the size of the 'solveButton'
 */

public class MainFrame extends JFrame {

	private Board mainBoard;
	private JButton solveButton;
	private FlowLayout layout;

	public MainFrame() {

		setup();
	}

	private void setup() {

		mainBoard = new Board();
		solveButton = new JButton("Solve Puzzle");
		layout = new FlowLayout(FlowLayout.LEFT);

		solveButton.setPreferredSize(new Dimension(40, 20));
		setLayout(layout);

		add(mainBoard);
		add(solveButton);

		setResizable(false);
		setSize(mainBoard.getWidth(), mainBoard.getHeight() + 250);
		setTitle("Sudoku Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public class solveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
