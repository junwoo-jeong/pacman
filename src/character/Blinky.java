package character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import board.PacmanBoard;

public class Blinky extends Sprite implements Moveable{
	private int dx;
	private int dy;
	private Direction direction;
	private boolean notMove;
	private boolean live;
	
	private Image upImage = new ImageIcon(getClass().getResource("../resourse/img/blinkyUP.png")).getImage();
	private Image downImage = new ImageIcon(getClass().getResource("../resourse/img/blinkyDOWN.png")).getImage();
	private Image leftImage = new ImageIcon(getClass().getResource("../resourse/img/blinkyLEFT.png")).getImage();
	private Image rightImage = new ImageIcon(getClass().getResource("../resourse/img/blinkyRIGHT.png")).getImage();
	private Image scaredImage = new ImageIcon(getClass().getResource("../resourse/img/ghostScared.png")).getImage();
	private Image dieUpImage =	new ImageIcon(getClass().getResource("../resourse/img/ghostDieUP.png")).getImage();
	private Image dieDownImage = new ImageIcon(getClass().getResource("../resourse/img/ghostDieDOWN.png")).getImage();
	private Image dieLeftImage = new ImageIcon(getClass().getResource("../resourse/img/ghostDieLEFT.png")).getImage();
	private Image dieRightImage = new ImageIcon(getClass().getResource("../resourse/img/ghostDieRIGHT.png")).getImage();

	public Blinky(int x, int y) {
		super(x, y);
		this.dx = 0;
		this.dy = 0;
		this.notMove = false;
		this.direction = Direction.NONE;
		this.live = true;
	}

	@Override
	public void move() {
		if(notMove) {
			notMove = false;
		}else {	
			setX();
			setY();
		}
	}

	@Override
	public boolean checkCollision(Sprite other) {
		return false;
	}

	@Override
	public void handleCollision(Sprite other) {
		
	}
	
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public void setX() {this.x += direction.getX();}
	public void setY() {this.y += direction.getY();}
	
	@Override
	public void draw(Graphics g) {
		if(live) {
			if(PacmanBoard.scaredStatus) g.drawImage(scaredImage, x*22, y*22, null);
			else if(direction == Direction.UP) g.drawImage(upImage, x*22, y*22, null);
			else if(direction == Direction.DOWN) g.drawImage(downImage, x*22, y*22, null);
			else if(direction == Direction.LEFT) g.drawImage(leftImage, x*22, y*22, null);
			else g.drawImage(rightImage, x*22, y*22, null);
		}else {
			if(PacmanBoard.scaredStatus) g.drawImage(scaredImage, x*22, y*22, null);
			else if(direction == Direction.UP) g.drawImage(dieUpImage, x*22, y*22, null);
			else if(direction == Direction.DOWN) g.drawImage(dieDownImage, x*22, y*22, null);
			else if(direction == Direction.LEFT) g.drawImage(dieLeftImage, x*22, y*22, null);
			else g.drawImage(dieRightImage, x*22, y*22, null);
		}
	}
}
