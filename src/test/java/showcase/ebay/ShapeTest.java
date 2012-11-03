package showcase.ebay;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShapeTest {
	private static final double DELTA = 0;

	@Test
	public void testConnectNull() {
		Shape shape1 = getMockShape(1);
		verifyDirectConnectionCount(0, shape1);
		verifyPower(1, shape1);
		Shape connectedShape = shape1.connect(null);
		assertEquals("shape.connect(null) should ignore silently", shape1,
				connectedShape);
		verifyDirectConnectionCount(0, shape1);
		verifyPower(1, shape1);
	}

	@Test
	public void testConnectItself() {
		Shape shape1 = getMockShape(1);
		Shape connectedShape = shape1.connect(shape1);
		assertEquals("shape.connect(itself) should return itself", shape1,
				connectedShape);
		verifyDirectConnectionCount(0, shape1);
		verifyPower(1, shape1);
	}

	@Test
	public void testConnectRepeatly() {
		Shape shape1 = getMockShape(1);
		Shape shape2 = getMockShape(2);
		shape1.connect(shape2);
		verifyDirectConnectionCount(1, shape1, shape2);
		verifyPower(2, shape1, shape2);
		
		shape1.connect(shape2);
		verifyDirectConnectionCount(1, shape1, shape2);
		verifyPower(2, shape1, shape2);

		shape2.connect(shape1);
		verifyDirectConnectionCount(1, shape1, shape2);
		verifyPower(2, shape1, shape2);
	}

	@Test
	public void testConnectInternally() {
		Shape shape1 = getMockShape(1);
		Shape shape2 = getMockShape(2);
		Shape shape3 = getMockShape(3);
		shape1.connect(shape2); 
		shape2.connect(shape3);
		verifyDirectConnectionCount(1, shape1, shape3);
		verifyDirectConnectionCount(2, shape2);
		verifyPower(3, shape1, shape2, shape3);

		// test connect to each other
		shape3.connect(shape1);
		verifyPower(3, shape1, shape2, shape3);
		verifyDirectConnectionCount(2, shape1, shape2, shape3);
		verifyPower(3, shape1, shape2, shape3);
		
		shape1.connect(shape3);
		verifyDirectConnectionCount(2, shape1, shape3);
		verifyDirectConnectionCount(2, shape1, shape2, shape3);
		verifyPower(3, shape1, shape2, shape3);
	} 
	@Test
	public void testConnectExternally() {
		Shape shape1 = getMockShape(1);
		Shape shape2 = getMockShape(2);
		Shape shape3 = getMockShape(3);
		shape1.connect(shape2); 
		shape2.connect(shape3); 
		verifyDirectConnectionCount(1, shape1, shape3); 
		verifyDirectConnectionCount(2, shape2); 
		verifyPower(3, shape1, shape2, shape3); 
		
	}
	
	

	@Test
	public void testGetPower() {
		Shape shape = getMockShape(1);
		assertEquals("Single shape power should equals to its area", 1,
				shape.getPower(), DELTA);

	}

	@Test
	public void testSampleCase() {
		Shape shapeA = getMockShape(10);
		Shape shapeB = getMockShape(15);
		Shape shapeC = getMockShape(30);
		Shape shapeD = getMockShape(5);
		shapeA.connect(shapeB);
		shapeB.connect(shapeC);
		shapeC.connect(shapeA);
		shapeD.connect(shapeC);
		verifyPower(30, shapeA, shapeB, shapeC, shapeD);
	}

	private void verifyDirectConnectionCount(int expectedCount, Shape... shapes) {
		for (Shape shape : shapes) {
			assertEquals(
					"The direct connection count of the shape should be expected. ",
					expectedCount, shape.getDirectConnections().size());
		}
	}

	private void verifyPower(float expectedPower, Shape... shapes) {
		for (Shape shape : shapes) {
			assertEquals("The shape power should equals to the expected. ",
					expectedPower, shape.getPower(), DELTA);
		}
	}

	private Shape getMockShape(final float mockArea) {
		Shape shape = new Shape() {
			@Override
			public float calculateArea() {
				this.area = mockArea;
				return this.area;
			}
		};
		// TODO How to enforce this
		shape.calculateArea();
		return shape;
	}

}
