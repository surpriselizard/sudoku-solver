import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

/*	Version: 10/06/2021
 *	- Create this file, with some boilerplate code
 *
 *	Version: 11/06/2021
 *	- Added some graphics functionality
 *	- Added initialization of some key objects
 *
 *	Version: 15/06/2021
 *	- Created drawNumbers which should draw all the numbers on the grid
 *	- Started 'solveListener'
 *
 *	Version: 16/06/2021
 *	- Removed the field 'puzzleGraph' since it could easily be replaced as a local variable
 *
 *	TODO
 *	- Add functionality for a thread to animate the algorithm.
 *
 *	^Note^ Here, grid means the graphical representation of the puzzle and graph means the int[][] in the 
 *	'Solver' class.
 */

public class Board extends JPanel {

	private int G_WIDTH;
	private int G_HEIGHT;
	private Solver solver;
	private Image gridImg;

	public Board() {

		initBoard();

		solver = new Solver();
	}

	private void initBoard() {

		setFocusable(true);

		loadImage();
	}

	private void drawPuzzle(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(gridImg, 0, 0, Color.WHITE, this);
	}

	private void drawNumbers(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		int[][] puzzleGraph = solver.getInputGraph();
		int numOffset = (G_WIDTH / 9) / 2;

		// It is up to 9 beacuse that is the width/height of the array in terms of cells
		for (int y = 0; y < 9; ++y) {

			for (int x = 0; x < 9; ++x) {

				g2d.drawString(Integer.toString(puzzleGraph[y][x]), (G_WIDTH / (x + 1)) - numOffset, (G_HEIGHT / (y + 1)) - numOffset);
			}
		}
	}

	private void loadImage() {

		ImageIcon ii = new ImageIcon("srcImages/grid.png");
		gridImg = ii.getImage();

		G_WIDTH = gridImg.getWidth(null);
		G_HEIGHT = gridImg.getHeight(null);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		drawPuzzle(g);
		drawNumbers(g);

		Toolkit.getDefaultToolkit().sync();
	}

	public int getWidth() { return G_WIDTH; }
	public int getHeight() { return G_HEIGHT; }
}
