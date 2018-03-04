package games.Mario;

public class FinishFlag extends Wall{

	private boolean touched;
	
	public FinishFlag(int x, int y, int length, int height) {
		super(x, y, length, height);
	}
	
	public boolean getTouched() {
		return touched;
	}
	public void setTouched(boolean touched) {
		this.touched=touched;
	}
	
}
