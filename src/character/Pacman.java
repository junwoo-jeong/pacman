package character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.xml.bind.SchemaOutputResolver;

import board.PacmanBoard;

public class Pacman extends Sprite implements Moveable{
	private int dx;		//단위 시간당 x축 이동거리 
	private int dy;		//단위 시간당 y축 이동거리
	private Direction direction;  //이동 방
	private boolean notMove;
	
	
	private Image upImage = new ImageIcon(getClass().getResource("../resourse/img/PacmanUP.gif")).getImage();
	private Image downImage = new ImageIcon(getClass().getResource("../resourse/img/PacmanDOWN.gif")).getImage();
	private Image leftImage = new ImageIcon(getClass().getResource("../resourse/img/PacmanLEFT.gif")).getImage();
	private Image rightImage = new ImageIcon(getClass().getResource("../resourse/img/PacmanRIGHT.gif")).getImage();

	public Pacman(int x, int y) {
		super(x, y);
		this.dx = 0;
		this.dy = 0;
		this.notMove = false;
		this.direction = Direction.NONE;
	}
	
	@Override
	public void move() {
		if(notMove) {
			notMove = false;
		}else {	
			if(PacmanBoard.scaredStatus && PacmanBoard.scaredTime!=0) PacmanBoard.scaredTime--;
			else PacmanBoard.scaredStatus = false;
			setX();
			setY();
		}
	}

	
	//객체간 충돌을 검출하는 메소드
	@Override
	public boolean checkCollision(Sprite other) {
		if((other instanceof Blinky || other instanceof Inky || other instanceof Pinky || other instanceof Pokey) && this.x == other.getX() && this.y == other.getY()) {
			return true;
		}
		if(!(other instanceof Blinky) && !(other instanceof Inky) && !(other instanceof Pinky) && !(other instanceof Pokey) && (this.x+direction.getX()) == other.getX() && (this.y+direction.getY()) == other.getY()) {
			return true;
		}
		if(other instanceof PowerDot && (this.x+direction.getX()) == other.getX() && (this.y+direction.getY()) == other.getY()) {
			return true;
		}
			
		else return false;
	}
	
	//객체간 충돌을 처리하는 메소드
	@Override
	public void handleCollision(Sprite other) {
		if(other instanceof Wall) {		//벽과 충돌
			notMove = true;
		}else if(other instanceof Blinky || other instanceof Inky || other instanceof Pinky || other instanceof Pokey) {		//유령들과 충돌
			if(PacmanBoard.scaredStatus) {
				
			}else {
				this.x = 1;
				this.y = 1;
				this.dx = 0;
				this.dy = 0;
				this.direction = Direction.NONE;
			}
		}
		if(other instanceof PowerDot) {
			PacmanBoard.scaredStatus = true;
		}
	}

	public void setDirection(Direction d) {this.direction = d;}
	public void setX() {this.x += direction.getX();}
	public void setY() {this.y += direction.getY();}
	
	@Override
	public void draw(Graphics g) {
		if(direction == Direction.UP) g.drawImage(upImage, x*22, y*22, null);
		else if(direction == Direction.DOWN) g.drawImage(downImage, x*22, y*22, null);
		else if(direction == Direction.LEFT) g.drawImage(leftImage, x*22, y*22, null);
		else g.drawImage(rightImage, x*22, y*22, null);
	}
	
}
