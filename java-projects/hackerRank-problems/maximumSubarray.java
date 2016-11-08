import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class maximumSubarray {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int test_number = sc.nextInt();
		sc.nextLine();
		int number_variables = 0;
		for (int i = 0; i < test_number; i++) {
			number_variables = sc.nextInt();
			sc.nextLine();
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int j = 0; j < number_variables; j++) {
				list.add(sc.nextInt());
			}
			System.out.println(nonContinuousSum(list, list.size(), 0, 0) + " " + continousSum(list));
            if(sc.hasNextLine()){sc.nextLine();}
		}
	}

	public static int continousSum(ArrayList<Integer> list) {
		int max_sum = 0;
		for (int x : list) {
			max_sum += x > 0 ? x : 0;
		}
		return max_sum;
	}

	/* This method is using kedanes */
	public static int nonContinuousSum(ArrayList<Integer> list,int length, int max_sum, int idex){
       int max_so_far = 0;
       int max_ending_here = 0;
         
       for(int x : list){
           max_ending_here = ((max_ending_here + x) < 0) ? 0 :  max_ending_here + x;
           /*max_ending_here = Math.max(max_ending_here + x,max_ending_here));*/
           max_so_far = max_so_far <  max_ending_here ? max_ending_here : max_so_far;
           /*max_so_far = Math.max(max_so_far,max_ending_here);*/   
       }
       return max_so_far;
     }
}