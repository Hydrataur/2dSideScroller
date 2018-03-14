package games.Mario;

public class Zombie {

	//Coords
	private int x;
	private int y;
	
	//Character direction
	private String direction;
	
	//Stores current direction
	protected boolean movingLeft;
	protected boolean movingRight;
	protected boolean falling;

	//Checks wall Collision
	private boolean touchingLeftWall=false;
	private boolean touchingRightWall=false;
	
	//Combat info
	int damage;
	int hp;
	int knockBackVelocity;
	
	//Constructor
	public Zombie(int x, int y, String direction, boolean movingLeft) {
		this.x=x;
		this.y=y;
		this.direction=direction;
		damage=10;
		hp=50;
		if(movingLeft) {
			this.movingLeft=true;
			this.movingRight=false;
		}
		else {
			this.movingLeft=false;
			this.movingRight=true;
		}
	}
	
	//Getters and Setters
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x=x;
	}
	public void setY(int y) {
		this.y=y;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction=direction;
	}
	
	public boolean getMovingRight() {
		return movingRight;
	}
	public void setMovingRight(Boolean movingRight) {
		this.movingRight=movingRight;
	}
	public boolean getMovingLeft() {
		return movingLeft;
	}
	public void setMovingLeft(Boolean movingLeft) {
		this.movingLeft=movingLeft;
	}
	public boolean getFalling() {
		return falling;
	}
	public void setFalling(Boolean falling) {
		this.falling=falling;
	}
	
	public boolean getTouchingRightWall() {
		return touchingRightWall;
	}
	public void setTouchingRightWall(Boolean TouchingRightWall) {
		this.touchingRightWall=TouchingRightWall;
	}
	public boolean getTouchingLeftWall() {
		return touchingLeftWall;
	}
	public void setTouchingLeftWall(Boolean TouchingLeftWall) {
		this.touchingLeftWall=TouchingLeftWall;
	}
	
	public int getDamage() {
		return damage;
	}
	public int getHP() {
		return hp;
	}
	
	public int getKnockBackV() {
		return knockBackVelocity;
	}
	public void setKnockBackV() {
		if(knockBackVelocity>0)
			knockBackVelocity-=10;
	}
	
	public void takeDamage(int damage) {
		hp-=damage;
		if(hp<=0)
			kill();
		else {
			//if(movingLeft)
				//x+=200;
			//else
			//x-=200;
			knockBackVelocity=50;
		}
	}
	
	//Kill
	public void kill() {
		this.setX(10000);
		this.setY(3000);
	}
	
	//Move
	public void move() {
		if(knockBackVelocity==0) {
			if(movingRight)
				setX(getX()+50);
			if(movingLeft)
				setX(getX()-50);
		}
		else {
			if(movingLeft)
				x+=knockBackVelocity;
			else
				x-=knockBackVelocity;
			setKnockBackV();
		}
		if(falling)
			setY(getY()+50);
	}
	
}
