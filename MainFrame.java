import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private Board mainBoard;

	public MainFrame() {

		setup();
	}

	private void setup() {

		add(mainBoard);

		setResizable(false);
		setSize(mainBoard.getWidth() + 50, mainBoard.getHeight() + 250);

		setTitle("Sudoku Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
