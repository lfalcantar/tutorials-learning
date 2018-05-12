package com.lfalcantar.euler.problem;

import java.util.stream.IntStream;

/*Problem_1*/
public class FindMultipliers{

    /**
    * This method will be bigO(n) to find the multiples
     * of two numbers from  to the limit;
    */
    public static int findMultiplesOf(int limit){
        return IntStream.range(1,limit)
                .filter(i -> i % 3 == 0 || i % 5 == 0)
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
        int totalElements = 0;

        for(int x : multipliers){
            totalElements = limit/x;
            sum += (totalElements * ( x + totalElements * x)) / 2;
            interception *=x;
        }

        totalElements= limit/interception;
        sum-= (totalElements * ( interception + totalElements * interception)) / 2;
        return sum;
    }

    public static void main(String [] args){
        int limit = 1000;
        long startTime = System.nanoTime();
        System.out.println("FindSum =" + findMultiplesOf(limit));
        long endTime = System.nanoTime();
        System.out.println("Execution time:" + (endTime - startTime));


        int[] multipliers = {3,5};
        startTime = System.nanoTime();
        System.out.println("FindSum =" + findMultiplesOf(multipliers, limit-1));
        endTime = System.nanoTime();
        System.out.println("Execution time:" + (endTime - startTime));

    }
}