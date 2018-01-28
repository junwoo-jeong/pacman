package character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.xml.bind.SchemaOutputResolver;

import board.PacmanBoard;

public class Pacman extends Sprite implements Moveable{
	private int dx;		//���� �ð��� x�� �̵��Ÿ� 
	private int dy;		//���� �ð��� y�� �̵��Ÿ�
	private Direction direction;  //�̵� ��
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

	
	//��ü�� �浹�� �����ϴ� �޼ҵ�
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
	
	//��ü�� �浹�� ó���ϴ� �޼ҵ�
	@Override
	public void handleCollision(Sprite other) {
		if(other instanceof Wall) {		//���� �浹
			notMove = true;
		}else if(other instanceof Blinky || other instanceof Inky || other instanceof Pinky || other instanceof Pokey) {		//���ɵ�� �浹
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
