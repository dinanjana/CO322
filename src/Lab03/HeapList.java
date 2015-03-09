/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab03;

import java.util.LinkedList;

/**
 *
 * @author Dinanajana
 * @param <T>
 */
public class HeapList<T extends Comparable<T>> implements HeapInterface {
    
    private final LinkedList <T> arrayList;
    
    
    public HeapList() { 
	arrayList = new LinkedList <>(); 
	
    }

    @Override
    public boolean isRoot(int index) {	return (index == 0);   }
    @Override
    public int leftChild(int index)  { return 2 * index + 1;  }
    @Override
    public int parent(int index)     { return (int)((index - 1) / 2); }
    @Override
    public int rightChild(int index) { return 2 * index + 2;   }
    
    @Override
    public T myParent(int index) { return arrayList.get(parent(index)); }
    //@Override
    
    
    @Override
    public boolean hasLeftChild(int i) { return leftChild(i) < arrayList.size(); } 
    @Override
    public boolean hasRightChild(int i){ return rightChild(i) < arrayList.size(); } 

    @Override
    public void swap(int a, int b) { 
	T tmp = arrayList.get(a);
        
	arrayList.set(a, arrayList.get(b)); 
	arrayList.set(b, tmp);
    }
    
    

    @Override
    public boolean isEmpty() { return (arrayList.size() == 1); } 


    /* adding heap */
    public void add(T value) { 
	
	arrayList.add(value); 
	bubbleUp(); 
    }

    @Override
    public void bubbleUp() { 
	if(arrayList.size() == 0) throw new IllegalStateException("Shape error");
	int index = arrayList.size() - 1;  
	while(!isRoot(index)) { 
	    if(myParent(index).compareTo(arrayList.get(index)) <= 0) break; 
	    /* else part */
	    swap(parent(index), index); 
	    index = parent(index);
	}
    }  

    /* removing */

    @Override
    public T remove() {
	if(isEmpty()) return null; 
	T res = arrayList.get(0); /* root */
	arrayList.set(0, arrayList.get(arrayList.size()-1)); 
	//size --; 
	bubbleDown(); 
	return res;
    }

    @Override
    public void bubbleDown() { 
	/* implmement me */
        
        int index = 0;
        
            while(index < arrayList.size() ){
                
                
                if(hasLeftChild(index)&&
                        hasRightChild(index)){
                    
                        //check for equal cases
                        if(arrayList.get(leftChild(index)).compareTo
                           (arrayList.get(rightChild(index))) >= 0){
                    
                            if(arrayList.get(index).compareTo
                            (arrayList.get(rightChild(index))) >= 0){
                    
                            swap(index,rightChild(index));
                        
                            index = rightChild(index);
                            }else{
                            
                            break;
                            }
                            
                        }
                        //check left
                        else if(arrayList.get(leftChild(index)).compareTo
                                (arrayList.get(rightChild(index))) < 0){
                            
                            if(arrayList.get(index).compareTo
                              (arrayList.get(leftChild(index))) > 0){
                        
                            swap(index,leftChild(index));
                        
                            index = leftChild(index);
                        
                            }else{
                            
                            break;
                            }
                        }else{ 
                            //exit loop
                            break;                        
                        }
                    }
                    // Left child only
                    if(hasLeftChild(index) && !hasRightChild(index)){
                    
                        if(arrayList.get(index).compareTo
                          (arrayList.get(leftChild(index))) > 0){
                        
                            swap(index,leftChild(index));
                        
                            index = leftChild(index);
                        
                        }else{
                        
                            break;
                        }
                
                
                
                }
                // Right child only
                if(hasRightChild(index) && !hasLeftChild(index)){
                
                    if(arrayList.get(index).compareTo
                      (arrayList.get(rightChild(index))) > 0){
                    
                        swap(index,rightChild(index));
                        
                        index = rightChild(index);
                    }else{
                        
                        break;
                    
                    }
                
                }
                // no children
                if(!hasRightChild(index) && !hasLeftChild(index)){
                
                    break;
                }
                
            }
        
    }
            
            


    public void show() {
        arrayList.stream().forEach((arrayList1) -> {
            System.out.print(arrayList1 + " ");
        });
	System.out.println("=======");
    }


    public static void main(String [] args) {
	HeapList heap = new HeapList<>(); 

	for(int i=0; i<10; i++) {
	    heap.add((int) (Math.random() * 10)); 
	    heap.show();
	}


	System.out.println("You should see sorted numbers");
	while(!heap.isEmpty()) 
	    System.out.print(heap.remove());
	
    }
    
}
