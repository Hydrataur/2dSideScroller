package games.Mario;

public class Projectile extends Zombie{

	int startX;
	int startY;
	int velocity;
	int targetX;
	int targetY;
	
	public Projectile(int x, int y, int targetX, int targetY) {
		super(x, y);
		startX=x;
		startY=y;
		this.targetX=targetX;
		this.targetY=targetY;
		velocity=0;
	}
	
	public void delete() {
		setX(-5000);
		setY(-5000);
	}
	
	public void move() {
		if(isUp()) {
			setY(getY()+velocity);
			velocity+=1;
		}
		else {
			setY(getY()-velocity);
			velocity-=1;
		}
		if(isRight()) 
			setX(getX()+20);
		else
			setX(getX()-20);
		//System.out.println(getX()+" "+getY());
	}
	
	public boolean isRight() {
		int a=Math.abs(startX);
		int b=Math.abs(targetX);
		if(a>b)
			return false;
		return true;
	}
	
	public boolean isUp() {
		int c=Math.abs(startX-targetX)/2;
		if(Math.abs(getX())<c)
			return true;
		return false;
	}
	
}
