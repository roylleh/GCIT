public class Rectangle implements Shape
{
	private int base;
	private int height;

	public Rectangle(int base, int height)
	{
		this.base = base;
		this.height = height;
	}

	@Override
	public double calculateArea()
	{
		return base * height;
	}

	@Override
	public void display()
	{
		System.out.println("I'm a rectangle with area " + calculateArea() );
	}
}