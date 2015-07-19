package assignment1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by PanamaCRI on 07/18/15.
 */
public class CountInversions {
    public static void main(String[] args){

        try{
            BufferedReader bufferRead;
            String line;
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
            line = bufferRead.readLine();
            int n = Integer.parseInt(line);

            int [] array = new int[n];
            int [] temp = new int[n];


            for (int i = 0; i < n; i++) {
                line = bufferRead.readLine();
                array[i] = Integer.parseInt(line);
            }

            System.out.println(countInversions(array, temp, 0, n-1));
        }
        catch (Exception e){
        }
    }

    public static long countInversions(int[] array, int[] temp, int i, int j){

        if(i >= j)
            return 0;

        return countInversions(array, temp, i, (i+j)/2) + countInversions(array, temp, (i+j)/2+1, j) + countSplittedInversions(array, temp, i, j);
    }

    public static long countSplittedInversions(int[] array, int[] temp, int i, int j){

        int left = i, finalLeft = (i+j)/2, right = (i+j)/2+1, finalRight = j;
        long inversions = 0;

        int k;
        for (k = i; k <= j && left <= finalLeft && right <= finalRight ; k++) {
            if(array[left] <= array[right])
                temp[k] = array[left++];
            else{
                temp[k] = array[right++];
                inversions += (finalLeft - left + 1);
            }
        }
        for (; left <= finalLeft; left++) {
            temp[k++] = array[left];
        }

        for (; right <= finalRight ; right++) {
            temp[k++] = array[right];
        }

        for (int l = i; l <= j ; l++) {
            array[l] = temp[l];
        }

        return inversions;
    }
}
