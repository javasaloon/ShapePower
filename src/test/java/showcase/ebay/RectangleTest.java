package showcase.ebay;

import static org.junit.Assert.*;
import static showcase.ebay.TestHelper.*;

import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Xiang Cheng
 * 
 */
public class RectangleTest {

	@Test(expected = IllegalArgumentException.class)
	public void testRectangleIllegalWidth() {
		new Rectangle(-3, 5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRectangleIllegalHeight() {
		new Rectangle(3, -5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testRectangleIllegalHeightWidth() {
		new Rectangle(-3, -5);
	}

	@Test
	public void testGetArea() {
		Rectangle rectangle = new Rectangle(3, 5);
		assertEquals("The shape area should equals to the expected. ", 15,
				rectangle.getArea(), DELTA);
	}

	@Test
	public void testGetPower() {
		Rectangle rectangle = new Rectangle(3, 5);
		assertEquals("The shape power should equals to the expected. ", 15,
				rectangle.getPower(), DELTA);
	}

	@Test
	public void testGetDirectConnections() {
		Rectangle rectangle15 = new Rectangle(3, 5);
		Rectangle rectangle25 = new Rectangle(5, 5);
		Rectangle rectangle30 = new Rectangle(5, 6);

		rectangle15.connect(rectangle30);
		verifyDirectConnectionCount(1, rectangle15, rectangle30);
		List<Shape> connections = rectangle15.getDirectConnections();
		// This should not effect the directConnections of rectangle15
		connections.add(rectangle25); 
		assertEquals("connections should have two items. ", 2,
				connections.size());
		verifyDirectConnectionCount(1, rectangle15, rectangle30);
	}

	@Test
	public void testConnectExternally() {
		Rectangle rectangle15 = new Rectangle(3, 5);
		Rectangle rectangle25 = new Rectangle(5, 5);
		Rectangle rectangle30 = new Rectangle(5, 6);

		rectangle15.connect(rectangle30);
		verifyDirectConnectionCount(1, rectangle15, rectangle30);
		verifyPower(30, rectangle15, rectangle30);
		verifyNullConnection(rectangle25);
		verifyPower(25, rectangle25);

		rectangle15.connect(rectangle25);
		verifyDirectConnectionCount(1, rectangle25, rectangle30);
		verifyDirectConnectionCount(2, rectangle15);
		verifyPower(30, rectangle15, rectangle25, rectangle30);
	}

}
