

public class UpdatePaddleRight implements Command {

	private ComponentObserver ob;
	private Paddle paddle;
	public UpdatePaddleRight(ComponentObserver ob)
	{
		this.ob = ob;
		paddle = (Paddle)ob;
		
	}
	@Override
	public void execute() {
		paddle.setDx(7);
		paddle.setHasMoved(Paddle.paddleMove.right);
		ob.update();
	}

	@Override
	public void undo() {
		paddle.setDx(-7);
		
		//paddle.hasMoved = paddleMove.right;
		ob.undo();

	}

}
