package games.Mario;

public class JumpingZombie extends Zombie {

	private int maxY;
	private boolean jumping;

	public JumpingZombie(int x, int y, String direction, boolean movingLeft) {
		super(x, y, direction, movingLeft);
		falling=true;
		jumping=false;
	}

	public int getMaxY() {
		return maxY;
	}
	public boolean getJumping() {
		return jumping;
	}
	
	public void setMaxY() {
		this.maxY=maxY;
	}
	public void setJumping() {
		jumping=true;
		falling=false;
	}
	
	@Override
	public void move() {
		if (movingRight)
			setX(getX() + 10);
		if (movingLeft)
			setX(getX() - 10);
		if (falling)
			setY(getY() + 20);
		if (jumping)
			setY(getY() - 20);
	}

	public void isJumping() {
		if (!falling && !jumping)
			maxY = getY() - 300;

		if (!falling)
			jumping = true;
		else
			jumping=false;
		
		if(getY()<maxY) {
			jumping=false;
			falling=true;
		}
		
	}

}
