import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day5
{
	public static void main(String[] args)
	{
		//Comparators
		List<String> stringArray = Arrays.asList("e", "the", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog", "e");

		Comparator<String> length = (s1, s2) -> s1.length() - s2.length();
		Comparator<String> reverseLength = (s1, s2) -> s2.length() - s1.length();
		Comparator<String> abc = (s1, s2) -> s1.charAt(0) - s2.charAt(0);
		Comparator<String> e = (s1, s2) -> s1.charAt(0) == 'e' ? -1 : 0;

		stringArray.sort(length);
		System.out.println(stringArray);

		stringArray.sort(reverseLength);
		System.out.println(stringArray);

		stringArray.sort(abc);
		System.out.println(stringArray);

		stringArray.sort(e);
		System.out.println(stringArray);



		//Lambdas
		List<Integer> intArray = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		System.out.println();
		intArray.stream().forEach( i -> System.out.format("%s%d, ", i % 2 == 0 ? "e" : "o", i) );



		//Filters
		List<String> stringArray2 = Arrays.asList("a", "b", "c", "abc", "app");

		System.out.format("%n%n");
		stringArray2.stream().filter(s -> s.charAt(0) == 'a' && s.length() == 3).forEach( s -> System.out.print(s + ", ") );



		//Date and Time
		Instant instant = Instant.now();
		ZonedDateTime zonedDateTime = instant.atZone( ZoneId.of("America/New_York") );

		System.out.format("%n%n");
		System.out.println(instant);
		System.out.println(zonedDateTime);
		System.out.println(zonedDateTime.with( TemporalAdjusters.previous(DayOfWeek.THURSDAY) ));
		System.out.println();

		int year = 2018;
		for(int i = 1; i <= 12; i++)
		{
			YearMonth yearMonth = YearMonth.of(year, i);
			System.out.format( "%s: %d days%n", yearMonth.toString(), yearMonth.lengthOfMonth() );
		}

		if(zonedDateTime.getDayOfWeek() == DayOfWeek.FRIDAY && zonedDateTime.getDayOfMonth() == 13)
			System.out.format("%nToday is Friday the 13th!%n");
	}
}