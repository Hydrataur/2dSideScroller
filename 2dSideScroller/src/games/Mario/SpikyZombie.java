package games.Mario;

public class SpikyZombie extends Zombie{

	public SpikyZombie(int x, int y, String direction, boolean movingLeft) {
		super(x, y, direction, movingLeft);
		this.damage=10;
	}
	
	@Override
	public void move() {
		if(knockBackVelocity==0) {
			if(movingRight)
				setX(getX()+25);
			if(movingLeft)
				setX(getX()-25);
		}
		else {
			if(movingLeft)
				setX(getX()+knockBackVelocity);
			else
				setX(getX()-knockBackVelocity);
			setKnockBackV();
		}
		if(falling)
			setY(getY()+50);
	}

}
