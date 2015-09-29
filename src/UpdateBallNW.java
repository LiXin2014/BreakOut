
public class UpdateBallNW implements Command {
	private Ball ball;
	private ComponentObserver ob;
	public UpdateBallNW(ComponentObserver ob)
	{
		ball = (Ball)ob;
		this.ob = ob;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stu	
		ball.setXdir(-1);
		ball.setYdir(-1);
		ob.update();
	}

	@Override
	public void undo() {
		ball.setXdir(1);
		ball.setYdir(1);
		ob.undo();
	}

}
