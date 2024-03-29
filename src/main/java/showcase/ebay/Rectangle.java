package showcase.ebay;

/**
 * 
 * @author Xiang Cheng
 * 
 */
public class Rectangle extends Shape {
	private double width = 0;
	private double height = 0;

	/**
	 * 
	 * @param width
	 * @param height
	 * @throws IllegalArgumentException
	 *             when the input <tt>width</tt> or <tt>height</tt> is not
	 *             larger than 0.
	 */
	public Rectangle(double width, double height) {
		if (width <= 0 || height <= 0) {
			throw new IllegalArgumentException(
					"Both width and height should be larger than 0.");
		}
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return width * height;
	}

}
