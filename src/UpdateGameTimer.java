
public class UpdateGameTimer implements Command {

	private GameTimer gameTimer;
	
	public UpdateGameTimer(GameTimer gameTimer) {
		// TODO Auto-generated constructor stub
		this.gameTimer = gameTimer;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		gameTimer.update();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		gameTimer.undo();
	}

}
