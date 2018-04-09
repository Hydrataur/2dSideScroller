package games.Mario;

public class Shooter extends Zombie{
	
	public Shooter(int x, int y, String direction, boolean movingLeft) {
		super(x, y, direction, movingLeft);
		shotTimer=0;
	}
	
	boolean inRange;
	int shotTimer;
	Projectile p;
	
	public void shoot(int targetX, int targetY) {
		System.out.println("Shot fired");
		if(shotTimer==0) {
			p=new Projectile(getX(), getY(), targetX, targetY);
			shotTimer=100;
		}
		else {
			p.move();
			shotTimer--;
		}
	}
	
	public Projectile getProjectile() {
		return p;
	}
	
	@Override
	public void move() {
		
	}
	
}
