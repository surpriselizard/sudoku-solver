import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 *	Version: 16/06/2021
 *	- Added a 'solveButton' and an ActionListener
 */

public class MainFrame extends JFrame {

	private Board mainBoard;
	private JButton solveButton;

	public MainFrame() {

		setup();
	}

	private void setup() {

		mainBoard = new Board();
		solveButton = new JButton("Solve Puzzle");

		add(mainBoard);
		add(solveButton);

		setResizable(false);
		setSize(mainBoard.getWidth() + 50, mainBoard.getHeight() + 250);

		setTitle("Sudoku Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public class solveListener extends ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
