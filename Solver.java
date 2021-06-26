import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/*
 *	Version: 11/06/2021
 *	- Added an accessor function for the 'inputGraph'
 *	
 *	Version: 15/06/2021
 *	- Overloaded 'initializeInput' with a function that is more easily used by other classes
 *
 *	Version: 24/06/2021
 *	- Making some changes so that each step in algorithim can be displayed on the graph
 *
 *	Version: 25/06/2021
 *	- Same thing as yesterday
 *
 *	TODO:
 *	- Make the algorithim show on the grid while it is being done.
 */

public class Solver implements ActionListener {

	private final int SUDOKU_SIZE = 9;
	private int[][] inputGraph;
	private int i = -1;
	private Board board;
	private Timer timer;
	
	public Solver(Board source) {
		
		inputGraph = initializeInput();
		board = source;
		timer = new Timer(500, this);
	}

	public void solveInput() {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		boolean solved = false;
		boolean unSolvable = false;
		boolean actionFound = false;
		timer.start();

		int x, y;
		int currentCell = 0;
		i = 1;

		for (y = 0; y < SUDOKU_SIZE;) {
			
			for (x = 0; x < SUDOKU_SIZE;) {
			
				actionFound = false;
				refreshGraph(inputGraph, actions);
				currentCell = inputGraph[y][x];
				board.repaint();


				if (currentCell == 0) {
					
					for (; i < SUDOKU_SIZE + 1; ++i) {

						actions.add(new Action(x, y, i));
						refreshGraph(inputGraph, actions);
						board.repaint();

						try {
							Thread.sleep(1);
						} 
						catch (InterruptedException ex) {
							System.out.println(ex.getMessage());
						}
						finally {
							System.out.println("The 'try-catch' is finished");
						}

						if (isCompatibleX(x, i) && isCompatibleY(y, i) && isCompatibleBlock(x, y, i)) {
							actionFound = true;
							i = 1;
							

							break;
						}
						else {

							actions.remove(actions.size() - 1);
							refreshGraph(inputGraph, actions);
							board.repaint();
						}
					}

					if (!actionFound) {
						
						int lastIndex = actions.size() - 1;

						if (lastIndex < 0) {

							unSolvable = true;
							break;
						}
						
						if (x == actions.get(lastIndex).getX() && y == actions.get(lastIndex).getY()) {

							actions.remove(lastIndex);
							lastIndex = actions.size() - 1;

							// Checking if we are out of actions since we just deleted one
							if (lastIndex < 0) {

								unSolvable = true;
								break;
							}
						}

						x = actions.get(lastIndex).getX();
						y = actions.get(lastIndex).getY();
						i = actions.get(lastIndex).getValue() + 1;

						actions.remove(lastIndex);
					}
				}

				else if (currentCell != 0)
					++x;

				if (actionFound)
					++x;
			}

			if (currentCell != 0)
				++y;

			if (actionFound)
				++y;
			
			if (unSolvable)
				break;
		}

		refreshGraph(inputGraph, actions);

		if (unSolvable)
			System.out.println("Graph is unsolvable.");

		printGraph(inputGraph);
	}

	private int[][] initializeInput() {
		
		int[][] tempGraph = new int[][] {
			{0, 0, 0,  9, 0, 2,  0, 0, 0},
			{0, 2, 8,  0, 4, 0,  1, 9, 0},
			{4, 0, 0,  0, 0, 0,  0, 0, 7},

			{0, 0, 3,  1, 0, 4,  6, 0, 0},
			{0, 0, 0,  6, 0, 9,  0, 0, 0},
			{0, 0, 1,  5, 0, 7,  4, 0, 0},

			{1, 0, 0,  0, 0, 0,  0, 0, 8},
			{0, 5, 9,  0, 7, 0,  3, 6, 0},
			{0, 0, 0,  2, 0, 5,  0, 0, 0}
		};

		return tempGraph;
	}

	public void initializeInput(int[][] graph) {

		for (int y = 0; y < SUDOKU_SIZE; ++y) {
			
			for (int x = 0; x < SUDOKU_SIZE; ++x) {

				inputGraph[y][x] = graph[y][x];
			}
		}
	}

