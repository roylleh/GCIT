public class Factorial
{
	public static void main(String[] args)
	{
		double n = 1000;
		
		System.out.println( recursiveFactorial(n) );
		factorial( (int)n );
	}
	
	public static double recursiveFactorial(double n)
	{
		if(n <= 1)
			return 1;
		
		return n * recursiveFactorial(n - 1);
	}
	
	public static void factorial(int n)
	{
		int[] num = new int[n * 4];
		num[0] = 1;
		int digits = 1;
		
		for(int i = 2; i <= n; i++)
			digits = multiply(i, num, digits);
		
		for(int i = digits - 1; i >= 0; i--)
			System.out.print(num[i]);
	}
	
	public static int multiply(int i, int[] num, int digits)
	{
		int carry = 0;
		
		for(int j = 0; j < digits; j++)
		{
			int temp = num[j] * i + carry;
			num[j] = temp % 10;
			carry = temp / 10;
		}
		
		while(carry > 0)
		{
			num[digits] = carry % 10;
			carry /= 10;
			digits++;
		}
		return digits;
	}
}