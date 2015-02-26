/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sorting;

/**
 *
 * @author Dinanajana
 */
/**************************************************
 * Simple sorting algorithms 
 * Modified by E/11/133
 **************************************************/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
class CompareSorting { 

    static void insertion_sort(int [] data) {
        
        int temp;
        
        for (int i = 1; i < data.length; i++) {
            
            for(int j = i ; j > 0 ; j--){
            
                if(data[j] < data[j-1]){
                
                    temp = data[j];
                    
                    data[j] = data[j-1];
                    
                    data[j-1] = temp;
                }
            }
        }
        
    }


    static void selection_sort(int [] data) { 
        for (int i = 0; i < data.length - 1; i++)
        {
            int index = i;
            
            for (int j = i + 1; j < data.length; j++)
                
                if (data[j] < data[index])
                   
                    index = j;
            
            int val = data[index]; 
           
            data[index] = data[i];
            
            data[i] = val;
        }
    }

    static void bubble_sort(int [] data) { 
        
        int i,j,tmp;
        for(i = data.length ; i >= 0 ; i-- ){
            
            for(j = 0 ; j < data.length - 1 ; j++ ){
                
                if(data[j] > data[j + 1]){
                
                    tmp = data[j];
                    
                    data[j] = data[j + 1];
                    
                    data[j + 1] = tmp;
                
                }
            
            }
        
        }
        
    }

    static int [] generate_data(int sizeOfData) { 
	/* create an array of sizeOfData and 
	 * populate with random integers betweem 0-1000
	 */
        if (sizeOfData == 0){
            
            System.out.println("Null array");
            
            return null;
        }
        int [] arr = new int [sizeOfData];
        
        Random generator = new Random();
        
        for(int i = 0 ; i < sizeOfData ; i++){
            
            arr[i] = generator.nextInt(1000);
        
        }
            
	return arr; 
    }

    static int [] duplicate_array(int [] data) { 
	/* create a duplicate array of the given 
	 * useful when sending the same array to different 
	 * algorithms.
	 */
        if(data.length == 0){
        
            System.out.println("Null array");
        
            return null;
        }
        
        int [] dup = new int [data.length];
        
        System.arraycopy(data, 0, dup, 0, data.length);
	
        return dup; 
    }

    static void postCondition(int [] data) { 
	/* if sorted, for any i data[i] > data[i-1]
	 * Need to run this with java -ea CompareSorting
	 */
	int i; 
	for(i=1; i < data.length; i++) 
	    if(data[i] > data[i-1]) break; 

	assert i == data.length : "Algorithm is broken";
    }

    public static void main(String [] ar) {
	/*int [] d = {1, 2, 3, 14, 4, 5, 7};
	postCondition(d);
	System.out.println("HG" + d.length);
                */
        int [] arr1 ;
        int [] arr2 ;
        int [] arr3 ;
        int start = 1000 ;
        
        ArrayList bubble_du = new ArrayList(); 
        ArrayList selection_du = new ArrayList();
        ArrayList insertion_du = new ArrayList();
        ArrayList steps = new ArrayList();
        
        while(start < 400001){
            
            arr1 = generate_data(start);
            arr2 = duplicate_array(arr1);
            arr3 = duplicate_array(arr1);
            
            long startTime ;
            long endTime ;
                       
            System.out.println("Step size " + start);
            
            //Bubble sort
            startTime = System.currentTimeMillis();
            bubble_sort(arr1);
            endTime = System.currentTimeMillis();
            bubble_du.add(endTime-startTime);
            
            
            
            //Insertion sort 
            startTime = System.currentTimeMillis();
            insertion_sort(arr2);
            endTime = System.currentTimeMillis();
            insertion_du.add(endTime-startTime);
            
            //Selection sort
            startTime = System.currentTimeMillis();
            selection_sort(arr3);
            endTime = System.currentTimeMillis();
            selection_du.add(endTime-startTime);
            
            steps.add(start);
            
            start  = start + 10000;
        }
        System.out.println("Steps" + steps);
        
        System.out.println("Bubble Sort duration" + bubble_du);
        
        System.out.println("Insertion Sort duration" + insertion_du);
        
        System.out.println("Selection Sort duration" + selection_du);
    }
                
}

