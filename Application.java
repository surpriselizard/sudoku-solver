import java.util.ArrayList;

public class Application {

	private final int SUDOKU_SIZE = 9;
	private int[][] inputGraph;
	private int[][] outputGraph;
	
	public Application() {
		
		inputGraph = initializeInput();

		outputGraph = solveInput();
	}

	public int[][] solveInput() {
		
		ArrayList<Action> actions = new ArrayList<Action>();
		boolean solved = false;
		boolean unSolvable = false;

		while(!solved || !unSolvable) {
			
			for (int y = 0; y < SUDOKU_SIZE; ++y) {
				
				for (int x = 0; x < SUDOKU_SIZE; ++x) {
				
					if (intputGraph[y][x] == 0) {

						for (int i = 0; i < SUDOKU_SIZE; ++i) {
							
							if (isCompatibleX(x, i) && isCompatibleY(y, i) && isCompatibleBlock(x, y, i)) {

								actions.add(new Action(x, y, value));
								break;
							}

							else {

								
							}
						}
					}
				}
			}
		}
	}

	private int[][] initializeInput() {
		
		int[][] tempGraph = new int[9][9] {
			{0, 0, 3, 0, 0, 0, 1, 0, 8},
			{8, 0, 0, 0, 9, 3, 6, 0, 0},
			{9, 0, 2, 1, 0, 0, 3, 0, 4},
			{2, 0, 0, 0, 1, 0, 0, 7, 0},
			{0, 0, 4, 0, 2, 0, 9, 0, 0},
			{0, 9, 0, 0, 6, 0, 0, 0, 5},
			{4, 0, 9, 0, 0, 1, 5, 0, 2},
			{0, 0, 8, 2, 5, 0, 0, 0, 9},
			{7, 0, 5, 0, 0, 0, 4, 0, 0}
		}

		return tempGraph;
	}

	public boolean isCompatibleX(int xValue, int value) {

		for (int yValue = 0; yValue < SUDOKU_SIZE; ++yValue) {

			if (inputGraph[yValue][xValue] == value)
				return false;
		}

		return true;
	}

	public boolean isCompatibleY(int yValue, int value) {
		
		for (int xValue = 0; xValue < SUDOKU_SIZE; ++xValue) {

			if (inputGraph[yValue][xValue] == value)
				return false;
		}

		return true;
	}

	public boolean isCompatibleBlock(int xValue, int yValue, int value) {

		boolean isInFirstColumn = (xValue > 0 && xValue < 3);
		boolean isInSecondColumn = (xValue > 2 && xValue < 6);
		boolean isInThirdColumn = (xValue > 5 && xValue < 9);

		boolean isInFirstRow = (yValue > 0 && yValue < 3);
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

		if (isInSecondColumn && isInThridRow) {

			return childBoxCheck(3, 5, 6, 8, value);
		}

		if (isInThridColumn && isInFirstRow) {

			return childBoxCheck(6, 8, 0, 2, value);
		}

		if (isInThirdColumn && isInSecondRow) {

			return childBoxCheck(6, 8, 3, 5, value);
		}

		if (isInThridColumn && isInThirdRow) {

			return childBoxCheck(6, 8, 6, 8, value);
		}
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
