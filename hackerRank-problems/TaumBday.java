mport java.math.BigInteger;
import java.util.Scanner;

public class TaumBday {

    public static String calculateCost(BigInteger b,BigInteger w,BigInteger x,BigInteger y,BigInteger z){
        BigInteger result = null;
        BigInteger temp = null;
        

        if(x.compareTo(z) > 0 && x.compareTo(z.add(y)) > 0){
            /*Formula:
                (Y+Z)B + W(Y) = Cost*/
            temp = y.add(z);
            temp = temp.multiply(b);
            result = w.multiply(y);
            result = result.add(temp);
        }
        else if(y.compareTo(z) > 0 && y.compareTo(x.add(z)) > 0){
            /*Formula:
                (X+Z)w + B(X) = Cost*/
            temp = x.add(z);
            temp = temp.multiply(w);
            result = b.multiply(x);
            result = result.add(temp);
        }
        else{
            temp = x.multiply(b);
            result = y.multiply(w);
            result = temp.add(result);
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            BigInteger  b = in.nextBigInteger();
            BigInteger  w = in.nextBigInteger();
            BigInteger  x = in.nextBigInteger();
            BigInteger  y = in.nextBigInteger();
            BigInteger  z = in.nextBigInteger();
            
            System.out.println(calculateCost(b,w,x,y,z)); 
        }
    }
}

