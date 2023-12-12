package Bitwave;

import java.util.Arrays;

public class Solution_22115428 {
    public void displayMatrix(double matrix[][], int size){
        for(double[] row:matrix){
            System.out.println(Arrays.toString(row));
        }
    }


    public double[][] addition(double[][] a, double[][] b) {
        // Implement this method
        if((a.length != b.length) && a[0].length != b[0].length) return null;
        double[][]arrAdd=new double[a.length][a[0].length];
        for(int row=0;row<a.length;row++){
            for(int col=0;col<a[row].length;col++){
                arrAdd[row][col]=a[row][col]+b[row][col];
            }
        }
        return arrAdd;
    }

    public double[][] subtraction(double[][] a, double[][] b) {
        // Implement this method
        if((a.length != b.length) && a[0].length != b[0].length) return null;
        double[][]arrSub=new double[a.length][a[0].length];
        for(int row=0;row<a.length;row++){
            for(int col=0;col<a[row].length;col++){
                arrSub[row][col]=a[row][col]-b[row][col];
            }
        }
        return arrSub;
    }

    public double[][] multiplication(double[][] a, double[][] b) {
        // Implement this method
        if(a[0].length != b.length) return null;
        double[][]arrMul=new double[a.length][b[0].length];
        for(int row = 0; row < a.length; row++){
            for(int col = 0; col < b[0].length; col++){
                int prod = 0;
                for(int row2 = 0; row2 < b.length; row2++){
                    prod += a[row][row2] * b[row2][col];
                }
                arrMul[row][col] = prod;
            }
        }
        return arrMul;
    }
    public void getMinor(double[][] a, double[][] detMat, int discardedRow, int discardedCol, int length) {
        //assign the 0 to discared row as determinant only involves first row
        // Initialize the row index for the determinant matrix.
        int detMatRow = 0;
        // Iterate through each row of the original matrix.
        for (int row = 0; row < length; row++) {
            // Skip the discarded row to create the minor matrix.
            if (row == discardedRow)
                continue;

            // Initialize the column index for the determinant matrix.
            int detMatCol = 0;

            // Iterate through each column of the original matrix.
            for (int col = 0; col < length; col++) {
                // Skip both the discarded row and column to create the minor matrix.
                if (row != discardedRow && col != discardedCol) {
                    // Copy the element to the determinant matrix.
                    //find the determinant(a,d,b,c)one by one of [0][0]through loop and place it into detMat
                    //after get detMatRow and detMatCol, detMatCol will increase by 1 (until col is not < length anymore)
                    detMat[detMatRow][detMatCol++] = a[row][col];
                }
            } //exit loop

            // Move to the next row in the determinant matrix.
            detMatRow++;
        }
    }
    //Method to find ADJUGATE
    public double[][] adjugate(double[][] a) {
        int length = a.length;
        double[][] result = new double[length][length];
        double[][][] detMat = new double[1][length - 1][length - 1];

        for (int rowMain = 0; rowMain < length; rowMain++) {
            //difference between adjugate and determinant method is adjugate will get all elements' minor(first row, 2nd and third) while determinant only get minor of elements at first row
            for (int colMain = 0; colMain < length; colMain++) {
                //Call method getMinor to calculate the determinant of the minor matrix formed by excluding the current row and column.
                getMinor(a, detMat[0], rowMain, colMain, length);
                result[colMain][rowMain] = ((rowMain + colMain) % 2 == 1 ? -1 : 1) * determinant(detMat)[0];
            }
        }
        return result; //return adjugate
    }

    public double[] determinant(double[][][] a) {
        // Implement this method
        int numOfMatrix = a.length;
        double[] result = new double[numOfMatrix];
        int length = a[0].length;

        //Base case 1: 1x1 matrix, return the first element in array
        if (length == 1){
            for(int i = 0; i <  numOfMatrix; i++){
                result[i] = a[i][0][0];
            }
        }

        //Base case 2: 2x2 matrix, use the formula for a 2x2 determinant
        else if (length == 2){
            for(int i = 0; i < numOfMatrix; i++){
                result[i] = a[i][0][0] * a[i][1][1] - a[i][0][1] * a[i][1][0];
            }
        }

        else {
            double[][][] detMat = new double[numOfMatrix][length - 1][length - 1];

            // Iterate over the elements of the first row (colMain is the current column)
            for(int i = 0; i < numOfMatrix; i++){
                for (int colMain = 0; colMain < length; colMain++) {
                    getMinor(a[i], detMat[i], 0, colMain, length);
                    // Add the determinant of the submatrix multiplied by the corresponding element of the first row
                    result[i] += ((colMain % 2) == 1 ? -1 : 1) * a[i][0][colMain] * determinant(detMat)[i];
                }
            }
        }
        return result;

    }

    public double[][] inverse(double[][] a) {
        // Implement this method
        //find determinant of matrix using determinant method
        double[][][] matrix = new double[1][][];
        matrix[0] = a;
        double det = determinant(matrix)[0];
        if (det == 0)
            return null; //inverse does not exist
        double[][] result = new double[a.length][a.length];
        double[][] adjoint = adjugate(a);
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a.length; col++) {
                result[row][col] = adjoint[row][col] / det;
            }
        }
        return result;
    }

    public String removeSpecialCharacters(String string) {
        // Implement this method
        return string.replaceAll("[^0-9a-zA-Z]","");
    }

    public boolean isPalindrome(String string) {
        // Implement this method
        int length = string.length();
        //check whether sentence input is a palindrome by looping from the first character
        for(int i = 0; i < length/2 ; i++){
            if(string.charAt(i) != string.charAt(length-i-1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int n){
        if(n < 2 || (n > 2 && n % 2 == 0)) return false;
        for(int i = 3; i < n; i += 2){
            if(n % i == 0) return false;
        }
        return true;
    }

    public int[] generatePrimes(int lower, int upper) {
        // Implement this method
        int [] primeList = new int[upper - lower];
        for(int i = lower, j = 0; i <= upper; i++){
            if(isPrime(i))
                primeList[j++] = i;
        }
        return primeList;
    }

    public boolean isValidPalindrome(String string){
        string = string.toLowerCase();
        System.out.println(string);
        String formattedString = "";
        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);
            if(Character.isLetterOrDigit(c)){
                formattedString += c;
            }
        }
        string = formattedString;
        System.out.println(formattedString);

        for(int i = 0; i < formattedString.length() / 2; i++){
            if(string.charAt(i) != string.charAt(string.length() - i - 1)){
                return false;
            }
        }
        return true;
    }
}