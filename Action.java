public class Action {

	private int value;
	private int x;
	private int y;

	public Action(int xCoor, int yCoor, int number) {

		value = number;
		x = xCoor;
		y = yCoor;
	}

	public int getValue() { return value; }
	public int getX() { return x; }
	public int getY() { return y; }
	public void setValue(int v) { value = v; }
	public void setX(int xValue) { x = xValue; }
	public void setY(int yValue) { y = yValue; }
}
