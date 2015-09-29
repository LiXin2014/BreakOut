import java.util.ArrayList;
import java.util.LinkedList;


public class CommandManager 
{
	private LinkedList<ArrayList<Command>> commandHistory;
	/**
	 * @return the commandHistory
	 */
	public LinkedList<ArrayList<Command>> getCommandHistory() {
		return commandHistory;
	}
	/**
	 * @param commandHistory the commandHistory to set
	 */
	public void setCommandHistory(LinkedList<ArrayList<Command>> commandHistory) {
		this.commandHistory = commandHistory;
	}
	public CommandManager()
	{
		commandHistory = new LinkedList<ArrayList<Command>>();
	}
	public void addCommand(ArrayList<Command> cmdList)
	{
		commandHistory.add(cmdList);
	}
	public ArrayList<Command> removeLastCommand()
	{
		return commandHistory.removeLast();
	}
	
}
