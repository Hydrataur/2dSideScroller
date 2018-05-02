package games.Mario;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MarioBoard extends JPanel implements ActionListener  {

	// Width and height of window
		private final static int BOARDWIDTH = 3200;
		private final static int BOARDHEIGHT = 1700;
	
	// Counts how many frames attack anim is onscreen
		int attackTimer=0;
	
	//Distance from start
		int distance=0;
	
	// Checks if in game
		private boolean inGame = true;
		
	//Checks if paused
		private boolean paused=false;

	// Timer used to record tick times
		private Timer timer;

	// Game speed. Lower speed==>Everything happens faster
		private static int speed = 45;
		
	//Player character
		Player mario=new Player();

	//Player image
		ImageIcon marioIcon=new ImageIcon("marioImagesNew/player/IdleRight.gif");
		
	//Finish flag
		FinishFlag flag=new FinishFlag(-7000, 1000, 200, 500);
		ImageIcon flagIcon=new ImageIcon("marioImagesNew/environment/FinishFlag.png");
		
	//Environment images
		ImageIcon bg=new ImageIcon("marioImagesNew/environment/Background.png");
		ImageIcon floor=new ImageIcon("marioImagesNew/environment/Floor.png");
		ImageIcon spike=new ImageIcon("marioImagesNew/environment/Spike.png");
		
	//Hostiles
		Zombie[] enemies= {new Zombie(1000, 1300, "Left", true), new JumpingZombie(2000, 1300, "Left", true), 
				new Shooter(2250, 1200, "Left", true), new SpikyZombie(3000, 1300, "Left", true)
		};
		
	//Hostile images
		ImageIcon[] enemyIcons= {new ImageIcon("marioImagesNew/enemies/FlatZombieLeft.png"), new ImageIcon("marioImagesNew/enemies/FlatZombieRight.png"),
				new ImageIcon("marioImagesNew/enemies/JumpingZombieLeft.png"), new ImageIcon("marioImagesNew/enemies/Shooter/ShooterWaitLeft.png"),
				new ImageIcon("marioImagesNew/enemies/SpikyZombieLeft.gif")
		};
		ImageIcon projectileImage=new ImageIcon("marioImagesNew/enemies/Shooter/Projectile.gif");
		
		
	//Spikes
		public Spike[] spikes= {
				//new Spike(-700, 1200), new Spike(-800, 1200),
				//new Spike(-900, 1200), new Spike (-2800, 1200)
		};
		
	//Walls
		public Wall[] walls= {
				new Wall(0, 1500, 8000, 300), new Wall(-500, 1300, 500, 200), 
				new Wall(-1500, 1300, 500, 200), new Wall(-2500, 1300, 500, 200),
				new Wall(-3500, 1300, 500, 200)
				//new Wall(-6000, 1500, 500, 200)
		};
	public MarioBoard() {

		addKeyListener(new Keys());
		setBackground(Color.black);
		setFocusable(true);

		setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

		initializeGame();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}	
		
	void draw(Graphics g) {
		if(inGame) {
			
			
			g.drawImage(bg.getImage(), distance, 0, this);
			
			g.setColor(Color.blue);
			((Graphics2D) g).setStroke(new BasicStroke(5));
			for(int i=0; i<walls.length; i++) {
				g.drawRoundRect(distance-walls[i].getX(), walls[i].getY(), walls[i].getLength(), walls[i].getHeight(), 100, 100);
				//g.drawImage(floor.getImage(), distance-walls[i].getX(), walls[i].getY(), walls[i].getLength(), walls[i].getHeight(), this);
			}
			
			for(int i=0; i<spikes.length; i++) {
				g.drawImage(spike.getImage(), distance-spikes[i].getX(), spikes[i].getY(), 100, 100, this);
			}
			
			//ImageIcon marioIcon=new ImageIcon("marioImages/smallMarioLooking"+mario.getDirection()+".gif");
			g.drawImage(marioIcon.getImage(), mario.getX(), mario.getY()-50, 150, 150, this);
			
			for(int i=0; i<enemies.length; i++) {
				g.drawImage(enemyIcons[i].getImage(), enemies[i].getX()+distance, enemies[i].getY(), 100, 100, this);
				if(enemies[i] instanceof Shooter)
					if(((Shooter)enemies[i]).getProjectile()!=null)
						g.drawImage(projectileImage.getImage(), ((Shooter)enemies[i]).getProjectile().getX()+distance, ((Shooter)enemies[i]).getProjectile().getY(), 50, 50, this);
			}
			//g.drawRect(distance-flag.getX(), flag.getY(), flag.getLength(), flag.getHeight());
			g.drawImage(flagIcon.getImage(), distance-flag.getX(), flag.getY(), flag.getLength(), flag.getHeight(), this);
			
			g.setFont(new Font("Verdana", Font.BOLD, 50));
			g.setColor(Color.red);
			
			g.drawString("Munny: "+Integer.toString(mario.getMoney()), 50, 50);
			
			g.drawString("HP: "+Integer.toString(mario.getHP()), 50, 200);
			
			g.setFont(new Font("Verdana", Font.BOLD, 100));
			if(paused)
				g.drawString("Paused", BOARDWIDTH/2-200, BOARDHEIGHT/2-50);
		}
		else
			endGame(g);
	}
	
	void endGame(Graphics g) {
		String message;
		if(flag.getTouched()) {
			//Create message telling player they beat the level
			message = "Level Complete";
		}
		else { 
			// Create a message telling the player the game is over
			message = "Java Mario Press the ENTER key in order to restart the game";
		}
		// Create a new font instance
		Font font = new Font("Verdana", Font.BOLD, 50);
		FontMetrics metrics = getFontMetrics(font);
	
		// Set the color of the text to red, and set the font
		g.setColor(Color.red);
		g.setFont(font);
	
			// Draw the message to the board
			g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2, BOARDHEIGHT / 2);
		
	}

	
	void initializeGame() {
		paused=false;
		mario.setY(1400);
		inGame=true;
		mario.setDirection("Right");
		mario.takeMoney(mario.getMoney());
		mario.refreshHealth();
		
		distance=0;
		flag.setTouched(false);
		
		enemies[0]=new Zombie(2000, 1300, "Left", true);
		enemies[1]=new Zombie(2000, 1300, "Right", false);
		enemies[2]=new JumpingZombie(2000, 1300, "Left", true);
		enemies[3]=new Shooter(2500, 1200, "Left", true);
		
		timer = new Timer(speed, this);
		timer.start();
	}

	void checkTouchFlag() {
		if(mario.getY()==flag.getY()-100
				&& Math.abs(distance-mario.getX())>Math.abs(flag.getX())
				&& Math.abs(distance-mario.getX())<Math.abs(flag.getLength()+flag.getX()))
			flag.setTouched(true);
		if(mario.getY()==flag.getY()-100
				&&Math.abs(distance-mario.getX()-100)>Math.abs(flag.getX()) 
				&& Math.abs(distance-mario.getX())<Math.abs(flag.getX()-flag.getLength()))
			flag.setTouched(true);
		
		if(mario.getY()+100>flag.getY() && mario.getY()<flag.getY()+flag.getLength()) {
			if(Math.abs(distance-mario.getX())+100==Math.abs(flag.getX()))
				flag.setTouched(true);
		}
		if(mario.getY()+100>flag.getY() && mario.getY()<flag.getY()+flag.getLength()) {
			if(Math.abs(distance-mario.getX())==Math.abs(flag.getX())+flag.getLength())
				flag.setTouched(true);
		}
		if(flag.getTouched()) {
			mario.giveMoney(500);
			inGame=false;
		}
	}
	
	void checkPlayerWallCollisions() {
		//Top walls
		mario.setFalling(true);
		for(int i=0; i<walls.length; i++) {
			if(mario.getY()==walls[i].getY()-100 
					&& Math.abs(distance-mario.getX())>Math.abs(walls[i].getX()) 
					&& Math.abs(distance-mario.getX())<Math.abs(walls[i].getLength()+walls[i].getX()))
				mario.setFalling(false);
			if(mario.getY()==walls[i].getY()-100
					&&Math.abs(distance-mario.getX()-100)>Math.abs(walls[i].getX()) 
					&& Math.abs(distance-mario.getX())<Math.abs(walls[i].getX()-walls[i].getLength()))
				mario.setFalling(false);
			
			if(mario.getY()+120>walls[i].getY() && mario.getY()<walls[i].getY()+walls[i].getLength()) {
				if(Math.abs(distance-mario.getX())+100==Math.abs(walls[i].getX()))
					mario.setMovingRight(false);
			}
			if(mario.getY()+120>walls[i].getY() && mario.getY()<walls[i].getY()+walls[i].getLength()) {
				if(Math.abs(distance-mario.getX())==Math.abs(walls[i].getX())+walls[i].getLength())
					mario.setMovingLeft(false);
			}
		}
		
	}

	void checkSpikeCollision() {
		for(int i=0; i<spikes.length; i++) {
			if(proximity(mario.getX()-distance, Math.abs(spikes[i].getX()), 50)||proximity(mario.getX()-distance+100 , Math.abs(spikes[i].getX()-100), 50)) {
				if(proximity(mario.getY(),spikes[i].getY(), 50))
					inGame=false;
			}
		}
	}
	
	void checkEnemyWallCollisions(Zombie c) {
		boolean jumper=false;
		if(c instanceof JumpingZombie)
			jumper=true;	
		if(c instanceof Shooter)
			if(((Shooter)c).getProjectile()!=null)
				checkEnemyWallCollisions(((Shooter)c).getProjectile());
		
		if(!jumper)
			c.setFalling(true);
		for(int i=0; i<walls.length; i++) {
			if(c.getY()==walls[i].getY()-100
					&& Math.abs(c.getX())>Math.abs(walls[i].getX())
					&& Math.abs(c.getX())<Math.abs(walls[i].getLength()-walls[i].getX())) 
				c.setFalling(false);
			if(c.getY()+100>walls[i].getY()&&c.getY()<walls[i].getY()+walls[i].getLength()) {
				if(Math.abs(c.getX())+100==Math.abs(walls[i].getX())) {
					c.setMovingRight(false);
					c.setMovingLeft(true);
					c.setDirection("Left");
					if(c instanceof Projectile) {
						System.out.println(c.getX()+" "+c.getY());
						//((Projectile)c).delete();
					}
				}
			}
			if(c.getY()+100>walls[i].getY()&&c.getY()<walls[i].getY()+walls[i].getLength()) {
				if(Math.abs(c.getX())==Math.abs(walls[i].getLength()-walls[i].getX())) {
					c.setMovingLeft(false);
					c.setMovingRight(true);
					c.setDirection("Right");
				}
			}
			
		}
	}
	
	private boolean proximity(int a, int b, int closeness) {
		return Math.abs((long) a - b) <= closeness;
	}
	
	void checkEnemyCollisions() {
		for (int i = 0; i < enemies.length; i++) {
			if(proximity(Math.abs(distance-mario.getX()), enemies[i].getX(), 100)&& (proximity(mario.getY()+80, enemies[i].getY(), 20))&&mario.getFalling()==true&&mario.getJumping()==false) {
				if(enemies[i] instanceof SpikyZombie)
					mario.takeDamage(enemies[i].getDamage());
				else
					enemies[i].kill();
				mario.giveMoney(50);
			}
			if ((proximity(Math.abs(distance-mario.getX()), enemies[i].getX(), 100)) && (proximity(mario.getY(), enemies[i].getY(), 100))) {
				enemies[i].takeDamage(0);
				mario.takeDamage(enemies[i].getDamage());
			}
			
			if(enemies[i] instanceof Shooter){
				if(((Shooter)enemies[i]).getProjectile()!=null) {
					if(proximity(Math.abs(distance-mario.getX()), ((Shooter)enemies[i]).getProjectile().getX(), 100) && proximity(Math.abs(mario.getY()), ((Shooter)enemies[i]).getProjectile().getY(), 100)) {
						mario.takeDamage(enemies[i].getDamage());
						((Shooter)enemies[i]).deleteProjectile();
					}
				}
			}
			
			if(mario.getHP()<=0)
				inGame=false;
			
		}
		if (!inGame)
			timer.stop();
	}
	
	void attackFront() {
		
		System.out.println(enemies[0].getX()+" "+distance);
		System.out.println(enemies[0].getX()+distance-mario.getX()+100);
		attackTimer=5;
		for(int i=0; i<enemies.length;i++) {
			int dist=101;
			
			if(mario.getDirection().equals("Right"))
				dist=enemies[i].getX()+distance-mario.getX()+100;
			if(mario.getDirection().equals("Left"))
				dist=distance-mario.getX()+enemies[i].getX();
			if(proximity(mario.getY(), enemies[i].getY(), 100)&&dist<=300) {
				enemies[i].takeDamage(mario.getDamage());
				if(enemies[i].getHP()<=0)
					mario.giveMoney(50);
			}
			
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(inGame) {
			checkTouchFlag();
			checkPlayerWallCollisions();
			checkSpikeCollision();
			for(int i=0; i<enemies.length; i++) {
				checkEnemyCollisions();
				checkEnemyWallCollisions(enemies[i]);
				enemyIcons[i]=new ImageIcon("marioImagesNew/enemies/FlatZombie"+enemies[i].getDirection()+".png");
				if(enemies[i] instanceof JumpingZombie) {
					((JumpingZombie) enemies[i]).isJumping();
					enemyIcons[i]=new ImageIcon("marioImagesNew/enemies/JumpingZombie"+enemies[i].getDirection()+".png");
				}
					enemies[i].move();
				if(enemies[i] instanceof Shooter) {
					enemyIcons[i]=new ImageIcon("marioImagesNew/enemies/Shooter/ShooterWait"+enemies[i].getDirection()+".png");
					if(proximity(Math.abs(distance-mario.getX()), enemies[i].getX(), 1000)) {
						((Shooter)enemies[i]).shoot(Math.abs(distance)-mario.getX(), mario.getY());
						enemyIcons[i]=new ImageIcon("marioImagesNew/enemies/Shooter/ShooterShoot"+enemies[i].getDirection()+".png");
					}
				}
				if(enemies[i] instanceof SpikyZombie) {
					enemyIcons[i]=new ImageIcon("marioImagesNew/enemies/SpikyZombie"+enemies[i].getDirection()+".gif");
				}
			}
			if(mario.getMovingLeft()&&distance<0) {
				distance+=50;
				marioIcon=new ImageIcon("marioImagesNew/player/RunLeft.gif");
			}
			if(mario.getMovingRight()) {
				distance-=50;
				marioIcon=new ImageIcon("marioImagesNew/player/RunRight.gif");
			}
		}
		if(!mario.getMovingLeft()&&!mario.getMovingRight())
			marioIcon=new ImageIcon("marioImagesNew/player/Idle"+mario.getDirection()+".gif");
		
		if(mario.getJumping()) {
			if(mario.getY()>mario.getJumpHeight()) {
					mario.setY(mario.getY()-40);
					marioIcon=new ImageIcon("marioImagesNew/player/Jumping"+mario.getDirection()+".gif");
			}
			else
				mario.setJumping(false);
		}
		
		if(!mario.getJumping()&&mario.getFalling()) {
			mario.setY(mario.getY()+40);
			marioIcon=new ImageIcon("marioImagesNew/player/Falling"+mario.getDirection()+".gif");
	}
		
		if(mario.getY()>BOARDHEIGHT) {
			inGame=false;
			timer.stop();
		}
		
		if(attackTimer>0) {
			attackTimer--;
			marioIcon=new ImageIcon("marioImagesNew/player/Attack"+mario.getDirection()+".gif");
		}
		repaint();
	}
	
	private class Keys extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			int key=e.getKeyCode();
			
			if(key==KeyEvent.VK_SPACE) {
				attackFront();
			}
			
			if(key==KeyEvent.VK_W||key==KeyEvent.VK_UP) {
				if(!mario.getJumping()&&!mario.getFalling()) {
					mario.setJumping(true);
					mario.setJumpHeight();
				}
			}
			if(key==KeyEvent.VK_D||key==KeyEvent.VK_RIGHT) {
				mario.setDirection("Right");
				mario.setMovingRight(true);
			}
			if(key==KeyEvent.VK_A||key==KeyEvent.VK_LEFT ) {
				mario.setMovingLeft(true);
				mario.setDirection("Left");
			}
			if(key==KeyEvent.VK_P) { 
				if(!paused) {
					timer.stop();
					paused=true;
				}
				else {
					timer.start();
					paused=false;
				}
			}
		}		
		
		@Override
		public void keyReleased(KeyEvent e) {
			int key=e.getKeyCode();
			if(key==KeyEvent.VK_D||key==KeyEvent.VK_RIGHT) {
				mario.setMovingRight(false);
			}
			if(key==KeyEvent.VK_A||key==KeyEvent.VK_LEFT ) {
				mario.setMovingLeft(false);
			}
			if(key==KeyEvent.VK_R) {
				timer.stop();
				initializeGame();
			}
			if(!inGame && key==KeyEvent.VK_ENTER) {
				initializeGame();
			}
		}
	}
}
