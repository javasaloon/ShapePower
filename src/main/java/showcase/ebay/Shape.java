package showcase.ebay;

import java.util.HashSet;
import java.util.Set;

public abstract class Shape {
	protected float area = 0;

	private PowerGraph powerGraph = null;
	private Set<Shape> directConnections = new HashSet<Shape>();

	public Shape connect(Shape shape) {
		if (shape == null || shape == this || connected(shape)) {
			return this;
		}
		// build bidirectional connection
		this.directConnections.add(shape);
		shape.directConnections.add(this);

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

	protected abstract float calculateArea();

	private boolean connected(Shape shape) {
		// TODO find a better way
		return directConnections.contains(shape);
	}

	void bind(PowerGraph powerGraph) {
		this.powerGraph = powerGraph;
	}

}
