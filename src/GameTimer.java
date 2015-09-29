
import javax.swing.JLabel;

public class GameTimer implements ComponentObserver {
	
	private Board board;
	// Storing the current System time
	private long startTime;
	// Variable to store the elapsed time
	private long timeElapsed;
	private long pauseTime;
	private long replayPauseTime;
	
	public GameTimer(Board board)
	{
		pauseTime = 0;
		replayPauseTime = 0;
		this.board = board;
	}
	
	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the timeElapsed
	 */
	public long getTimeElapsed() {
		return timeElapsed;
	}

	/**
	 * @param timeElapsed the timeElapsed to set
	 */
	public void setTimeElapsed(long timeElapsed) {
		this.timeElapsed = timeElapsed;
	}

	/**
	 * @return the pauseTime
	 */
	public long getPauseTime() {
		return pauseTime;
	}

	/**
	 * @param pauseTime the pauseTime to set
	 */
	public void setPauseTime(long pauseTime) {
		this.pauseTime = pauseTime;
	}

	/**
	 * @return the replayPauseTime
	 */
	public long getReplayPauseTime() {
		return replayPauseTime;
	}

	/**
	 * @param replayPauseTime the replayPauseTime to set
	 */
	public void setReplayPauseTime(long replayPauseTime) {
		this.replayPauseTime = replayPauseTime;
	}

	public void reset()
	{
		startTime = System.currentTimeMillis();
		pauseTime = 0;
		board.getTimeLabel().setText("00:00");
	}

	// Update method to find the elapsed time
	@Override
	public void update() {
		timeElapsed = System.currentTimeMillis() - startTime;
		timeElapsed += pauseTime;
		draw(board);
	}

	// Draw the eplased time on the panel
	@Override
	public void draw(Board com) {
		JLabel label = (JLabel) com.getTimeLabel();

		int sec = (int) ((timeElapsed / 1000) % 60);
		int min = (int) (timeElapsed / (60 * 1000));

		String secString = String.format("%02d", sec);
		String minString = String.format("%02d", min);

		label.setText(minString + ":" + secString);
		label.repaint();
	}

	@Override
	public Command getCommand() {
		return new UpdateGameTimer(this);
	}

	@Override
	public void undo() {
		timeElapsed = timeElapsed -10;
		draw(board);
	}
}
