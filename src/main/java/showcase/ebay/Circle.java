package showcase.ebay;

/**
 * 
 * @author Xiang Cheng
 * 
 */
public class Circle extends Shape {
	private double radius = 0;

	/**
	 * 
	 * @param radius
	 * @throws IllegalArgumentException
	 *             when the input <tt>radius</tt> is not larger than 0.
	 */
	public Circle(double radius) {
		if (radius <= 0) {
			throw new IllegalArgumentException(
					"Radius should be larger than 0.");
		}
		this.radius = radius;
	}

	@Override
	public double getArea() {
		return radius * radius * Math.PI;
	}
}
