package games.Mario;

public class Player {

	//Player Location
	private static final int x=300;
	private int y;
	
	//Munny
	private int money;
		
	//Mario direction
	private String direction;
	
	//Stores wall hit and direction of wall
	private boolean touchingRoof=false;
	private boolean touchingFloor=false;
	private boolean touchingLeftWall=false;
	private boolean touchingRightWall=false;
	
	//Combat info
	int damage;
	int maxHP;
	int currentHP;
	
	//Stores current direction
	private boolean movingLeft;
	private boolean movingRight;
	private boolean falling;
	
	//Current jump height
	private int jumpHeight;
	
	//Stores jumping
	private boolean jumping=false;
	
	//Getters and Setters
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public int getMoney() {
		return money;
	}
	public void giveMoney(int money) {
		this.money+=money;
	}
	public boolean takeMoney(int money) {
		if(this.money>=money) {
			this.money-=money;
			return true;
		}
		return false;
	}
	
	public int getJumpHeight() {
		return jumpHeight;
	}
	public void setJumpHeight() {
		jumpHeight=y-300;
	}
	
	public boolean getTouchingRoof() {
		return touchingRoof;
	}
	public boolean getTouchingFloor() {
		return touchingFloor;
	}
	public boolean getTouchingLeftWall() {
		return touchingLeftWall;
	}
	public boolean getTouchingRightWall() {
		return touchingRightWall;
	}
	
	public void setTouchingRoof(boolean touchingRoof) {
		this.touchingRoof=touchingRoof;
	}
	public void setTouchingFloor(boolean touchingFloor) {
		this.touchingFloor=touchingFloor;
	}
	public void setTouchingLeftWall(boolean touchingLeftWall) {
		this.touchingLeftWall=touchingLeftWall;
	}
	public void setTouchingRightWall(boolean touchingRightWall) {
		this.touchingRightWall=touchingRightWall;
	}
	
	public boolean getJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping=jumping;
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
}
