package games.Mario;

public class Wall {

	//Walls 4 corners
	private int x, length;
	private int y, height;
	
	
	public int getX() {
		return x;
	}
	public int getLength() {
		return length;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	
	public Wall(int x, int y, int length, int height) {
		this.x=x;
		this.length=length;
		this.y=y;
		this.height=height;
	}
	
}
