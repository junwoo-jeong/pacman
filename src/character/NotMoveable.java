package character;

public interface NotMoveable {
	//다른객체와의 충돌 여부를 계산한다. 충돌이면 true 반환
		public boolean checkCollision(Sprite other);
		
		//충돌을 처리한다.
		public void handleCollision(Sprite other);
}
