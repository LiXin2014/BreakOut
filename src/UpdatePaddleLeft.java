

public class UpdatePaddleLeft implements Command {

	private ComponentObserver ob;
	private Paddle paddle;
	
	public UpdatePaddleLeft(ComponentObserver ob)
	{
		this.ob = ob;
		paddle = (Paddle)ob;
		
	}
	@Override
	public void execute() {
		paddle.setDx(-7);
		paddle.setHasMoved(Paddle.paddleMove.left);
		ob.update();
	}

	@Override
	public void undo() {
		paddle.setDx(7);
		//paddle.hasMoved = paddleMove.left;
		ob.undo();

	}

}
