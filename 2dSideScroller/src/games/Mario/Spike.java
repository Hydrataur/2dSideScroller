package games.Mario;

public class Spike extends Wall{

	int damage;
	
	public Spike(int x, int y) {
		super(x, y, 100, 100);
		damage=50;
	}
	
}
