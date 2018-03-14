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
	
	public void setJumping() {
		jumping=true;
		falling=false;
	}
	
	@Override
	public void move() {
		if(knockBackVelocity==0) {
			if (movingRight)
				setX(getX() + 10);
			if (movingLeft)
				setX(getX() - 10);
		}
		else {
			if(movingLeft)
				setX(getX()+knockBackVelocity);
			else
				setX(getX()-knockBackVelocity);
			setKnockBackV();
		}
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
