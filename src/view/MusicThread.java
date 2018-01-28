package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class MusicThread extends Thread{
	private Player player;
	private boolean isLoop;
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public MusicThread(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../resourse/music/" + name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
	}
	
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
	
	@Override
	public void run() {
		try {
			do {
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			} while (isLoop);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
