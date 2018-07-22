public class PosibleSteps {

	private static int[] cache = null;


	public static void main(String [] args){
		int numberOfStairs =  20;
		cache = new int[numberOfStairs + 1];

		int[] steps = {1,2,4};

		long startTime, endTime;

		startTime = System.nanoTime();
		System.out.println(calculatePosibleSteps(numberOfStairs, steps));
		endTime = System.nanoTime();
		System.out.println("Duration:"+ (endTime - startTime));

		startTime = System.nanoTime();
		System.out.println(recursibeFibonashi( numberOfStairs));
		endTime = System.nanoTime();
		System.out.println("Duration:"+ (endTime - startTime));
	}

	public static int calculatePosibleSteps(int n, int[] steps){
		if (n == 0) return 1;
		if (cache[n] != 0){ return  cache[n]; }
		int sum = 0, nextStep;

		for (int step : steps) {
			nextStep = n - step;
			sum += nextStep >= 0 ? calculatePosibleSteps(nextStep, steps) : 0;
		}

		cache[n] = sum;

		return sum;
	}

	public static int recursibeFibonashi(int n){
		if (n == 0 || n == 1) return 1;
		if (n < 0) return  0;
		return  recursibeFibonashi(n-1) + recursibeFibonashi(n-2) + recursibeFibonashi(n-4);
	}
}

/**
 *
 *  1 +0 = 1; +1;
 *  		1 +1 = 2;
 *  	 	1 +2= 3;
 *
 *  2 + 0 =2; +1;
 *  3 + - =3; +1;
 */
