package character;

public interface NotMoveable {
	//�ٸ���ü���� �浹 ���θ� ����Ѵ�. �浹�̸� true ��ȯ
		public boolean checkCollision(Sprite other);
		
		//�浹�� ó���Ѵ�.
		public void handleCollision(Sprite other);
}
