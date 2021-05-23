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
		
		ArrayList<Action> actions = new ArrayList<Action>(1);
		boolean solved = false;
		boolean unSolvable = false;

		while(!solved && !unSolvable) {
			
			for (int y = 0; y < SUDOKU_SIZE; ++y) {
				
				for (int x = 0; x < SUDOKU_SIZE; ++x) {
				
					if (intputGraph[y][x] == 0) {

						// TODO
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
}
