package character;

public class Node {
	
	public int x;
	public int y;
	public Direction d;
	
	public Node(int x, int y, Direction d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
