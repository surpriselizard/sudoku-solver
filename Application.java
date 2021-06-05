import java.util.ArrayList;

public class Application {

	private final int SUDOKU_SIZE = 9;
	private int[][] inputGraph;
	
	public Application() {
		
		inputGraph = initializeInput();

		solveInput();

		printGraph(inputGraph);
	}

	public void solveInput() {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		boolean solved = false;
		boolean unSolvable = false;
		boolean actionFound = false;

		int x, y, i;

		for (y = 0; y < SUDOKU_SIZE; ++y) {
			
			for (x = 0; x < SUDOKU_SIZE; ++x) {
			
				actionFound = false;

				if (inputGraph[y][x] == 0) {
					
					for (i = 1; i < SUDOKU_SIZE + 1; ++i) {
						
						applyActionsTo(inputGraph, actions);

						if (isCompatibleX(x, i) && isCompatibleY(y, i) && isCompatibleBlock(x, y, i)) {
							actionFound = true;
							actions.add(new Action(x, y, i));
							break;
						}
					}

					if (!actionFound) {
						
						int lastIndex = actions.size() - 1;

						if (lastIndex < 0) {

							unSolvable = true;
							break;
						}
						
						// Removing action since it does not lead to the solution
						if (x == actions.get(lastIndex).getX() && y == actions.get(lastIndex).getY()) {

							actions.remove(lastIndex);

							// Checking if we are out of actions since we just deleted one
							if (lastIndex < 0) {

								unSolvable = true;
								break;
							}
						}

						x = actions.get(lastIndex).getX();
						y = actions.get(lastIndex).getY();
						i = actions.get(lastIndex).getValue() + 1;
					}
				}
			}
			
			if (unSolvable)
				break;
		}

		applyActionsTo(inputGraph, actions);

		if (unSolvable)
			System.out.println("Graph is unsolvable.");
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
	
	private void applyActionsTo(int[][] graph, ArrayList<Action> acts) {

		for (Action act : acts)
			graph[act.getY()][act.getX()] = act.getValue();
	}

	private void applyActionTo(int[][] graph, Action act) {
			
		if (graph[act.getY()][act.getX()] != 0) {

			System.out.println("Problem with 'applyActionTo'");
			return;
		}
		else
			graph[act.getY()][act.getX()] = act.getValue();
	}

	private boolean isCompatibleX(int xValue, int value) {

		for (int yValue = 0; yValue < SUDOKU_SIZE; ++yValue) {

			if (inputGraph[yValue][xValue] == value) {

				return false;
			}
		}

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

	// DEBUG FUNCTION
	private void makeGraphZeros(int[][] graph) {

		for (int yV = 0; yV < SUDOKU_SIZE; ++yV) {

			for (int xV = 0; xV < SUDOKU_SIZE; ++xV) {

				graph[yV][xV] = 0;
			}
		}
	}

}
