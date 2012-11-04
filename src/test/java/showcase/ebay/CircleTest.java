package showcase.ebay;

import static org.junit.Assert.*;
import static showcase.ebay.TestHelper.*;

import org.junit.Test;

/**
 * 
 * @author Xiang Cheng
 * 
 */
public class CircleTest {
	@Test(expected = IllegalArgumentException.class)
	public void testCircle() {
		new Circle(-1);
	}

	@Test
	public void testGetArea() {
		Circle circle = new Circle(3);
		assertEquals("The shape area should equals to the expected. ",
				3 * 3 * Math.PI, circle.getArea(), DELTA);
	}

	@Test
	public void testGetPowerEqualsArea() {
		Circle circle = new Circle(3);
		assertEquals("The shape power should equals to the expected. ",
				circle.getArea(), circle.getPower(), DELTA);
	}

	@Test
	public void testConnectMixShapes() {
		Shape shape1 = getMockShape(1);
		Circle circle3 = new Circle(3);
		Rectangle rectangle15 = new Rectangle(3, 5);

		rectangle15.connect(shape1);
		verifyDirectConnectionCount(1, rectangle15, shape1);
		verifyPower(15, rectangle15);

		rectangle15.connect(circle3);
		verifyDirectConnectionCount(1, circle3, shape1);
		verifyDirectConnectionCount(2, rectangle15);
		verifyPower(3 * 3 * Math.PI, rectangle15, circle3, shape1);
	}

}
