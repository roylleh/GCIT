public class Triangle implements Shape
{
	private int base;
	private int height;

	public Triangle(int base, int height)
	{
		this.base = base;
		this.height = height;
	}

	@Override
	public double calculateArea()
	{
		return 0.5 * base * height;
	}

	@Override
	public void display()
	{
		System.out.println("I'm a triangle with area " + calculateArea() );
	}
}