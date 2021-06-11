import javax.swing.JPanel;
import java.awt.Toolkit
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
 *	TODO
 *	- Add functionality for a thread to animate the algorithm.
 *	- Find a way to translate graph coordinates to component coordinates for numbers in the graph.
 *
 *	^Note^ Here, grid means the graphical representation of the puzzle and graph means the int[][] in the 
 *	'Solver' class.
 */

public class Board extends JPanel {

	private int G_WIDTH;
	private int G_HEIGHT;
	private Solver solver;
	private Image gridImg;
	private int[][] puzzleGraph;

	public Board() {

		initBoard();
	}

	private void initBoard() {

		setFocusable(true);

		loadImage();

		solver = new Solver();
		puzzleGraph = Solver.getInputGraph();
	}

	private void drawPuzzle(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		drawImage(gridImg, 0, 0, Color.WHITE, this);
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

		Toolkit.getDefaultToolkit().sync();
	}
}
