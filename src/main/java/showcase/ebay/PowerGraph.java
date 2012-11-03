package showcase.ebay;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This is used for <tt>Shape</tt> to help keep and calculate the power. This
 * class holds all the <tt>Shape</tt> vertices in it and their power which is
 * the biggest area among the vertices.
 * 
 * @author Xiang Cheng
 * 
 */
class PowerGraph {
	private float power = 0;
	private Set<Shape> vertices = null;

	/**
	 * Construct a new <tt>PowerGraph</tt> with the input <tt>vertex</tt>. Leave
	 * power as 0 and vertices as empty <tt>Set</tt> if the input
	 * <tt>vertex</tt> is null. Otherwise, add it into the vertex set and set
	 * its area as the initial power.
	 * 
	 * @param vertex
	 */
	public PowerGraph(Shape vertex) {
		super();
		vertices = new HashSet<Shape>();
		if (vertex != null) {
			this.power = vertex.getArea();
			this.vertices.add(vertex);
		}
	}

	/**
	 * Merge with the input <tt>graph</tt>. Bind all vertices whose
	 * <tt>graph</tt> has smaller area to the merged <tt>graph</tt> which has
	 * bigger area. The <tt>graph</tt> which has smaller area would be gc
	 * because these is no reference to it after the merge.
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

	private void bind(Collection<? extends Shape> vertices2) {
		if (vertices2 == null) {
			return;
		}
		for (Shape vertex : vertices2) {
			vertex.bind(this);
			this.vertices.add(vertex);
		}
	}

	/**
	 * 
	 * @return the power which is the biggest area among the vertices.
	 */
	public float getPower() {
		return this.power;
	}

}
