package assignment2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by PanamaCRI on 07/19/15.
 */
public class Sorting {

    private static long comparissons;

    public static void main(String[] args){
        try{
            BufferedReader bufferRead;
            String line;
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
            line = bufferRead.readLine();
            int n = Integer.parseInt(line);

            int [] array = new int[n];

            for (int i = 0; i < n; i++) {
                line = bufferRead.readLine();
                array[i] = Integer.parseInt(line);
            }

            long count = countComparissons(array, 0, n - 1);
            System.out.println(count);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static long countComparissons(int[] array, int start, int end){
        comparissons = 0;
        quickSort(array, start, end);

        return comparissons;
    }

    public static void quickSort(int[] array, int start, int end){
        if(start >= end)
            return;

        int pivotPosition = medianOf3PivotSelection(array, start, end);
        int q = partition(array, start, end, pivotPosition);

        comparissons += (q - start);
        comparissons += (end - q);

        quickSort(array, start, q - 1);
        quickSort(array, q + 1, end);
    }

    public static int partition(int[] array, int start, int end, int pivotPosition){
        swap(array, start, pivotPosition);
        int pivot = array[start];
        int i = start + 1;

        for (int j = start + 1; j <= end ; j++) {
            if(array[j] < pivot){
                swap(array, i, j);
                i++;
            }
        }

        swap(array, start, i - 1);

        return i - 1;
    }

    public static int partition2(int[] array, int start, int end, int pivotPosition){
        swap(array, start, pivotPosition);
        int pivot = array[start];
        int i = start;

        for (int j = start + 1; j <= end ; j++) {
            if(array[j] < pivot){
                swap(array, i + 1, j);
                swap(array, i + 1, i);
                i++;
            }
        }

        array[i] = pivot;

        return i;
    }

    public static void swap(int[] array, int i, int j){
        int t = array[i];
        array[i] = array[j];
        array[j]= t;
    }

    public static int firstElementPivotSelection(int[] array, int start, int end){
        return start;
    }

    public static int lastElementPivotSelection(int[] array, int start, int end){
        return end;
    }

    public static int medianOf3PivotSelection(int[] array, int start, int end){
        return positionOfMin(array, positionOfMax(array, start, end), (start + end) / 2);
    }

    public static int positionOfMin(int[] array, int i, int j){
        return array[i] < array[j] ? i : j;
    }

    public static int positionOfMax(int[] array, int i, int j){
        return array[i] >= array[j] ? i : j;
    }
}
