
public interface ComponentObserver extends Observer {
	public void undo();
	public void draw(Board com);
	public Command getCommand();
}
