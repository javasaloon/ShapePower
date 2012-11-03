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
	private Set<Shape> directConnections = null;
	private PowerGraph powerGraph = null;

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
		if (this.directConnections == null) {
			this.directConnections = new HashSet<Shape>();
		}
		this.directConnections.add(shape); 
		
		if (shape.directConnections == null) {
			shape.directConnections = new HashSet<Shape>();
		}
		shape.directConnections.add(this);
		
		mergePowerGraph(shape);
		
		return this;
	}

	/**
	 * Return its area if it has no connections.
	 * 
	 * @return
	 */
	public float getPower() {
		if (this.directConnections == null || this.directConnections.isEmpty()) {
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
	 * Pick powerGraph whose power is bigger.
	 * 
	 * @param shape
	 */
	private void mergePowerGraph(Shape shape) {
		if (shape == null) {
			return;
		}
		if (this.powerGraph == null) {
			this.powerGraph = new PowerGraph(this);
		}
		if (shape.powerGraph == null) {
			shape.powerGraph = new PowerGraph(shape);
		}
		this.powerGraph.merge(shape.powerGraph);
	}

	/**
	 * Test if the input <tt>shape</tt> has been in the
	 * <tt>directConnections</tt> which means there is a reference to the input
	 * <tt>shape</tt>.
	 * 
	 * @param shape
	 * @return
	 */
	private boolean connected(Shape shape) {
		if (shape == null || this.directConnections == null) {
			return false;
		}
		for (Shape connection : this.directConnections) {
			if (connection == shape) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Bind this shape on the input <tt>powerGraph</tt> which should be not
	 * null.
	 * 
	 * @param powerGraph
	 * @throws NullPointerException
	 *             if input <tt>powerGraph</tt> is null.
	 */
	final void bind(PowerGraph powerGraph) {
		if (powerGraph == null) {
			throw new NullPointerException();
		}
		this.powerGraph = powerGraph;
	}

}
