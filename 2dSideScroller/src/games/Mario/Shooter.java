package games.Mario;

public class Shooter extends Zombie{
	
	public Shooter(int x, int y, String direction, boolean movingLeft) {
		super(x, y, direction, movingLeft);
		shotTimer=0;
		damage=30;
	}
	
	boolean inRange;
	int shotTimer;
	Projectile p;
	
	public void shoot(int targetX, int targetY) {
		//System.out.println("Shot fired");
		if(shotTimer==0) {
			p=new Projectile(getX(), getY(), targetX, targetY);
			shotTimer=100;
		}
		else {
			p.move();
			shotTimer =-3;
		}
	}
	
	public void deleteProjectile() {
		p.delete();;
	}
	
	public Projectile getProjectile() {
		return p;
	}
	
	@Override
	public void kill() {
		setX(10000);
		setY(3000);
		deleteProjectile();
	}
	
	@Override
	public void move() {
		
	}
	
}
