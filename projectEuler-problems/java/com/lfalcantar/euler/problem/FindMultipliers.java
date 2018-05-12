package com.lfalcantar.euler.problem;

import java.util.stream.IntStream;

/*Problem_1*/
public class FindMultipliers{

    /**
    * This method will be bigO(n) to find the multiples
     * of two numbers from  to the limit;
    */
    public static int findMultiplesOf(long limit){
        return IntStream.range(1, 1000)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
                .map(i -> i % 3 == 0 && i % 5 == 0 ? 2 * i : i)
                .sum();
    }

    /**
     * This method will be bigO(1) to find the multiples
     * of two numbers from  to the limit;
     *
     * (1/2)n(n+1) This will find the sum of all the multiplers of a number
     *
     */
    public static int findMultiplesOf(int[] multipliers, int limit){
        int interception = 1;
        int sum = 0;

        for(int x : multipliers){
            int upperBound = limit/x;
            sum += ((x * upperBound)*( 1 + upperBound)) / 2;
            interception *=x;
        }

        sum-=((interception * (interception/limit))*( 1 + (interception/limit))) / 2;
        return sum;
    }

    public static void main(String [] args){
        int limit = 999;
        long startTime = System.nanoTime();
        System.out.println("FindSum =" + findMultiplesOf(limit));
        long endTime = System.nanoTime();
        System.out.println("Execution time:" + (endTime - startTime));


        int[] multipliers = {3,5};
        startTime = System.nanoTime();
        System.out.println("FindSum =" + findMultiplesOf(multipliers, limit));
        endTime = System.nanoTime();
        System.out.println("Execution time:" + (endTime - startTime));

    }
}