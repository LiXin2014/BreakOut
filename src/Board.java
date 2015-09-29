
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Board extends JFrame implements Dimensions {

	// Variable Declaration
	private boolean isRunning;
	private boolean isUndoClicked;
	private JPanel contentPane;
	private JPanel panelRight;
	private JPanel panelLeft;
	private JLabel timeLabel;
	private JLabel msgLabel;
	private JButton pauseButton;
	private JButton stopButton;
	private JButton undoButton;
	private JButton replayButton;
	private Ball ball;
	private Paddle paddle;
	private Brick brick;
	private TimerTask timerTask;
	private GameTimer gameTimer;
	/**
	 * @return the pauseButton
	 */
	public JButton getPauseButton() {
		return pauseButton;
	}

	/**
	 * @return the timerTask
	 */
	public TimerTask getTimerTask() {
		return timerTask;
	}

	/**
	 * @return the timeLabel
	 */
	public JLabel getTimeLabel() {
		return timeLabel;
	}

	/**
	 * @param timeLabel the timeLabel to set
	 */
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}

	/**
	 * @param timerTask the timerTask to set
	 */
	public void setTimerTask(TimerTask timerTask) {
		this.timerTask = timerTask;
	}

	/**
	 * @param pauseButton the pauseButton to set
	 */
	public void setPauseButton(JButton pauseButton) {
		this.pauseButton = pauseButton;
	}

	/**
	 * @return the stopButton
	 */
	public JButton getStopButton() {
		return stopButton;
	}

	/**
	 * @param stopButton the stopButton to set
	 */
	public void setStopButton(JButton stopButton) {
		this.stopButton = stopButton;
	}

	/**
	 * @return the undoButton
	 */
	public JButton getUndoButton() {
		return undoButton;
	}

	/**
	 * @param undoButton the undoButton to set
	 */
	public void setUndoButton(JButton undoButton) {
		this.undoButton = undoButton;
	}

	/**
	 * @return the replayButton
	 */
	public JButton getReplayButton() {
		return replayButton;
	}

	/**
	 * @param replayButton the replayButton to set
	 */
	public void setReplayButton(JButton replayButton) {
		this.replayButton = replayButton;
	}

	private Replay replay;
	private CommandManager cmdManager;
	private ArrayList<Command> lastCommandList;
	private boolean isReplayGameOver;
	
	/**
	 * @return the isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning the isRunning to set
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/**
	 * @return the msgLabel
	 */
	public JLabel getMsgLabel() {
		return msgLabel;
	}

	/**
	 * @param msgLabel the msgLabel to set
	 */
	public void setMsgLabel(JLabel msgLabel) {
		this.msgLabel = msgLabel;
	}

	/**
	 * @return the paddle
	 */
	public Paddle getPaddle() {
		return paddle;
	}

	/**
	 * @param paddle the paddle to set
	 */
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	/**
	 * @return the gameTimer
	 */
	public GameTimer getGameTimer() {
		return gameTimer;
	}

	/**
	 * @param gameTimer the gameTimer to set
	 */
	public void setGameTimer(GameTimer gameTimer) {
		this.gameTimer = gameTimer;
	}

	/**
	 * @return the cmdManager
	 */
	public CommandManager getCmdManager() {
		return cmdManager;
	}

	/**
	 * @param cmdManager the cmdManager to set
	 */
	public void setCmdManager(CommandManager cmdManager) {
		this.cmdManager = cmdManager;
	}

	/**
	 * @return the isReplayGameOver
	 */
	public boolean isReplayGameOver() {
		return isReplayGameOver;
	}

	/**
	 * @param isReplayGameOver the isReplayGameOver to set
	 */
	public void setReplayGameOver(boolean isReplayGameOver) {
		this.isReplayGameOver = isReplayGameOver;
	}

	/**
	 * @return the ball
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * @param ball the ball to set
	 */
	public void setBall(Ball ball) {
		this.ball = ball;
	}

	/**
	 * @return the brick
	 */
	public Brick getBrick() {
		return brick;
	}

	/**
	 * @param brick the brick to set
	 */
	public void setBrick(Brick brick) {
		this.brick = brick;
	}

	LinkedList<ArrayList<Command>> tempHistory;
	
	public Board() {

		tempHistory = new LinkedList<ArrayList<Command>>();
		isRunning = false;
		isUndoClicked = false;
		isReplayGameOver = false;

		panelRight = new JPanel();
		panelLeft = new JPanel();
		timeLabel = new JLabel();
		msgLabel = new JLabel();
		pauseButton = new JButton();
		stopButton = new JButton();
		undoButton = new JButton();
		replayButton = new JButton();

		cmdManager = new CommandManager();
		lastCommandList = new ArrayList<Command>();
		paddle = new Paddle(this);
		brick = new Brick();
		gameTimer = new GameTimer(this);
		timerTask = new TimerTask(this);
		ball = new Ball(this);

		// build the main container JFrame
		setTitle("Break Out Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Dimensions.START_X, Dimensions.START_Y, Dimensions.WIDTH,
				Dimensions.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLayeredPane layeredPane = new JLayeredPane();

		// build the right panel of the JFrame
		// the ball, paddle and bricks are in this panel
		contentPane.add(layeredPane, BorderLayout.CENTER);
		panelRight.setBackground(Color.LIGHT_GRAY);
		panelRight.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelRight.setBounds(110, 11, 454, 330);
		layeredPane.add(panelRight);

		// build the right panel of the JFrame
		// the timer and the game messages are displayed here
		panelLeft.setBackground(Color.GRAY);
		panelLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLeft.setBounds(10, 11, 90, 330);
		layeredPane.add(panelLeft);

		initState();

		addKeyListener(new StrokeAdapter());
		pauseButton.addActionListener(new PauseAdapter());
		stopButton.addActionListener(new StopAdapter());
		undoButton.addActionListener(new UndoAdapter());
		replayButton.addActionListener(new ReplayAdapter());
	}

	public void initState() {
		
		isUndoClicked = false;
		
		paddle.setX(300);
		paddle.setY(360);

		ball.setX(325);
		ball.setY(330);

		brick.reset();
		ball.reset();
		paddle.reset();
		gameTimer.reset();

		// time label to display the time elapsed since the game starts
		timeLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
		panelLeft.add(timeLabel);

		// Stop button
		stopButton.setText("Start");
		stopButton.setFocusable(false);
		stopButton.setName("Start");
		panelLeft.add(stopButton);

		// Pause button
		pauseButton.setText("Pause");
		pauseButton.setFocusable(false);
		pauseButton.setEnabled(false);
		pauseButton.setName("Pause");
		panelLeft.add(pauseButton);

		// Undo button
		undoButton.setText("Undo");
		undoButton.setFocusable(false);
		undoButton.setEnabled(false);
		panelLeft.add(undoButton);

		// Replay button
		replayButton.setText("Replay");
		replayButton.setEnabled(false);
		replayButton.setFocusable(false);
		panelLeft.add(replayButton);

		// message label to print a message when the game finishes
		msgLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		msgLabel.setForeground(Color.RED);
		panelLeft.add(msgLabel);
		msgLabel.setText("");

		repaint();
	}

	class PauseAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (pauseButton.getName().equals("Pause")) {
				pauseButton.setName("Resume");
				pauseButton.setText("Resume");
				undoButton.setEnabled(true);
				replayButton.setEnabled(true);
				Board.this.pause();
			} else {
				pauseButton.setName("Pause");
				pauseButton.setText("Pause");
				undoButton.setEnabled(false);
				replayButton.setEnabled(false);
				Board.this.resume();
			}
		}
	}
	
	class ReplayAdapter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			gameTimer.setReplayPauseTime(gameTimer.getPauseTime());
			initState();
			replay = new Replay(Board.this);
			timerTask.register(replay);
		}
		
	}
	
	class UndoAdapter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Board.this.isUndoClicked = true;
			lastCommandList = cmdManager.removeLastCommand();
			timerTask.undoOperation(lastCommandList);
			if(Board.this.cmdManager.getCommandHistory().size() == 0)
			{
				Board.this.undoButton.setEnabled(false);
			}
		}
		
	}

	class StopAdapter implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (stopButton.getName().equals("Stop")) {
				stopButton.setName("Start");
				stopButton.setText("Start");
				Board.this.stop();
			} else {
				stopButton.setName("Stop");
				stopButton.setText("Stop");
				Board.this.start();
			}
		}
	}

	public void start() {
		timerTask.register(ball);
		timerTask.register(paddle);
		timerTask.register(gameTimer);
		pauseButton.setEnabled(true);
		gameTimer.reset();
		brick.setDestroyedInReplay(false);
		isReplayGameOver = false;
		timerTask.run();
		isRunning = true;
	}

	public void stop() {
		timerTask.unRegister(ball);
		timerTask.unRegister(gameTimer);
		timerTask.unRegister(paddle);
		initState();
		cmdManager.getCommandHistory().clear();
	}

	public void pause() {
		if(isUndoClicked)
		{
			ball.setXdir(-ball.getXdir());
			ball.setYdir(-ball.getYdir());
		}
		timerTask.unRegister(ball);
		timerTask.unRegister(gameTimer);
		timerTask.unRegister(paddle);
		gameTimer.setPauseTime(gameTimer.getTimeElapsed());
	}

	public void resume() {
		paddle.setDx(0);
		if(isUndoClicked)
		{
			ball.setXdir(-ball.getXdir());
			ball.setYdir(-ball.getYdir());
		}
		timerTask.unRegister(replay);
		timerTask.register(ball);
		timerTask.register(paddle);
		timerTask.register(gameTimer);
		//gameTimer.timeElapsed = 0;
		gameTimer.setStartTime(System.currentTimeMillis());
	}

	/**
	 * Key Listener adapter class
	 */
	class StrokeAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent ke) {
			int key = ke.getKeyCode();
			
			if (key == KeyEvent.VK_LEFT && isRunning == true) {
				paddle.move("left");
			} else if (key == KeyEvent.VK_RIGHT && isRunning == true) {
				paddle.move("right");
			}
		}

		public void keyReleased(KeyEvent ke) {
			if (isRunning == true) {
				paddle.KeyReleased();
			}
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Board boardObj = new Board();
		boardObj.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(g);
		/*
		 * Paddle fillRect(x, y, width, height)
		 */
		g.setColor(Color.BLACK);
		g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(),
				paddle.getHeight());
		/*
		 * Ball * fillOval(x, y, width, height)
		 */
		g.setColor(Color.RED);
		g.fillOval(ball.getX(), ball.getY(), ball.getWidth(), ball.getHeight());
		/*
		 * Brick fillRect(x, y, width, height)
		 */
		if (brick.isDestroyed() == false) {
			g.setColor(Color.BLUE);
			g.fillRect(brick.getX(), brick.getY(), brick.getWidth(),
					brick.getHeight());
		}

	}
}
