package board;
/*
 * 4방향 검사 부분 리팩토링 필요
 * 
 * 
 * */
import java.util.LinkedList;

import character.Direction;
import character.Node;

public class GhostAI {
	public Direction smartSearch(boolean[][] b, int pacmanX, int pacmanY, int ghostX, int ghostY) {
		boolean[][] board = b;//장애물의 유무를 나타내는 2차원 배열
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
	
	//팩맨과 멀어지는 알고리즘 
	public Direction scaredSearch(boolean[][] b, int pacmanX, int pacmanY, int ghostX, int ghostY) {
		boolean[][] board = b;//장애물의 유무를 나타내는 2차원 배열
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
	
	//랜덤이동 AI
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
		int index = (int)(Math.random()*(moveableDirections.size()));//랜덤하게 이동
		return moveableDirections.get(index).d;
	}
	
	//이동할 수 있는 방향이 교차로(이동 가능한 방향이 3개 이상)일 경우에만 팩멘을 따라가는 AI
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
			int index = (int)(Math.random()*(moveableDirections.size()));//랜덤하게 이동
			return moveableDirections.get(index).d;
		}
		else {
			return ghostDirection;
		}
	}
}
