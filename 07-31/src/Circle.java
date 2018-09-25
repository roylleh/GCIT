public class Circle implements Shape
{
	private int radius;

	public Circle(int radius)
	{
		this.radius = radius;
	}

	@Override
	public double calculateArea()
	{
		return Math.PI * radius * radius;
	}

	@Override
	public void display()
	{
		System.out.println("I'm a circle with area " + calculateArea() );
	}
}