/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sorting;

import java.util.Arrays;
import static sorting.CompareSorting.bubble_sort;
import static sorting.CompareSorting.generate_data;

/**
 *
 * @author Dinanajana
 */
public class Sorting {
   
    
    public static void main(String [] args){
    
        int size = 10000;
        int [] arr = new int[size];
        int [] arr1 = new int[size];
        int [] arr2 = new int[size];
        long startTime;
        long endTime;
       
        arr = generate_data(size);
        
        System.arraycopy(arr, 0, arr1, 0, size);
        System.arraycopy(arr, 0, arr2, 0, size);
        
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        
        startTime = System.currentTimeMillis();
        bubble_sort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("1st attempt " + (endTime - startTime));
        
        startTime = System.currentTimeMillis();
        bubble_sort(arr1);
        endTime = System.currentTimeMillis();
        System.out.println("2nd attempt " + (endTime - startTime));
        
        startTime = System.currentTimeMillis();
        bubble_sort(arr2);
        endTime = System.currentTimeMillis();
        System.out.println("3rd at+tempt " + (endTime - startTime));
    
    }
}
