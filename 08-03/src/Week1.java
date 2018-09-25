import java.util.Arrays;
import java.util.List;

public class Week1
{
	public static void main(String[] args)
	{
		List<Integer> intArray = Arrays.asList(-121, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 121);
		List<String> stringArray = Arrays.asList("ax", "bb", "cx", "xxax", "xbxbx", "xxcx", "x");



		//Assignment 1: Lambdas
		intArray.stream().forEach( i -> System.out.format("%d is %s!%n", i, isOdd(i) ? "odd" : "even") );
		System.out.println();

		intArray.stream().forEach( i -> System.out.format("%d is %s!%n", i, isPrime(i) ? "prime" : "not prime") );
		System.out.println();

		intArray.stream().forEach( i -> System.out.format("%d is %s!%n", i, isPalindrome(i) ? "a palindrome" : "not a palindrome") );
		System.out.println();

		intArray.stream().forEach( i -> System.out.format("%d is %s!%n", i, isPalindrome(i) ? "a palindrome" : "not a palindrome") );
		System.out.println();



		//Assignment 2-4: Functional
		intArray.stream().forEach( i -> System.out.println(i % 10) );
		System.out.println();

		intArray.stream().forEach( i -> System.out.println(i * 2) );
		System.out.println();

		stringArray.stream().forEach( i -> System.out.println(removeX(i)) );
		System.out.println();



		//Assignment 5: Recursion
		int arr1[] = {2, 4, 8};
		int arr2[] = {1, 2, 4, 8, 1};
		int arr3[] = {2, 4, 4, 8};

		System.out.println( groupSumClump(arr1, arr1.length, false, 10) );
		System.out.println( groupSumClump(arr2, arr2.length, false, 14) );
		System.out.println( groupSumClump(arr3, arr3.length, false, 14) );
	}

	public static boolean isOdd(int n)
	{
		return n % 2 != 0;
		//O(1)
	}

	public static boolean isPrime(int n)
	{
		if(n == 2)
			return true;

		if(n <= 1 || n % 2 == 0)
			return false;

		for(int i = 3; i * i <= n; i += 2)
			if(n % i == 0)
				return false;

		return true;
		//O( sqrt(n) )
	}

	public static boolean isPalindrome(int n)
	{
		if(n == 0)
			return true;

		if(n < 0 || n % 10 == 0)
			return false;

		long temp = 0;
		while(n > temp)
		{
			temp = temp * 10 + n % 10;
			n /= 10;

			if(temp < Integer.MIN_VALUE || temp > Integer.MAX_VALUE)
				return false;
		}

		return n == temp || n == temp / 10;
		//O( log10(n) )
	}

	public static String removeX(String s)
	{
		String s2 = "";

		for(int i = 0; i < s.length(); i++)
			if(s.charAt(i) != 'x')
				s2 += s.charAt(i);

		return s2;
		//O(n)
	}

	public static boolean groupSumClump(int[] arr, int sz, boolean group, int target_sum)
	{
		if(target_sum == 0)
			return true;

		if(target_sum < 0 || sz <= 0)
			return false;

		if( sz > 1 && arr[sz - 2] == arr[sz - 1] )
			return groupSumClump( arr, sz - 1, true, target_sum - arr[sz - 1] );
		else if(group)
			return groupSumClump( arr, sz - 1, false, target_sum - arr[sz - 1] );

		return groupSumClump( arr, sz - 1, false, target_sum - arr[sz - 1] ) || groupSumClump( arr, sz - 1, false, target_sum );
		//O(2^n)
		//Iterative DP O(n^2) Solution Possible
	}
}