import java.util.Scanner;

public class Triangle
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		int triangle = getPositive(in, "Triangle: ");
		int lines = getPositive(in, "Lines: ");
		
		in.close();
		printTriangle(triangle, lines);
	}
	
	public static int getPositive(Scanner in, String s)
	{	
		int n = 0;
		while(n <= 0)
		{
			System.out.print(s);
			while( !in.hasNextInt() )
			{
				System.out.print(s);
				in.next();
			}
			n = in.nextInt();
		}
		
		System.out.println("");
		return n;
	}
	
	public static void printTriangle(int triangle, int lines)
	{
		if(triangle == 1)
		{
			for(int i = 1; i <= lines; i++)
			{
				for(int j = 1; j <= i; j++)
					System.out.print("*");
				
				System.out.println();
			}
		}
		else if(triangle == 2)
		{
			for(int i = lines; i >= 1; i--)
			{
				for(int j = i; j >= 1; j--)
					System.out.print("*");
				
				System.out.println();
			}
		}
		else if(triangle == 3)
		{
			for(int i = 1; i <= lines; i++)
			{
				for(int j = 1; j <= lines - i; j++)
					System.out.print(" ");
				for(int j = 1; j <= i * 2 - 1; j++)
					System.out.print("*");
				for(int j = 1; j <= lines - i; j++)
					System.out.print(" ");
				
				System.out.println();
			}
		}
		else if(triangle == 4)
		{
			for(int i = lines; i >= 1; i--)
			{
				for(int j = lines - i; j >= 1; j--)
					System.out.print(" ");
				for(int j = i * 2 - 1; j >= 1; j--)
					System.out.print("*");
				for(int j = lines - i; j >= 1; j--)
					System.out.print(" ");
				
				System.out.println();
			}
		}
		else if(triangle == 5)
		{
			for(int i = 1; i <= lines; i++)
			{
				for(int j = 1; j <= lines - i; j++)
					System.out.print(" ");
				
				int temp = 1;
				for(int j = 1; j <= i; j++)
				{
					System.out.print(temp + " ");
					temp = temp * (i - j) / j;
				}
				
				for(int j = 1; j <= lines - i; j++)
					System.out.print(" ");
				
				System.out.println();
			}
		}
	}
}