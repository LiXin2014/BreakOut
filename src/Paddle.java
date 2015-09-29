
import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Sprite implements Dimensions, ComponentObserver {

	public enum paddleMove {
		unmoved, left, right
	};


	private Board board;
	private int dx;
	private paddleMove hasMoved;
	/**
	 * @return the hasMoved
	 */
	public paddleMove getHasMoved() {
		return hasMoved;
	}

	/**
	 * @param hasMoved the hasMoved to set
	 */
	public void setHasMoved(paddleMove hasMoved) {
		this.hasMoved = hasMoved;
	}

	/**
	 * @return the dx
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * @param dx the dx to set
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	public Paddle(Board board) {
		dx = 0;
		this.board = board;
		hasMoved = paddleMove.unmoved;
		this.setWidth(Dimensions.PADDLE_WIDTH);
		this.setHeight(Dimensions.PADDLE_HEIGHT);
		reset();
	}

	public void reset() {
		// Setting the initial coordinates
		dx = 0;
		this.setX(Dimensions.PADDLE_X);
		this.setY(Dimensions.PADDLE_Y);
	}

	Sprite spriteObj = new Sprite();

	/*
	 * keyPressed()
	 */
	public void KeyPressed() {

	}

	/*
	 * keyReleased()
	 */
	public void KeyReleased() {
		dx = 0;
	}

	/*
	 * move()
	 */
	public void move(String directionToMove) {
		if (directionToMove.equals("left")) {
			//dx = -7;
			hasMoved = paddleMove.left;
		} else if (directionToMove.equals("right")) {
			//dx = 7;
			hasMoved = paddleMove.right;
		} else {
			hasMoved = paddleMove.unmoved;
			//dx = 0;
		}
	}

	public Command getCommand() {
		if (hasMoved == paddleMove.left) {
			// hasMoved = paddleMove.unmoved;
			return new UpdatePaddleLeft(this);
		} else if (hasMoved == paddleMove.right) {
			// hasMoved = paddleMove.unmoved;
			return new UpdatePaddleRight(this);
		} else
			return null;
	}

	/*
	 * paint()
	 */
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		// fil;Rect(int x, int y, int width, int height)
		g.fillRect(250, 480, 80, 5);
	}

	public void checkPaddleCollision() {
		if (this.getX() <= 2) {
			this.setX(2);
		} else if (this.getX() >= Dimensions.PADDLE_RIGHT) {
			this.setX(Dimensions.PADDLE_RIGHT);
		} else if (this.getX() <= Dimensions.PADDLE_LEFT) {
			this.setX(Dimensions.PADDLE_LEFT);
		}
	}

	// updates the x position of the paddle
	@Override
	public void update() {
		this.setX(this.getX() + dx);
		checkPaddleCollision();
		draw(board);
		hasMoved = paddleMove.unmoved;
	}

	@Override
	public void draw(Board com) {
		com.repaint();
	}

	@Override
	public void undo() {
		/*if (hasMoved == paddleMove.left) {
			dx = 7;
		} else if (hasMoved == paddleMove.right) {
			dx = -7;
		} else
			dx = 0;*/
		this.setX(this.getX() + dx);
		//checkPaddleCollision();
		draw(board);
	}
}
