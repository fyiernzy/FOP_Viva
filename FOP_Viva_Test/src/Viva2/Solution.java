package Viva2;

import java.util.List;
import java.util.ArrayList;

public class Solution {
    private boolean isSameDimension(double[][] a, double[][] b) {
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b[i].length)
                return false;
        }
        return true;
    }

    public double[][] addition(double[][] a, double[][] b) {
        if (!isSameDimension(a, b))
            return null;
        double[][] c = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    public double[][] subtraction(double[][] a, double[][] b) {
        if (!isSameDimension(a, b))
            return null;
        double[][] c = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[i].length; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    public double[][] multiplication(double[][] a, double[][] b) {
        if (a[0].length != b.length)
            return null;
        double[][] c = new double[a.length][b[0].length];
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    public double[] determinant(double[][][] a) {
        double[] determinant = new double[a.length];
        for (int i = 0; i < a.length; i++)
            determinant[i] = getDeterminant(a[i]);
        return null;
    }

    private double getDeterminant(double[][] a) {
        if (a.length != a[0].length)
            return 0;
        if (a.length == 1)
            return a[0][0];
        double determinant = 0;
        for (int i = 0; i < a.length; i++) {
            double[][] subMatrix = getSubMatrix(a, 0, i); // Assuming getSubMatrix is updated to handle double[][]
            determinant += Math.pow(-1, i) * a[0][i] * getDeterminant(subMatrix);
        }
        return determinant;
    }

    private double[][] getSubMatrix(double[][] a, int excludingRow, int excludingCol) {
        double[][] subMatrix = new double[a.length - 1][a.length - 1];
        int r = -1;

        for (int i = 0; i < a.length; i++) {
            if (i == excludingRow)
                continue;
            r++;
            int c = -1;

            for (int j = 0; j < a.length; j++) {
                if (j == excludingCol)
                    continue;
                subMatrix[r][++c] = a[i][j];
            }
        }
        return subMatrix;
    }

    public double[][] inverse(double[][] a) {
        double determinant = getDeterminant(a);
        if (determinant == 0)
            return null;

        double[][] inverse = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                double[][] subMatrix = getSubMatrix(a, i, j);
                inverse[j][i] = Math.pow(-1, i + j) * getDeterminant(subMatrix) / determinant;
            }
        }

        return inverse;
    }

    public String removeSpecialCharacters(String string) {
        return string.replaceAll("[^a-zA-Z0-9]", "");
    }

    public boolean isPalindrome(String string) {
        string = cleanUpString(string);
        for (int i = 0; i < string.length() / 2; i++)
            if (string.charAt(i) != string.charAt(string.length() - i - 1))
                return false;
        return true;
    }

    public boolean isValidPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while(start <= last) {
        	char currFirst = s.charAt(start);
        	char currLast = s.charAt(last);
        	if (!Character.isLetterOrDigit(currFirst )) {
        		start++;
        	} else if(!Character.isLetterOrDigit(currLast)) {
        		last--;
        	} else {
        		if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
        			return false;
        		}
        		start++;
        		last--;
        	}
        }
        return true;
    }

    private String cleanUpString(String string) {
        return removeSpecialCharacters(string).toLowerCase();
    }

    public int[] generatePrimes(int lower, int upper) {
        if (upper < 2 || lower > upper)
            return null;
        List<Integer> primeNumbers = new ArrayList<>();
        if (lower <= 2)
            primeNumbers.add(2);
        lower = Math.max(lower, 3);
        while (lower <= upper) {
            if (isPrime(lower))
                primeNumbers.add(lower);
            lower += 2;
        }
        return primeNumbers.stream().mapToInt(i -> i).toArray();
    }

    public boolean isPrime(int num) {
        if (num < 2)
            return false;
        for (int i = 3; i <= Math.sqrt(num); i += 2)
            if (num % i == 0)
                return false;
        return true;
    }
}
