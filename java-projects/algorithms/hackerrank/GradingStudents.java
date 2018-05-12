/**
https://www.hackerrank.com/challenges/grading/problem
**/

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GradingStudents {

    static final int  FAILLING_GRADE = 37;
    static final int  ROUNDING = 5;
    static final int  ALLOW_TO_ROUND = 2; 
    /*
     * Complete the gradingStudents function below.
     */
    static int[] gradingStudents(int[] grades) {
        for(int i = 0; i < grades.length;i++){ 
            int grade = grades[i];
            int roundingGap = (ROUNDING - (grade % ROUNDING));
            
            if(grade <= FAILLING_GRADE || roundingGap > ALLOW_TO_ROUND){ continue;}
            if(grade < 0 || grade > 100){ continue;}// check for error
            grades[i] = grade + roundingGap;
        }
        return grades;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] grades = new int[n];

        for (int gradesItr = 0; gradesItr < n; gradesItr++) {
            int gradesItem = Integer.parseInt(scan.nextLine().trim());
            grades[gradesItr] = gradesItem;
        }

        int[] result = gradingStudents(grades);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}

