import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

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
 *	Version: 21/06/2021
 *	- Adjusting coordinate calculation for numbers on the grid
 *
 *	Version: 23/06/2021
 *	- Numbers are sufficiently centered in each cell
 *
 *	Version: 24/06/2021
 *	- Enlarged the numbers for better visual clarity
 *	- Zeros are now blank spaces
 *
 *	TODO
 *	- When clicking on the grid, the user will be able to input numbers and when they are
 *    done inputting numbers, they can press the solveButton and it will solve the grid.
 *
 *	^Note^ Here, grid means the graphical representation of the puzzle and graph means the int[][] in the 'Solver' class.
 */

public class Board extends JPanel {

	private int G_WIDTH;
	private int G_HEIGHT;
	private Solver solver;
	private Image gridImg;
	private int[][] puzzleGraph;

	public Board() {

		initBoard();

		solver = new Solver(this);
		puzzleGraph = solver.getInputGraph();
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

		puzzleGraph = solver.getInputGraph();

		int xNumOffset = 24;
		int yNumOffset = 32;
		int smallBorderWidth = 2;
		int largeBorderWidth = 5;
		int cellWidth = 59;

		// It is up to 9 beacuse that is the width/height of the array in terms of cells
		for (int y = 0; y < 9; ++y) {

			for (int x = 0; x < 9; ++x) {

				int xCoord = (x * cellWidth) + (x * smallBorderWidth) + xNumOffset;
				int yCoord = (y * cellWidth) + (y * smallBorderWidth) + yNumOffset;
				String num = Integer.toString(puzzleGraph[y][x]);

				g2d.setFont(new Font("numFont", Font.PLAIN, 15));

				if (x < 3)
					xCoord += largeBorderWidth;
				else if (x > 2 && x < 6) {

					xCoord += (largeBorderWidth * 2);
					xCoord -= smallBorderWidth;
				}
				else if (x > 5) {

					xCoord += (largeBorderWidth * 3);
					xCoord -= (smallBorderWidth * 2);
					// Irregularity in the graph calls for a small cut in the xCoord
					xCoord -= 1;
				}

				if (y < 3)
					yCoord += largeBorderWidth;
				else if (y > 2 && y < 6) {

					yCoord += (largeBorderWidth * 2);
					yCoord -= smallBorderWidth;
				}
				else if (y > 5) {

					yCoord += (largeBorderWidth * 3);
					yCoord -= (smallBorderWidth * 2);
				}

				if (num.equals("0")) {

					num = " ";
				}

				g2d.drawString(num, xCoord, yCoord);
			}
		}
	}

	private void loadImage() {

		ImageIcon ii = new ImageIcon("srcImages/grid.png");
		gridImg = ii.getImage();

		G_WIDTH = gridImg.getWidth(null);
		G_HEIGHT = gridImg.getHeight(null);
	}

	public void solveGraph() {

		solver.solveInput();
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
