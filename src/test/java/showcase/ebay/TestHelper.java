package showcase.ebay;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * 
 * @author Xiang Cheng
 * 
 */
public final class TestHelper {
	public static final double DELTA = 0;

	public static void verifyDirectConnectionCount(int expectedCount,
			Shape... shapes) {
		for (Shape shape : shapes) {
			assertEquals(
					"The direct connection count of the shape should be expected. ",
					expectedCount, shape.getDirectConnections().size());
		}
	}

	public static void verifyNullConnection(Shape... shapes) {
		for (Shape shape : shapes) {
			assertNull(
					"The direct connection count of the shape should be expected. ",
					shape.getDirectConnections());
		}
	}

	public static void verifyPower(double expectedPower, Shape... shapes) {
		for (Shape shape : shapes) {
			assertEquals("The shape power should equals to the expected. ",
					expectedPower, shape.getPower(), TestHelper.DELTA);
		}
	}

	public static Shape getMockShape(final double mockArea) {
		Shape shape = new Shape() {
			@Override
			public double getArea() {
				return mockArea;
			}
		};
		return shape;
	}
}
