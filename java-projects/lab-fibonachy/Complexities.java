import java.util.Random;

public class Complexities {

	// *******************************"1"************************************************
	// constant iterative "BigO(1)"
	public static double Iconstant() {
		return 1 / 4 + 444;

	}

	// recursive"BigO(1)"
	public static double Rconstant(int n) {
//		if (n < 0 || n >=10)
//			throw new IllegalArgumentException(
//					"Please enter a positive nuber x > 0");
		if (n == 10)
			return 0;
		else
			return n + Rconstant(n + 1);

	}

	// ********************************N************************************************
	// Linear Iterative
	public static double Ilinear(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		double sum = 0;
		for (int i = 1; i < n; i++) {
			sum += i / 2 + i;
		}
		return sum;

	}

	// Linear recursive T(N)=T(N-1)+1
	public static double Rlinear(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		if (n == 1) {
			return 1;
		} else {
			return (n / 2 + n) + Rlinear(n - 1);
		}
	}

	// ***********************************N^2*********************************************
	// N^2 Iterative
	public static int Isquare(int[][] x) {
		if (x.length == 0)
			throw new IllegalArgumentException("The matrix is empty ");
		int sum = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				sum += x[i][j];
			}
		}
		return sum;
	}

	// N^2 Recursive T(N)=2T(N+1)+1
	public static int Rsquare(int[][] x, int i, int j) {
		if (x.length == 0)
			throw new IllegalArgumentException("The matrix is empty ");
		if (i == x.length)
			return 0;
		if (j == x[i].length)
			return Rsquare(x, i + 1, j = 0);
		else
			return x[i][j] + Rsquare(x, i, j + 1);

	}

	// ******************************N^3**************************************************
	// N^3 Iterative
	public static int[][] IMatrixmultiplication(int[][] x, int[][] y) {
		if (x.length != y[0].length)
			throw new IllegalArgumentException(
					"The matrixes do not have the correct dimentcions to multiply ");

		int[][] c = new int[x.length][y[0].length];

		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y[0].length; j++) {
				for (int k = 0; k < x[0].length; k++) {
					c[i][j] += x[i][k] * y[k][j];
				}
			}
		}
		return c;
	}

	// N^3 REcursive "T(N) = 3T(n+1) + 1
	public static int[][] RMatrixmultiplication(int[][] x, int[][] y,
			int[][] c, int i, int j, int k) {
		if (i == x.length)
			return c;
		if (j == y[0].length)
			return RMatrixmultiplication(x, y, c, i + 1, j = 0, k);
		if (k == x[0].length)
			return RMatrixmultiplication(x, y, c, i, j + 1, k = 0);
		else {
			c[i][j] += x[i][k] * y[k][j];
			return RMatrixmultiplication(x, y, c, i, j, k + 1);
		}
	}

	// Helper
	public static int[][] RMatrixmultiplication(int[][] x, int[][] y) {
		if (x.length != y[0].length)
			throw new IllegalArgumentException("The matrix is empty ");
		int[][] c = new int[x.length][y[0].length];
		return RMatrixmultiplication(x, y, c, 0, 0, 0);

	}

	// ***********************************logN*********************************************
	// logN iterative
	public static int ILog(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		int sum = 0;
		for (int i = 1; i < n; i += i) {
			sum += i;
		}
		return sum;
	}

	// logN Recursive "T(N) = T(N/2) + 1
	public static int RLog(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		if (n == 0)
			return 0;
		else
			return 0 + RLog(n / 2);
	}

	// *************************************NLogN***
	// NLogN Iterative
	public static void INlogn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j += j) {
				// System.out.println("");
			}
		}
	}

	// NLogN Recursive "T(N) = 2T(n/2) + n"
	public static void RNlogn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		if (n == 0) {
			// System.out.println("l");
		} else {
			RNlogn(n / 2);
			// System.out.println("l");
			RNlogn(n / 2);
		}

		for (int i = 0; i < n; i++) {
			// System.out.printl("");
		}
	}

	// ***********************************N^2LogN*********************************************
	// N^2LogN Iterative
	public static void IN2logn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 1; k < n; k = k + k) {
					// System.out.println();
				}
			}
		}
	}

	// N^2LogN Recursive "T(N) = 4T(n/2) + n^2"
	public static void RN2logn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		if (n > 0) {
			for (int i = 0; i < 4; i++) {
				RN2logn(n / 2);
				// System.out.println(n);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n;) {
				j++;
			}
		}
	}

	// ************************************N^3LogN*****
	// N^3LogN Iterative
	public static int IN3logn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		int sum = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					for (int c = n; c > 0; c = c / 2) {
						// sum+=1;
					}
				}
			}
		}
		return sum;
	}

	// N^3LogN Recursive "T(N) = 8T(n/2) + n^3"
	public static void RN3logn(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");

		if (n > 0)
			for (int i = 0; i < 8; i++) {
				RN2logn(n / 2);
				// System.out.println(n);
			}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n;) {
					k++;
				}
			}
		}

	}

	// *************************************2^n*******************************************
	// 2^n iterative
	// because of time issues i crate a simple method
	public static void I2n(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		for (int i = 0; i < Math.pow(2, n); i++) {
			// System.out.println("2^n");
		}
	}

	// 2^n recursive
	// fibonachi recursive
	public static int R2n(int n) {
		if (n < 0)
			throw new IllegalArgumentException(
					"Please enter a positive nuber x > 0");
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return R2n(n - 2) + R2n(n - 1);
		}
	}

	// ********************************************************************************

	/*
	 * help to print the 2 dimensional arrays , to proof they are correct.
	 */

	public static void print(int[][] x) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[i].length; j++) {
				System.out.print(x[i][j] + "\t");
			}
			System.out.println("");
		}
	}

	/*
	 * TimeAverage is used for testing it computers a method 10 times and add
	 * the time for each of the iteration
	 * 
	 * @return sum/10 sum is the addition of the iteration and 10 the ten times
	 * , returns the average time .
	 */
	public static double TimeAverage() {
		double sum = 0;
		long time;
		// //int[][] matrix = { { 15, 59, 6, 4, 5, 6, 7, 8 },
		// { 9, 32, 43, 3, 87, 65, 32, 23 }, { 15, 59, 6, 4, 5, 6, 7, 8 },
		// { 9, 32, 43, 3, 87, 65, 32, 23 }, { 15, 59, 6, 4, 5, 6, 7, 8 },
		// { 9, 32, 43, 3, 87, 65, 32, 23 }, { 15, 59, 6, 4, 5, 6, 7, 8 },
		// { 9, 32, 43, 3, 87, 65, 32, 23 } };
		// int[][] a = new int[6][4];
		// int[][] b = new int[6][6];

		int[] nln = new int[10000];
		nln = fill(nln);

		for (int i = 0; i < 10; i++) {
			time = System.nanoTime();

			// method
			IN3logn(100);
			sum += System.nanoTime() - time;
		}
		return sum / 10;
	}

	// helps to test the Array.sort in method N^3LogN filling a array with
	// random numbers
	public static int[] fill(int[] x) {
		Random r = new Random();
		for (int i = 0; i < x.length; i++) {
			x[i] = r.nextInt(100);
		}
		return x;
	}

	public static void main(String[] args) {
		long time;

		System.out.println("******************Constant**********************");
		System.out.println("");
		time = System.nanoTime();
		System.out.print(Iconstant());// "20"time average 1000.0M-Time"200" time
										// average 4300.0M-Time 2000 time
										// average 55400.0.0M
		//System.out.println(" -> The time for constant  iterative is   "
				//+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		System.out.print(Rconstant(1));// "20"time average 3400.0M-Time"200"
										// time average 27600.0M-Time 2000 time
										// average 211900.0M
		System.out.println(" -> The time for constant  Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************Linear**********************");
		time = System.nanoTime();
		System.out.print(Ilinear(10));// 10-time 700.0--100-time 2300--1000-time
										// 24700
		System.out.println(" ->  The time for Linear   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		System.out.print(Rlinear(10));// 10-time 1200--100-time 12200--1000-time
										// 88200
		System.out.println(" ->  The time for Linear   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************square**********************");
		time = System.nanoTime();
		int[][] matrix = { { 15, 59, 26, 87 }, { 9, 32, 43, 3 } };
		System.out.print(Isquare(matrix));// 2*8 time 1200 - 4*8 time 1300 - 8*8
											// time 2600
		System.out.println(" ->  The time for Square   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		System.out.print(Rsquare(matrix, 0, 0));// 2*8 time 3200 - 4*8 3800 -
												// 8*8 time 9300
		System.out.println(" ->  The time for Square   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************Cubic**********************");

		int[][] a = new int[3][3];
		int[][] b = new int[3][3];

		a[0][0] = 1;
		a[1][0] = 4;
		a[2][0] = 6;
		a[0][1] = 1;
		a[1][1] = 4;
		a[2][1] = 6;
		a[0][2] = 1;
		a[1][2] = 4;
		a[2][2] = 6;
		b[0][0] = 2;
		b[0][1] = 5;
		b[0][2] = 7;
		b[1][0] = 3;
		b[1][1] = 8;
		b[1][2] = 9;
		b[2][0] = 3;
		b[2][1] = 8;
		b[2][2] = 9;
		time = System.nanoTime();
		IMatrixmultiplication(b, a);// [3*2-3*3] time 1900 -[6*4-6*6] time 9500
		System.out.println(" ->  The time for Cubic   iterative is   "
				+ (System.nanoTime() - time) + "  Miliseconds");
		time = System.nanoTime();
		RMatrixmultiplication(b, a);// [3*2-3*3] time 6800-[6*4-6*6] time 19100
		System.out.println(" ->  The time for Cubic   Recursive is   "
				+ (System.nanoTime() - time) + "  Miliseconds");
		// print(l);//testing purposes
		// print(k);//testing purposes

		System.out.println("******************LogN**********************");
		time = System.nanoTime();
		ILog(1000);// 10 time 7000 -100 time 8000 -1000 time 9500
		System.out.println(" ->  The time for Log   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		RLog(1000);// 10 time 4000.0 -100 time 5000 -1000 time 7000
		System.out.println(" ->  The time for Log   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************NLogN**********************");
		time = System.nanoTime();
		INlogn(1000);// 10 time 9000-100 time 12000 -1000 169000
		System.out.println(" ->  The time for NLogN   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		RNlogn(1000);// 10 time 40000-100 time 67000 -1000
						// 306000
		System.out.println(" ->  The time for NLogN   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************N^2LogN**********************");
		time = System.nanoTime();
		IN2logn(10);// 10 time 10000-100 time 779000 -1000 4814000
		System.out.println(" ->  The time for N^2LogN   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		RN2logn(10);// 10 time 35000-100 time 2175000 -1000 5856000
		System.out.println(" ->  The time for N^2LogN   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************N^3LogN**********************");
		time = System.nanoTime();
		IN3logn(1000);// 10 time 103000--100 time 10784000-1000
						// time 8542524000
		System.out.println(" ->  The time for N^3LogN   iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		RN3logn(1000);// 10 time 84000-100 time 4045000 -1000 time 335364000

		System.out.println(" ->  The time for N^3LogN   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

		System.out.println("******************2^N**********************");
		time = System.nanoTime();
		I2n(10);// 10 time 684000 -24 time 6501000 -35 time NA time
		System.out.println(" ->  The time for 2^N  iterative is   "
				+ (System.nanoTime() - time) + "    Miliseconds");
		time = System.nanoTime();
		R2n(10);// 10 time 16000 - 24 1986000 -35 time NA

		System.out.println(" ->  The time for 2^N   Recursive is   "
				+ (System.nanoTime() - time) + "    Miliseconds");

	}

}
