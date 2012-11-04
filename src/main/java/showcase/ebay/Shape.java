package showcase.ebay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This is an abstract <tt>Shape</tt> class and hope the subclass to implement
 * the abstract method {@link #getArea()}.
 * <p>
 * It offers power of the connection chain through {@link #getPower()} after it
 * connects to other shapes. The power is the biggest area in all its directly
 * or indirectly connected shapes. The power equals to its area provided by
 * {@link #getArea()} before it connects to others.
 * </p>
 * 
 * @author Xiang Cheng
 * 
 */
public abstract class Shape {
	private List<Shape> directConnections = null;
	private PowerGraph powerGraph = null;

	/**
	 * Build bidirectional connection with the input <tt>shape</tt> and merge
	 * their <tt>powerGraph</tt>. Nothing happened if the input <tt>shape</tt>
	 * is null, the same as itself or has connected with it.
	 * 
	 * @param shape
	 * @return <tt>this</tt>
	 * @throws IllegalArgumentException
	 *             when the {@link #getArea()} of the input <tt>shape</tt>
	 *             returns value which is not larger than 0.
	 */

	public final Shape connect(Shape shape) {
		if (shape == null || shape == this || connected(shape)) {
			return this;
		}
		if (shape.getArea() <= 0) {
			throw new IllegalArgumentException(
					"The area of the input shape should be bigger than 0. You may need to check the getArea() method.");
		}
		if (this.directConnections == null) {
			this.directConnections = new ArrayList<Shape>();
		}
		this.directConnections.add(shape);

		if (shape.directConnections == null) {
			shape.directConnections = new ArrayList<Shape>();
		}
		shape.directConnections.add(this);

		mergePowerGraph(shape);

		return this;
	}

	/**
	 * 
	 * @return its area if it has no connections.
	 */
	public final double getPower() {
		if (this.directConnections == null || this.directConnections.isEmpty()) {
			return this.getArea();
		}
		return this.powerGraph.getPower();
	}

	/**
	 * The {@link #directConnections} should not be changed outside this class,
	 * because {@link #powerGraph} should know about the change of
	 * {@link #directConnections}. And the only allowed way to change
	 * {@link #directConnections} is {@link #connect(Shape)}.
	 * 
	 * @return a copy of directConnections
	 */
	public final List<Shape> getDirectConnections() {
		if (directConnections == null) {
			return null;
		}
		return new ArrayList<Shape>(directConnections);
	}

	/**
	 * Return the area of the shape which should be larger than 0.
	 * 
	 * @return
	 */
	protected abstract double getArea();

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
	 * Bind this shape on the input <tt>powerGraph</tt> which should not be
	 * null.
	 * 
	 * @param powerGraph
	 * @throws NullPointerException
	 *             if input <tt>powerGraph</tt> is null.
	 */
	private void bind(PowerGraph powerGraph) {
		if (powerGraph == null) {
			throw new NullPointerException();
		}
		this.powerGraph = powerGraph;
	}

	/**
	 * This is used for <tt>Shape</tt> to help keep and calculate the power.
	 * This class holds all the <tt>Shape</tt> vertices in it and their power
	 * which is the biggest area among the vertices.
	 * 
	 * @author Xiang Cheng
	 * 
	 */
	private static class PowerGraph {
		private double power = 0;
		private List<Shape> vertices = null;

		/**
		 * Construct a new <tt>PowerGraph</tt> with the input <tt>vertex</tt>.
		 * Leave power as 0 and vertices as empty <tt>List</tt> if the input
		 * <tt>vertex</tt> is null. Otherwise, add it into the vertex list and
		 * set its area as the initial power.
		 * 
		 * @param vertex
		 */
		public PowerGraph(Shape vertex) {
			vertices = new ArrayList<Shape>();
			if (vertex != null) {
				this.power = vertex.getArea();
				this.vertices.add(vertex);
			}
		}

		/**
		 * Merge with the input <tt>graph</tt>. Bind all vertices whose
		 * <tt>graph</tt> has smaller area to the merged <tt>graph</tt> which
		 * has bigger area. The <tt>graph</tt> which has smaller area would be
		 * collected by GC because these is no reference to it after the merge.
		 * Return <tt>this</tt> directly if the input <tt>graph</tt> is null or
		 * the same as <tt>this</tt>.
		 * 
		 * @param graph
		 * @return merged graph with all vertices of both graphs.
		 */
		public PowerGraph merge(PowerGraph graph) {
			if (graph == null || graph == this) {
				return this;
			}
			if (graph.power > this.power) {
				graph.bind(this.vertices);
				return graph;
			} else {
				this.bind(graph.vertices);
				return this;
			}
		}

		private void bind(Collection<? extends Shape> newVertices) {
			if (newVertices == null) {
				return;
			}
			for (Shape vertex : newVertices) {
				vertex.bind(this);
				this.vertices.add(vertex);
			}
		}

		/**
		 * 
		 * @return the power which is the biggest area among the vertices.
		 */
		public double getPower() {
			return this.power;
		}
	}
}
