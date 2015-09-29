
public class UpdateBallSE implements Command {
	private Ball ball;
	private ComponentObserver ob;
	public UpdateBallSE(ComponentObserver ob) {
		// TODO Auto-generated constructor stub
		this.ob = ob;
		ball = (Ball)ob;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ball.setXdir(1);
		ball.setYdir(1);
		ob.update();

	}

	@Override
	public void undo() {
		ball.setXdir(-1);
		ball.setYdir(-1);
		ob.undo();

	}

}
