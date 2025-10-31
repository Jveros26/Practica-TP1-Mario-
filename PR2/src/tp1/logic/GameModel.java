package tp1.logic;

public interface GameModel {

	public boolean isFinished();
	public void update();
	public void reset(); 
	public void exit();
	public void resetLives();
	public void resetPoints();
	public void reset(int nLevel);
	public void clearList();
}
