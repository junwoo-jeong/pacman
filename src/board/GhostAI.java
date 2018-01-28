package board;
/*
 * 4���� �˻� �κ� �����丵 �ʿ�
 * 
 * 
 * */
import java.util.LinkedList;

import character.Direction;
import character.Node;

public class GhostAI {
	public Direction smartSearch(boolean[][] b, int pacmanX, int pacmanY, int ghostX, int ghostY) {
		boolean[][] board = b;//��ֹ��� ������ ��Ÿ���� 2���� �迭
		LinkedList<Node> moveableDirections = new LinkedList<Node>();
		LinkedList<Node> needlessDirections = new LinkedList<Node>();
		moveableDirections.add(new Node(ghostX, ghostY, Direction.NONE));
		for(int i = 0 ; i < 4 ; i++) {
			if(i==0) {
				if(board[ghostY][ghostX-1]) moveableDirections.add(new Node(ghostX-1, ghostY, Direction.LEFT));
			}
			if(i==1) {
				if(board[ghostY][ghostX+1]) moveableDirections.add(new Node(ghostX+1, ghostY, Direction.RIGHT));
			}
			if(i==2) {
				if(board[ghostY-1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY-1, Direction.UP));
			}
			if(i==3) {
				if(board[ghostY+1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY+1, Direction.DOWN));
			}
		}
		needlessDirections.add(moveableDirections.get(0));
		moveableDirections.remove(0);
		int min = 999999999;
		int index = 0;
		for(int i = 0; i < moveableDirections.size() ; i++) {
			int d = 10 * Math.abs(moveableDirections.get(i).getX() - pacmanX) + Math.abs(moveableDirections.get(i).getY() - pacmanY); 
			if(min > 10+d) {
				min = 10+d;
				index = i;
			}
		}
		return moveableDirections.get(index).d;
	}
	
	//�Ѹǰ� �־����� �˰��� 
	public Direction scaredSearch(boolean[][] b, int pacmanX, int pacmanY, int ghostX, int ghostY) {
		boolean[][] board = b;//��ֹ��� ������ ��Ÿ���� 2���� �迭
		LinkedList<Node> moveableDirections = new LinkedList<Node>();
		LinkedList<Node> closeList = new LinkedList<Node>();
		moveableDirections.add(new Node(ghostX, ghostY, Direction.NONE));
		for(int i = 0 ; i < 4 ; i++) {
			if(i==0) {
				if(board[ghostY][ghostX-1]) moveableDirections.add(new Node(ghostX-1, ghostY, Direction.LEFT));
			}
			if(i==1) {
				if(board[ghostY][ghostX+1]) moveableDirections.add(new Node(ghostX+1, ghostY, Direction.RIGHT));
			}
			if(i==2) {
				if(board[ghostY-1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY-1, Direction.UP));
			}
			if(i==3) {
				if(board[ghostY+1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY+1, Direction.DOWN));
			}
		}
		closeList.add(moveableDirections.get(0));
		moveableDirections.remove(0);
		int min = 0;
		int index = 0;
		for(int i = 0; i < moveableDirections.size() ; i++) {
			int d = 10 * Math.abs(moveableDirections.get(i).getX() - pacmanX) + Math.abs(moveableDirections.get(i).getY() - pacmanY); 
			if(min < 10+d) {
				min = 10+d;
				index = i;
			}
		}
		return moveableDirections.get(index).d;
	}
	
	//�����̵� AI
	public Direction randomSearch(boolean[][] b, int ghostX, int ghostY) {
		boolean[][] board = b;
		LinkedList<Node> moveableDirections = new LinkedList<Node>();
		for(int i = 0 ; i < 4 ; i++) {
			if(i==0) {
				if(board[ghostY][ghostX-1]) moveableDirections.add(new Node(ghostX-1, ghostY, Direction.LEFT));
			}
			if(i==1) {
				if(board[ghostY][ghostX+1]) moveableDirections.add(new Node(ghostX+1, ghostY, Direction.RIGHT));
			}
			if(i==2) {
				if(board[ghostY-1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY-1, Direction.UP));
			}
			if(i==3) {
				if(board[ghostY+1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY+1, Direction.DOWN));
			}
		}
		int index = (int)(Math.random()*(moveableDirections.size()));//�����ϰ� �̵�
		return moveableDirections.get(index).d;
	}
	
	//�̵��� �� �ִ� ������ ������(�̵� ������ ������ 3�� �̻�)�� ��쿡�� �Ѹ��� ���󰡴� AI
	public Direction normalSearch(boolean[][]b, int ghostX, int ghostY, Direction ghostDirection) {
		boolean[][] board = b;
		LinkedList<Node> moveableDirections = new LinkedList<Node>();
		for(int i = 0 ; i < 4 ; i++) {
			if(i==0) {
				if(board[ghostY][ghostX-1]) moveableDirections.add(new Node(ghostX-1, ghostY, Direction.LEFT));
			}
			if(i==1) {
				if(board[ghostY][ghostX+1]) moveableDirections.add(new Node(ghostX+1, ghostY, Direction.RIGHT));
			}
			if(i==2) {
				if(board[ghostY-1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY-1, Direction.UP));
			}
			if(i==3) {
				if(board[ghostY+1][ghostX]) moveableDirections.add(new Node(ghostX, ghostY+1, Direction.DOWN));
			}
		}
		if(moveableDirections.size()>=3) {
			int index = (int)(Math.random()*(moveableDirections.size()));//�����ϰ� �̵�
			return moveableDirections.get(index).d;
		}
		else {
			return ghostDirection;
		}
	}
}
