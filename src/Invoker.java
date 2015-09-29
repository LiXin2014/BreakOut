
public class Invoker {
	
	private Command cmd;
	
	public void setCommand(Command cmd)
	{
		this.cmd = cmd;
	}
	public void invoke()
	{
		cmd.execute();
	}
	public void revoke()
	{
		cmd.undo();
	}

}
