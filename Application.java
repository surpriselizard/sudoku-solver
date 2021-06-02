import java.util.ArrayList;

public class Application {

	private final int SUDOKU_SIZE = 9;
	private int[][] inputGraph;
	private int[][] outputGraph;
	
	public Application() {
		
		inputGraph = initializeInput();

		outputGraph = solveInput();

		if (outputGraph != null) 
			printGraph(outputGraph);
		else 
			System.out.println("Could not find a solution to the sudoku puzzle.");
	}

	public int[][] solveInput() {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		boolean solved = false;
		boolean unSolvable = false;
		boolean actionFound = false;

		int i;

		for (int y = 0; y < SUDOKU_SIZE; ++y) {
			
			for (int x = 0; x < SUDOKU_SIZE; ++x) {
			
				actionFound = false;

				if (inputGraph[y][x] == 0) {
					
					for (i = 1; i < SUDOKU_SIZE + 1; ++i) {
						
						if (isCompatibleX(x, i) && isCompatibleY(y, i) && isCompatibleBlock(x, y, i)) {
							actionFound = true;
							actions.add(new Action(x, y, i));
							break;
						}
					}

					if (!actionFound) {

						if ((actions.size() - 1) < 0) {

							unSolvable = true;
							break;
						}
						
						y = actions.get(actions.size() - 1).getY();
						x = actions.get(actions.size() - 1).getX();
						i = actions.get(actions.size() - 1).getValue() + 1;
					}
				}
			}
			
			if (unSolvable)
				break;
		}

		if (solved)
			return applyActionsTo(inputGraph, actions);
		else 
			return null;
	}

	private int[][] initializeInput() {
		
		int[][] tempGraph = new int[][] {
			{0, 6, 0,  0, 0, 8,  0, 0, 0},
			{4, 0, 0,  0, 0, 3,  0, 6, 0},
			{0, 0, 0,  6, 0, 5,  9, 0, 0},

			{6, 9, 0,  7, 3, 0,  0, 0, 0},
			{0, 4, 0,  0, 0, 0,  6, 3, 0},
			{5, 0, 0,  8, 6, 0,  0, 0, 2},

			{0, 1, 0,  0, 2, 0,  0, 8, 6},
			{3, 0, 0,  0, 0, 6,  2, 5, 0},
			{0, 0, 6,  0, 0, 7,  0, 0, 0}
		};

		return tempGraph;
	}

	private void printGraph(int[][] graph) {

		for (int y = 0; y < SUDOKU_SIZE; ++y) {

			for (int x = 0; x < SUDOKU_SIZE; ++x) {

				System.out.print(graph[y][x] + " ");
			}
			System.out.println();
		}
	}

	private int[][] applyActionsTo(int[][] graph, ArrayList<Action> acts) {

		for (Action act : acts) {
			
			// Checking if Actions are compatible with the given graph
			if (graph[act.getY()][act.getX()] != 0) 
				return null;
			else 
				graph[act.getY()][act.getX()] = act.getValue();
		}

		return graph;
	}

	private boolean isCompatibleX(int xValue, int value) {

		// int yValue;

		for (int yValue = 0; yValue < SUDOKU_SIZE; ++yValue) {

			if (inputGraph[yValue][xValue] == value) {

				// System.out.println("xValue: " + xValue + " yValue: " + yValue + " value: " + value + " return: false");
				return false;
			}
		}

		// System.out.println("xValue: " + xValue + " yValue: " + yValue + " value: " + value + " return: true");
		return true;
	}

	private boolean isCompatibleY(int yValue, int value) {
		
		for (int xValue = 0; xValue < SUDOKU_SIZE; ++xValue) {

			if (inputGraph[yValue][xValue] == value)
				return false;
		}

		return true;
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

		for (int h = yMin; h <= yMax; ++h) {

			for (int w = xMin; w <= xMax; ++w) {

				if (inputGraph[h][w] == valueNum) {

					return false;
				}
			}
		}
		return true;
	}

}
