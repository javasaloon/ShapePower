package showcase.ebay;

import java.util.HashSet;
import java.util.Set;

/**
 * This is an abstract <tt>Shape</tt> class and hope the subclass to implement
 * the abstract method {@link #calculateArea()}
 * 
 * @author Xiang Cheng
 * 
 */
public abstract class Shape {
	protected float area = 0;

	private PowerGraph powerGraph = null;
	private Set<Shape> directConnections = new HashSet<Shape>();

	/**
	 * Build bidirectional connection with the input <tt>shape</tt>. And merge
	 * their <tt>powerGraph</tt>. Nothing happened if the input <tt>shape</tt>
	 * is null, the same as itself or has connected with it.
	 * 
	 * @param shape
	 * @return itself
	 */
	public Shape connect(Shape shape) {
		if (shape == null || shape == this || connected(shape)) {
			return this;
		}
		// build bidirectional connection
		this.directConnections.add(shape);
		shape.directConnections.add(this);

		// TODO optimize
		if (this.powerGraph == null) {
			this.powerGraph = new PowerGraph(this);
		}
		if (shape.powerGraph == null) {
			shape.powerGraph = new PowerGraph(shape);
		}
		 
		this.powerGraph.merge(shape.powerGraph);

		return this;
	}

	public float getPower() {
		if (this.powerGraph == null) {
			return this.getArea();
		}
		return this.powerGraph.getPower();
	}

	public float getArea() {
		return this.area;
	}

	public Set<Shape> getDirectConnections() {
		return directConnections;
	}

	/**
	 * Concrete <tt>Shape</tt> class should provide its implementation
	 * accordingly.
	 * 
	 * @return the area value.
	 */
	protected abstract float calculateArea();

	/**
	 * Test if the input <tt>shape</tt> has been in the
	 * <tt>directConnections</tt> which means there is a reference to the input
	 * <tt>shape</tt>.
	 * 
	 * @param shape
	 * @return
	 */
	private boolean connected(Shape shape) {
		for (Shape connection : directConnections) {
			if (connection == shape) {
				return true;
			}
		}
		return false;
	}

	void bind(PowerGraph powerGraph) {
		this.powerGraph = powerGraph;
	}

}
