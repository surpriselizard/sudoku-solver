import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public MainFrame() {

		setup();
	}

	private void setup() {

		add(new Board());

		setResizable(false);
		pack();

		setTitle("Sudoku Solver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}
