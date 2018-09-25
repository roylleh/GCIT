import java.util.Random;

public class Day2
{	
	public static void main(String[] args)
	{
		System.out.print("Arguments: ");
		int total = 0;

		for(String s : args)
		{
			try
			{
				System.out.print(s + " ");
				int i = Integer.parseInt(s);
				total += i;
			}
			catch(NumberFormatException er)
			{
				//return;
			}

		}

		System.out.format("%n%nResult: " + total);
		System.out.format("%n%n%n");



		int m = 10;
		int n = 10;
		int[][] num = new int[m][n];
		Random rand = new Random();

		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				num[i][j] = rand.nextInt(100) + 1;

		int maxNum = 0;
		int maxRow = 0;
		int maxCol = 0;

		for(int i = 0; i < m; i++)
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(num[i][j] + "\t");
				if(num[i][j] > maxNum)
				{
					maxNum = num[i][j];
					maxRow = i;
					maxCol = j;
				}
			}
			System.out.println();
		}

		System.out.format("%nResult: %d at (%d, %d)%n%n%n", maxNum, maxRow, maxCol);



		Circle shape1 = new Circle(1);
		Triangle shape2 = new Triangle(2, 3);
		Rectangle shape3 = new Rectangle(4, 5);

		shape1.display();
		shape2.display();
		shape3.display();
	}
}