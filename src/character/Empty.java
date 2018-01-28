package character;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Empty extends Sprite implements NotMoveable{
	private Image image = new ImageIcon(getClass().getResource("../resourse/img/empty.png")).getImage();
	
	public Empty(int x, int y) {
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
