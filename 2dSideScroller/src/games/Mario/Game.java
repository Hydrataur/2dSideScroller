package games.Mario;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame{

	public static void main(String[] args) {
		//Delay creation to allow for processing of GUI first
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame=new Game();
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				frame.add(new MarioBoard());
				
				frame.setResizable(false);
				frame.pack();
				
				frame.setVisible(true);
			}
		});
	}
	
}
