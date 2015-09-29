

public class Brick extends Sprite {

	private boolean isDestroyed;
	private boolean isDestroyedInReplay;

	/**
	 * @return the isDestroyed
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * @param isDestroyed the isDestroyed to set
	 */
	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * @return the isDestroyedInReplay
	 */
	public boolean isDestroyedInReplay() {
		return isDestroyedInReplay;
	}

	/**
	 * @param isDestroyedInReplay the isDestroyedInReplay to set
	 */
	public void setDestroyedInReplay(boolean isDestroyedInReplay) {
		this.isDestroyedInReplay = isDestroyedInReplay;
	}

	public Brick() {
		isDestroyed = false;
		this.setWidth(Dimensions.BRICK_WIDTH);
		this.setHeight(Dimensions.BRICK_HEIGHT);
		reset();
	}

	public void reset() {
		isDestroyed = false;

		this.setX(Dimensions.BRICK_X);
		this.setY(Dimensions.BRICK_Y);

		// Setting the height and width of the brick
		this.setHeight(Dimensions.BRICK_HEIGHT);
		this.setWidth(Dimensions.BRICK_WIDTH);
	}

}
