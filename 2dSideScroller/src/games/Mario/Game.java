package games.Mario;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Game extends JFrame {

	public static void main(String[] args) {
		// Delay creation to allow for processing of GUI first
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new Game();

				frame.setContentPane(new JLabel(new ImageIcon("marioImagesNew/MainMenuBG.png")));

				frame.setLayout(null);

				JButton play = new JButton("PLAY");
				JButton instructions = new JButton("INSTRUCTIONS");
				JButton quit = new JButton("QUIT");
				JButton back = new JButton("BACK");

				
				play.setBounds(1350, 500, 500, 200);
				instructions.setBounds(1350, 750, 500, 200);
				quit.setBounds(1350, 1000, 500, 200);
				back.setBounds(200, 1400, 500, 200);

				play.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						play.setVisible(false);
						instructions.setVisible(false);
						quit.setVisible(false);

						MarioBoard mb = new MarioBoard();
						mb.setBounds(0, 0, 3200, 1700);
						frame.add(mb);
						mb.setVisible(true);
					}
				});

				ImageIcon jumpIcon = new ImageIcon("marioImagesNew/player/JumpingRight.gif");
				ImageIcon runIcon = new ImageIcon("marioImagesNew/player/RunRight.gif");
				ImageIcon attackIcon = new ImageIcon("marioImagesNew/player/AttackRight.gif");
				Image JIcon = jumpIcon.getImage();
				Image RIcon = runIcon.getImage();
				Image AIcon = attackIcon.getImage();

				Image JIcon2 = JIcon.getScaledInstance(150, 150, Image.SCALE_FAST);
				Image RIcon2 = RIcon.getScaledInstance(150, 150, Image.SCALE_FAST);
				Image AIcon2 = AIcon.getScaledInstance(150, 150, Image.SCALE_FAST);

				ImageIcon jumpIcon2 = new ImageIcon(JIcon2);
				ImageIcon runIcon2 = new ImageIcon(RIcon2);
				ImageIcon attackIcon2 = new ImageIcon(AIcon2);

				JLabel jumpGif = new JLabel(jumpIcon2);
				JLabel runGif = new JLabel(runIcon2);
				JLabel attackGif = new JLabel(attackIcon2);
				JTextArea jumpInstruc = new JTextArea("W or UP to jump");
				JTextArea runInstruc = new JTextArea("LEFT/RIGHT or A/D to move",
						500, 500);
				JTextArea attackInstruc = new JTextArea("SPACE to attack");

				jumpInstruc.setFont(new Font("Impact", Font.BOLD, 30));
				runInstruc.setFont(new Font("Impact", Font.BOLD, 30));
				attackInstruc.setFont(new Font("Impact", Font.BOLD, 30));

				jumpInstruc.setOpaque(false);
				jumpInstruc.setEditable(false);
				runInstruc.setOpaque(false);
				runInstruc.setEditable(false);
				attackInstruc.setOpaque(false);
				attackInstruc.setEditable(false);
				
				

				jumpGif.setBounds(1300, 500, 200, 200);
				runGif.setBounds(1300, 700, 200, 200);
				attackGif.setBounds(1300, 900, 200, 200);
				jumpInstruc.setBounds(1500, 550, 610, 100);
				runInstruc.setBounds(1500, 750, 900, 100);
				attackInstruc.setBounds(1500, 950, 430, 100);

				instructions.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						play.setVisible(false);
						instructions.setVisible(false);
						quit.setVisible(false);

						frame.add(jumpGif);
						frame.add(runGif);
						frame.add(attackGif);
						frame.add(jumpInstruc);
						frame.add(runInstruc);
						frame.add(attackInstruc);

						jumpGif.setVisible(true);
						runGif.setVisible(true);
						attackGif.setVisible(true);
						jumpInstruc.setVisible(true);
						runInstruc.setVisible(true);
						attackInstruc.setVisible(true);

						back.setVisible(true);
					}
				});

				quit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
					}
				});

				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jumpGif.setVisible(false);
						runGif.setVisible(false);
						attackGif.setVisible(false);
						jumpInstruc.setVisible(false);
						runInstruc.setVisible(false);
						attackInstruc.setVisible(false);

						back.setVisible(false);
						play.setVisible(true);
						instructions.setVisible(true);
						quit.setVisible(true);
					}
				});

				play.setFont(new Font("Impact", Font.BOLD, 50));
				instructions.setFont(new Font("Impact", Font.BOLD, 50));
				quit.setFont(new Font("Impact", Font.BOLD, 50));
				back.setFont(new Font("Impact", Font.BOLD, 50));

				frame.add(play);
				frame.add(instructions);
				frame.add(quit);
				frame.add(back);

				play.setVisible(true);
				instructions.setVisible(true);
				quit.setVisible(true);
				back.setVisible(false);

				frame.setBounds(0, 0, 3200, 1700);

				frame.setResizable(false);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setVisible(true);
			}
		});
	}

}
