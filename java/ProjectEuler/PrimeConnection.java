import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class PrimeConnection {

	/* Method to find primes numbers using Sieve of Eratosthenes */
	public static boolean[] findPrimes(boolean[] array) {

		int limit = (int) Math.sqrt(array.length);

		for (int i = 2; i < limit; i++) {
			if (!array[i])
				for (int j = i + 1; j < array.length; j++)
					if (j % i == 0)
						array[j] = true;
		}

		return array;
	}

	public static ArrayList<Integer> primeTestHelper(int lower_bound, int upper_bound) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean[] array = new boolean[upper_bound + 10];

		array = findPrimes(array);

		for (int j = lower_bound; j < array.length; j++) {
			if (!array[j]) {
				list.add(j);
				if (j > upper_bound)
					return list;
			}
		}

		return list;
	}

	public static int findMinDivisor(int x, int y) {

		int a = x;

		String temp = "";
		String temp2 = Integer.toString(x);

		while (true) {
			temp = Integer.toString(a);
			if (a % y == 0 && temp.substring(temp.length() - temp2.length()).compareTo(temp2) == 0) {
				return a;
			}

			a += x < 10 ? 1 : 100;
		}

	}

	public static void main(String[] args) {
		/* Scanner for input */
		Scanner sc = new Scanner(System.in);

		/* First line number of test cases */
		int numTestCases = sc.nextInt();
		sc.nextLine();

		int length = 0, sum = 0;

		long startTimeFor = System.nanoTime();
		/* Traverse the tests cases and get the solutions */
		for (int i = 0; i < numTestCases; i++) {
			/*GET PRIME NUMBERS*/
			long startTimePrime = System.nanoTime();
			ArrayList<Integer> list = primeTestHelper(sc.nextInt(), sc.nextInt());
			long endTimePrime = System.nanoTime();
			System.out.println("The Prime Time:" + (endTimePrime - startTimePrime));
			/*END GET PRIME TIME*/

			length = list.size();
			sum = 0;

			for (int j = 1; j < length; j++) {
				/*GET MIN DIV */
				long startTimeDiv = System.nanoTime();
				sum += findMinDivisor(list.get(j - 1), list.get(j));
				long endTimeDiv = System.nanoTime();
				System.out.println("The Div Time:" + (endTimeDiv - startTimeDiv));
				/*END GET DIV*/

			}

			System.out.println(sum);
		}
		long endTimeFor = System.nanoTime();
		System.out.println("The Test Case Time:" + (endTimeFor - startTimeFor));

	}
}