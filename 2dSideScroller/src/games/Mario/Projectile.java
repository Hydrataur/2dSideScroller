package games.Mario;

public class Projectile {

	int x;
	int y;
	int startX;
	int startY;
	int velocity;
	int targetX;
	int targetY;
	
	public Projectile(int x, int y, int targetX, int targetY) {
		this.x=x;
		this.y=y;
		startX=x;
		startY=y;
		this.targetX=targetX;
		this.targetY=targetY;
		velocity=0;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x=x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	public void move() {
		//if(isUp()) {
			//y+=velocity;
			//velocity-=1;
		//}
	//	else {
		//	y-=velocity;
	//		velocity+=1;
//		}
		if(isRight()) 
			setX(getX()+20);
		else
			setX(getX()-20);
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
		if(Math.abs(x)<c)
			return true;
		return false;
	}
	
}
