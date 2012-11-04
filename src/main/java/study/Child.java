package study;

public class Child extends Parent{
	
	private void print() {
		System.out.println("Hello Child");
		
	}
	
	public static void main(String[] args) {
		Child child = new Child();
		child.hello();
	}
}
