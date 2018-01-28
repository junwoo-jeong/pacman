package board;

import java.util.ArrayList;

import character.Blinky;
import character.Empty;
import character.Inky;
import character.Pacman;
import character.Pinky;
import character.Pokey;
import character.PowerDot;
import character.SmallDot;
import character.Sprite;
import character.Wall;

public class PacmanBoard {
	static final char WALL = '#';
	static final char SMALLDOT = '*';
	static final char POWERDOT = '@';
	static final char BILINLY = 'B';
	static final char INKY = 'I';
	static final char PINKY = 'N';
	static final char POKEY = 'O';
	static final char PLAYER = 'P';
	static final char EMPTY =  'E';
	private static char[][] board = {
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
			{'#', 'P', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'N', '#'},
			{'#', '*', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '*', '#'},
			{'#', '*', '#', '*', '*', '*', '*', '*', '#', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '#', '*', '*', '*', '*', '*', '#', '*', '#'},
			{'#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#'},
			{'#', '*', '@', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '#', '*', '#', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '*', '*', '#'},
			{'#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#'},
			{'#', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'O', '*', '*', '#', '*', '#'},
			{'#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#'},
			{'#', '*', '*', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '#', '*', '#', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '*', '*', '#'},
			{'#', '@', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '*', '#', '*', '#'},
			{'#', '*', '#', '*', '*', '*', '*', '*', '#', '*', '#', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '#', '*', '#', '*', '*', '*', '*', '*', '#', '*', '#'},
			{'#', '*', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '*', '#', '#', '#', '#', '#', '*', '#', '*', '#', '*', '#', '#', '#', '#', '#', '*', '#'},
			{'#', 'I', '*', '*', '*', '@', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', '*', 'B', '#'},
			{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}};
	public static boolean scaredStatus = false;
	public static int scaredTime = 30;
	
	private Pacman pacman;
	private Blinky blinky;
	private Inky inky;
	private Pokey pokey;
	private Pinky pinky;
	private GhostAI ai = new GhostAI(); 
	private ArrayList<Sprite> notMoveableSprites = new ArrayList<Sprite>();
	private ArrayList<Thread> moveableThread = new ArrayList<Thread>();
	
	private int life;
	private int score;
	public static boolean turnOff = true;
	
	private int pacmanUpdateMS = 200;
	private int ghostsUpdateMS = 250;
	
	public PacmanBoard() {
		initSprites();
		initThread();
		life = 3;
		score = 0;
	}
	//静饭靛 积己
	public void initThread() {
		moveableThread.add(new Thread("pacman") {
			public void run() {
				while(true) {
					PacmanCollisonChack();
					pacman.move();
					try {Thread.sleep(pacmanUpdateMS);} catch (Exception e) {}
				}
			}
		});
		moveableThread.add(new Thread("blinky") {
			public void run() {
				while(true) {
					if(scaredStatus) {
						blinky.setDirection(ai.scaredSearch(getBoolBoard(), pacman.getX(), pacman.getY(), blinky.getX(), blinky.getY()));
					}
					else {
						blinky.setDirection(ai.smartSearch(getBoolBoard(), pacman.getX(), pacman.getY(), blinky.getX(), blinky.getY()));
						scaredTime = 30;
					}
					blinky.move();
					try {Thread.sleep(ghostsUpdateMS);} catch (Exception e) {}
				}
			}
		});
		moveableThread.add(new Thread("inky") {
			public void run() {
				while(true) {
					if(scaredStatus) {
						inky.setDirection(ai.scaredSearch(getBoolBoard(), pacman.getX(), pacman.getY(), inky.getX(), inky.getY()));
					}
					else {
						inky.setDirection(ai.smartSearch(getBoolBoard(), pacman.getX(), pacman.getY(), inky.getX(), inky.getY()));
						scaredTime = 30;
					}
					inky.move();
					try {Thread.sleep(ghostsUpdateMS);} catch (Exception e) {}
				}
			}
		});
		moveableThread.add(new Thread("pinky") {
			public void run() {
				while(true) {
					if(scaredStatus) {
						pinky.setDirection(ai.scaredSearch(getBoolBoard(), pacman.getX(), pacman.getY(), pinky.getX(), pinky.getY()));
					}
					else {
						pinky.setDirection(ai.randomSearch(getBoolBoard(), pinky.getX(), pinky.getY()));
						scaredTime = 30;
					}
					pinky.move();
					try {Thread.sleep(ghostsUpdateMS);} catch (Exception e) {}
				}
			}
		});
		moveableThread.add(new Thread("pokey") {
			public void run() {
				while(true) {
					if(scaredStatus) {
						pokey.setDirection(ai.scaredSearch(getBoolBoard(), pacman.getX(), pacman.getY(), pokey.getX(), pokey.getY()));
					}
					else {
						pokey.setDirection(ai.randomSearch(getBoolBoard(), pokey.getX(), pokey.getY()));
						scaredTime = 30;
					}
					pokey.move();
					try {Thread.sleep(ghostsUpdateMS);} catch (Exception e) {}
				}
			}
		});
	}
	//按眉 积己
	public void initSprites() {
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board[i].length ; j++) {
				Sprite obj;
				switch(board[i][j]) {
				case PLAYER :
					pacman = new Pacman(j, i);
					break;
				case BILINLY :
					blinky = new Blinky(j, i);
					break;
				case INKY :
					inky = new Inky(j, i);
					break;
				case PINKY :
					pinky = new Pinky(j, i);
					break;
				case POKEY :
					pokey = new Pokey(j, i);
					break;
				case SMALLDOT :
					obj = new SmallDot(j, i);
					notMoveableSprites.add(obj);
					break;
				case WALL :
					obj = new Wall(j, i);
					notMoveableSprites.add(obj);
					break;
				case POWERDOT :
					obj = new PowerDot(j, i);
					notMoveableSprites.add(obj);
					break;
				case EMPTY :
					obj = new Empty(j, i);
					notMoveableSprites.add(obj);
					break;
				}// switch end
			}// inner for end
		}//outer for end
	}//initGameObjects Method end

	public void PacmanCollisonChack() {
		if(pacman.checkCollision(blinky)) {
			pacman.handleCollision(blinky);
			life--;
		}
		if(pacman.checkCollision(pinky)) {
			pacman.handleCollision(pinky);
			life--;
		}
		if(pacman.checkCollision(inky)) {
			pacman.handleCollision(inky);
			life--;
		}
		if(pacman.checkCollision(pokey)) {
			pacman.handleCollision(pokey);
			life--;
		}
		for(int i = 0 ; i < notMoveableSprites.size() ; i++) {
			Sprite other = (Sprite)notMoveableSprites.get(i);
			if(pacman.checkCollision(other)) {
				if(other instanceof SmallDot) {
					notMoveableSprites.remove(i);
					score+=10;
				}else if(other instanceof PowerDot) {
					notMoveableSprites.remove(i);
					pacman.handleCollision(other);
				}
				else {
					pacman.handleCollision(other);
				}
			}
		}
	}
	
	public void gameStart() {
		for(int i = 0 ; i < moveableThread.size() ; i++) {
			moveableThread.get(i).start();
		}
	}
	
	public int getScore() {
		return score;
	}
	public Pacman getPacman() {
		return pacman;
	}
	public Blinky getblinky() {
		return blinky;
	}
	public Inky getInky() {
		return inky;
	}
	public Pokey getPokey() {
		return pokey;
	}
	public Pinky getPinky() {
		return pinky;
	}
	public ArrayList<Sprite> getNotMoveableSprites() {
		return notMoveableSprites;
	}
	public void setNotMoveableSprites(ArrayList<Sprite> notMoveableSprites) {
		this.notMoveableSprites = notMoveableSprites;
	}
	public char[][] getBoard() {
		return board;
	}
	
	public int getLife() {
		return life;
	}
	public boolean[][] getBoolBoard() {
		boolean[][] b = new boolean[board.length][board[1].length];
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0; j < board[i].length ; j++) {
				if(board[i][j] == '#') b[i][j] = false;
				else b[i][j] = true;
			}
		}
		return b;
	}
}