	private void printGraph(int[][] graph) {

		for (int y = 0; y < SUDOKU_SIZE; ++y) {

			for (int x = 0; x < SUDOKU_SIZE; ++x) {

				System.out.print(graph[y][x] + " ");
			}
			System.out.println();
		}
	}

	public void refreshGraph(int[][] graph, ArrayList<Action> acts) {

		int[][] temporaryGraph = initializeInput();

		for (int yValue = 0; yValue < SUDOKU_SIZE; ++yValue) {

			for (int xValue = 0; xValue < SUDOKU_SIZE; ++xValue) {

				graph[yValue][xValue] = temporaryGraph[yValue][xValue];
			}
		}

		for (Action act : acts)
			graph[act.getY()][act.getX()] = act.getValue();
	}

	private boolean isCompatibleX(int xValue, int value) {

		int counter = 0;

		for (int yValue = 0; yValue < SUDOKU_SIZE; ++yValue) {

			if (inputGraph[yValue][xValue] == value)
				counter++;
		}

		if (counter == 1)
			return true;
		else if (counter > 1)
			return false;
		else
			return false;
	}

	private boolean isCompatibleY(int yValue, int value) {

		int counter = 0;
		
		for (int xValue = 0; xValue < SUDOKU_SIZE; ++xValue) {

			if (inputGraph[yValue][xValue] == value)
				counter++;
		}

		if (counter == 1)
			return true;
		else if (counter > 1)
			return false;
		else 
			return false;
	}

	private boolean isCompatibleBlock(int xValue, int yValue, int value) {

		boolean isInFirstColumn = (xValue >= 0 && xValue < 3);
		boolean isInSecondColumn = (xValue > 2 && xValue < 6);
		boolean isInThirdColumn = (xValue > 5 && xValue < 9);

		boolean isInFirstRow = (yValue >= 0 && yValue < 3);
		boolean isInSecondRow = (yValue > 2 && yValue < 6);
		boolean isInThirdRow = (yValue > 5 && yValue < 9);

		if (isInFirstColumn && isInFirstRow) {

			return childBoxCheck(0, 2, 0, 2, value);
		}

		if (isInFirstColumn && isInSecondRow) {

			return childBoxCheck(0, 2, 3, 5, value);
		}

		if (isInFirstColumn && isInThirdRow) {

			return childBoxCheck(0, 2, 6, 8, value);
		}

		if (isInSecondColumn && isInFirstRow) {

			return childBoxCheck(3, 5, 0, 2, value);
		}

		if (isInSecondColumn && isInSecondRow) {

			return childBoxCheck(3, 5, 3, 5, value);
		}

		if (isInSecondColumn && isInThirdRow) {

			return childBoxCheck(3, 5, 6, 8, value);
		}

		if (isInThirdColumn && isInFirstRow) {

			return childBoxCheck(6, 8, 0, 2, value);
		}

		if (isInThirdColumn && isInSecondRow) {

			return childBoxCheck(6, 8, 3, 5, value);
		}

		if (isInThirdColumn && isInThirdRow) {

			return childBoxCheck(6, 8, 6, 8, value);
		}

		else 
			return false;
	}

	private boolean childBoxCheck(int xMin, int xMax, int yMin, int yMax, int valueNum) {

		int counter = 0;

		for (int h = yMin; h <= yMax; ++h) {

			for (int w = xMin; w <= xMax; ++w) {

				if (inputGraph[h][w] == valueNum)
					counter++;
			}
		}

		if (counter == 1)
			return true;
		else if (counter > 1)
			return false;
		else 
			return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		board.repaint();
	}

	public int[][] getInputGraph() { return inputGraph; }
	public int getIterator() { return i; }

	// DEBUG FUNCTION
	private void makeGraphZeros(int[][] graph) {

		for (int yV = 0; yV < SUDOKU_SIZE; ++yV) {

			for (int xV = 0; xV < SUDOKU_SIZE; ++xV) {

				graph[yV][xV] = 0;
			}
		}
	}
}
