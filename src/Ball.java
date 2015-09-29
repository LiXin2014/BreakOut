
import java.util.Random;

public class Ball extends Sprite implements Dimensions, ComponentObserver {
	private int xdir;
	private int ydir;
	private Board board;
	private int arrXdir[] = { -1, 1 };

	/**
	 * @return the xdir
	 */
	public int getXdir() {
		return xdir;
	}

	/**
	 * @param xdir
	 *            the xdir to set
	 */
	public void setXdir(int xdir) {
		this.xdir = xdir;
	}

	/**
	 * @return the ydir
	 */
	public int getYdir() {
		return ydir;
	}

	/**
	 * @param ydir
	 *            the ydir to set
	 */
	public void setYdir(int ydir) {
		this.ydir = ydir;
	}

	public Ball(Board board) {
		this.board = board;
		this.setHeight(Dimensions.BALL_HEIGHT);
		this.setWidth(Dimensions.BALL_WIDTH);
		Random random = new Random();
		xdir = arrXdir[random.nextInt(arrXdir.length)];
		reset();
	}

	public void reset() {

		// Setting initial coordinates of ball
		this.setX(Dimensions.BALL_X);
		this.setY(Dimensions.BALL_Y);
		ydir = -1;
	}

	// Check if the ball collides with the right end
	public boolean isCollidingWithRightWall() {
		if (this.getX() >= 548) {
			return true;
		}
		return false;
	}

	// Check if the ball collides with the top end
	public boolean isCollidingWithTopWall() {
		if (this.getY() <= 45) {
			return true;
		}
		return false;
	}

	// Check if the ball collides with the left end
	public boolean isCollidingWithLeftWall() {
		if (this.getX() <= 125) {
			return true;
		}
		return false;
	}

	// Check if the ball collides with the paddle
	public boolean isCollidingWithPaddle() {

		if (this.getX() >= board.getPaddle().getX()
				&& this.getX() <= board.getPaddle().getX() + board.getPaddle().getWidth()) {
			if (this.getY() >= 330 && this.getY() <= 335) {
				return true;
			}
		}
		return false;
	}

	// Check if the ball collides with the brick
	public boolean isCollidingWithBrick() {
		if (this.getX() >= board.getBrick().getX() - board.getBall().getHeight()
				&& this.getX() <= board.getBrick().getX() + board.getBrick().getWidth()
				&& this.getY() >= board.getBrick().getY() - board.getBall().getHeight()
				&& this.getY() <= board.getBrick().getY() + board.getBrick().getHeight()) {
			board.getBrick().setDestroyed(true);
			return true;
		}
		return false;
	}

	// Check if the ball has touched the floor
	public boolean isGameOver() {
		if (this.getX() <= board.getPaddle().getX()
				|| this.getX() >= board.getPaddle().getX() + board.getPaddle().getWidth()) {
			if (this.getY() >= 400) {
				return true;
			}
		}
		return false;
	}

	/*
	 * update() method of the Observer class Used to update the position of the
	 * ball
	 */

	public Command getCommand() {
		if (isCollidingWithRightWall()) {
			if (ydir == 1) {
				return (new UpdateBallSW(this));
			} else {
				return (new UpdateBallNW(this));
			}
		}

		if (isCollidingWithTopWall()) {
			if (xdir == 1) {
				return (new UpdateBallSE(this));
			} else {
				return (new UpdateBallSW(this));
			}
		}

		if (isCollidingWithLeftWall()) {
			if (ydir == 1) {
				return (new UpdateBallSE(this));
			} else {
				return (new UpdateBallNE(this));
			}
		}

		if (isCollidingWithPaddle()) {
			if (xdir == 1) {
				return (new UpdateBallNE(this));
			} else {
				return (new UpdateBallNW(this));
			}
		}

		if (isCollidingWithBrick()) {
			// player wins when brick is destroyed
			// all the observers are unregistered
			board.repaint();
			board.setRunning(false);
			board.getMsgLabel().setText("You win!  ");
			board.getPauseButton().setEnabled(false);
			board.getTimerTask().unRegister(this);
			board.getTimerTask().unRegister(board.getPaddle());
			board.getTimerTask().unRegister(board.getGameTimer());
			board.getReplayButton().setEnabled(true);
			board.getBrick().setDestroyedInReplay(true);
		}

		if (isGameOver()) {
			// player looses when the ball falls through the floor
			// game over message is printed
			// and all the observers are unregistered
			board.setRunning(false);
			board.getMsgLabel().setText("Game Over!");
			board.getTimerTask().unRegister(this);
			board.getTimerTask().unRegister(board.getPaddle());
			board.getTimerTask().unRegister(board.getGameTimer());
			board.getReplayButton().setEnabled(true);
			board.getPauseButton().setEnabled(false);
			board.setReplayGameOver(true);
		}

		if (xdir < 0) {
			if (ydir < 0) {
				return (new UpdateBallNW(this));
			} else {
				return (new UpdateBallSW(this));
			}
		} else {
			if (ydir < 0) {
				return (new UpdateBallNE(this));
			} else {
				return (new UpdateBallSE(this));
			}
		}
	}

	@Override
	public void update() {
		this.setX(this.getX() + xdir);
		this.setY(this.getY() + ydir);
		board.repaint();
	}

	public void undo() {
		this.setX(this.getX() + xdir);
		this.setY(this.getY() + ydir);
		board.repaint();
	}

	@Override
	public void draw(Board com) {
		com.repaint();
	}
}
