
import java.util.ArrayList;
import java.util.LinkedList;


public class Replay implements Observer{

	private Board board;
	private LinkedList<ArrayList<Command>> tempCommandHistory;
	
	public Replay(Board board) {
		this.board = board;
		tempCommandHistory = new LinkedList<ArrayList<Command>>();
		tempCommandHistory.addAll(board.getCmdManager().getCommandHistory());
		board.getStopButton().setText("Stop");
		board.getStopButton().setName("Stop");
		board.getStopButton().setEnabled(false);
	}
	
	@Override
	public void update() {
		ArrayList<Command> cmdList = new ArrayList<Command>();
		cmdList  = tempCommandHistory.removeFirst();
		
		for(Command cmd: cmdList)
		{
			cmd.execute();
		}
		
		if(tempCommandHistory.size() == 0)
		{
			board.getTimerTask().unRegister(this);
			board.getStopButton().setEnabled(true);
			board.getPauseButton().setText("Resume");
			board.getPauseButton().setName("Resume");
			board.getPauseButton().setEnabled(true);
			board.getGameTimer().setPauseTime(board.getGameTimer().getReplayPauseTime());
			board.getGameTimer().setReplayPauseTime(0);
			if(board.getBrick().isDestroyedInReplay() == true)
			{
				board.getBrick().setDestroyed(true);
				board.getPauseButton().setEnabled(false);
				board.getReplayButton().setEnabled(true);
			}
			if(board.isReplayGameOver() == true)
			{
				board.getPauseButton().setEnabled(false);
				board.getReplayButton().setEnabled(true);
			}
		}
	}
}
