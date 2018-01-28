package character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Wall extends Sprite implements NotMoveable{
	private Image image = new ImageIcon(getClass().getResource("../resourse/img/wall.png")).getImage();
	
	public Wall(int x, int y) {
		super(x, y);
	}
	
	
	@Override
	public boolean checkCollision(Sprite other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(Sprite other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x*22, y*22, null);
	}
}
