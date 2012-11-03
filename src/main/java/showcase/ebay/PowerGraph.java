package showcase.ebay;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class PowerGraph {
	private float power = 0.0f;
	private Set<Shape> vertices = null;

	public PowerGraph(Shape vertex) {
		super();
		vertices = new HashSet<Shape>();
		this.power = vertex.getArea();
		this.vertices.add(vertex);
	}

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

	private void bind(Collection<? extends Shape> vertices2) {
		for (Shape shape : vertices2) {
			bind(shape);
		}
	}

	private void bind(Shape vertex) {
		vertex.bind(this);
		this.vertices.add(vertex);
	}

	public float getPower() {
		return this.power;
	}

}
