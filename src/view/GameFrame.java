package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.PacmanBoard;
import character.Direction;
import character.Sprite;

public class GameFrame extends JPanel implements KeyListener{
	private ImageIcon startButtonEnteredImage = new ImageIcon(getClass().getResource("../resourse/img/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(getClass().getResource("../resourse/img/startButtonBasic.png"));
	
	private JLabel scoreLabel;
	private JLabel lifeLabel; 
	private JButton startButton = new JButton(startButtonBasicImage);
	
	private PacmanBoard board = new PacmanBoard();
	private int labelWidth;
	private int labelHeight;
	private boolean running = true;
	
	public GameFrame() {
		JFrame frame = new JFrame();
		labelWidth = 22 * board.getBoard().length;
		labelHeight = 22 * board.getBoard()[1].length;
		this.setLayout(null);
		MusicThread introMusic = new MusicThread("PacManOriginalTheme.mp3", true);
		introMusic.start();
		
		scoreLabel = new JLabel();
		scoreLabel.setText("Score : " + board.getScore());
		scoreLabel.setFont(new Font("±Ã¼­Ã¼", 15, 20));
		scoreLabel.setBounds(100, 250, 200, 200);
		this.add(scoreLabel);
		
		lifeLabel = new JLabel();
		lifeLabel.setText("Life : " + board.getLife());
		lifeLabel.setFont(new Font("±Ã¼­Ã¼", 15, 20));
		lifeLabel.setBounds(300, 250, 200, 200);
		this.add(lifeLabel);				
		
		frame.setSize(labelHeight+18, labelWidth+100);
		frame.addKeyListener(this);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, labelHeight, labelWidth);
		for(int i = 0 ; i < board.getNotMoveableSprites().size() ; i++) {
			Sprite obj = (Sprite) board.getNotMoveableSprites().get(i);
			obj.draw(g);
		}
		board.getPacman().draw(g);
		board.getblinky().draw(g);
		board.getInky().draw(g);
		board.getPinky().draw(g);
		board.getPokey().draw(g);
	}
	
	public void gampLoop() {
		board.gameStart();
		while(board.turnOff) {
			repaint();
			scoreLabel.setText("Score : " + board.getScore());
			lifeLabel.setText("Life : " + board.getLife());
			if(board.getLife()==0) board.turnOff = false;
			try {
				Thread.sleep(20);
			} catch (Exception e) {}
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		Direction d = null; 
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP : 
			d = Direction.UP;
			board.getPacman().setDirection(d);
			break;
		case KeyEvent.VK_DOWN : 
			d = Direction.DOWN;
			board.getPacman().setDirection(d);
			break;
		case KeyEvent.VK_LEFT : 
			d = Direction.LEFT;
			board.getPacman().setDirection(d);
			break;
		case KeyEvent.VK_RIGHT : 
			d = Direction.RIGHT;
			board.getPacman().setDirection(d);
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
