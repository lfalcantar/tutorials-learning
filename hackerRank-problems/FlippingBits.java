import java.util.Scanner;

public class FlippingBits {

	public static void main(String [] args){
	   Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        long mask = (1L<<32) - 1 ;

        for(int i= 0; i < num; i++) {
            long number = sc.nextLong();
            System.out.println(number^mask);
        }
        
        sc.close();
        
	}
}
