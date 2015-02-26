/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *This class contains methods required 
 * for sorting
 * 
 */
public class OddMan {
    
    public static void main(String [] arg){
    
        ExtractData data = new ExtractData() ;
        
        StudentData [] a =data.relation.toArray(new StudentData [data.relation.size()]) ;
        
        mergeSort(a);
        
       /* for(int i = 0 ; i < a.length ; i++){
        
            System.out.println(a[i].personalityno + " " + a[i].regNo);
        }*/
        
        List<StudentData> b = new ArrayList<> ();
        
        b.addAll(Arrays.asList(a));
        
        int lengthOfb = a.length;
        
        if(lengthOfb%2 == 0){
        
            System.out.println("There can't be an odd person");
        
        }else{
            int k = 1;
      
            while(true){
                if(lengthOfb > 1){
                    System.out.println("Round " + k);
                    k++;
                
                    int lengthOfdiff = lengthOfb - 1;
                
                    double [] diff = new double [lengthOfdiff] ;
                
                        for(int i  = 0 ; i < lengthOfb -1 ; i++ ){
        
                    
                            double c  = b.get(i+1).personalityno;
                            double d =  b.get(i).personalityno;
                          
                            diff[i] = c - d ;
                            
                        }
                        
                        for(int i = 0 ; i < lengthOfdiff - 1 ; i++){
                    
                            if(diff[i] < diff[i+1] /* && 
                                    b.get(i).regNo != null &&
                                    b.get(i+1).regNo != null*/){
                                
                                System.out.println(b.get(i).regNo + " " +b.get(i).personalityno 
                                        + " & "+b.get(i+1).regNo + " " +b.get(i+1).personalityno
                                + " coupled" );
                                b.get(i).regNo = null;
                                b.get(i+1).regNo = null;
                                i++;
                                
                        
                            }
                              
                        }
        
                        for (int i = 0 ; i < b.size() ; i++) {
                            if (b.get(i).regNo == null) {
                                
                                //System.out.println( " " + b.get(i).personalityno);
                                b.remove(i);
                                    if(i > 0) i--;
                                    
                            }
                        }
                        /*for(int i = 0 ; i < b.size() ; i++){
        
                            System.out.println( " " + b.get(i).personalityno + " " + b.get(i).regNo);
                        }*/
                
                
                        lengthOfb = b.size();
                
                }else{
            
                        System.out.println("Odd one is :" + b.get(0).regNo +" "
                        + "And his/her personality number is :"
                        + "" + b.get(0).personalityno);
                        break;
                }
            
            }
        }
         
    
    }
static void mergeSort(StudentData [] data) {
    StudentData [] tmp = new StudentData [data.length];
    
    mergeSort(data, tmp, 0, data.length-1);
    
}
  
static void mergeSort(StudentData [] data, StudentData [] tmp, int start,
    int end){
    if(start < end) {
        int mid = (start + end) / 2;
        
        mergeSort(data, tmp, start, mid);
        
        mergeSort(data, tmp, mid+1, end);
        /* merge the two sorted arrays */
        merge(data, tmp, start, mid, end);
    }
}

static void merge(StudentData [] data, StudentData [] tmp, int start, int
    mid, int end) {
    int total = end - start + 1;
    
    int i = start, j = mid+1, k;
    /* use tmp array for movements; starting from 0 */
    for(k=0; (k < total) && (i <= mid) && (j <= end); k++) {
        
        if(data[i].personalityno < data[j].personalityno) tmp[k] = data[i++];
        
        else tmp[k] = data[j++];
    }
    
    for(;k < total && i <= mid; k++, i++) tmp[k] = data[i];
    
    for(;k < total && j <= end; k++, j++) tmp[k] = data[j];
    /* copy the merged results back to the data array */
    for(k=0; k < total; k++)
    
    data[start + k] = tmp[k];
}

  
}
